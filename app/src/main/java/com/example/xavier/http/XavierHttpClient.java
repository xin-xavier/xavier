package com.example.xavier.http;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.xavier.BuildConfig;
import com.example.xavier.app.ConstantPool;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.example.xavier.app.ConstantPool.CACHE_DIR;

public class XavierHttpClient {

    private static volatile XavierHttpClient singleton;

    private boolean login = false;
    private String token = "";

    //设缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    private OkHttpClient okHttpClient;

    private XavierHttpClient() { }

    public static XavierHttpClient getInstance() {
        if (singleton == null) {
            synchronized (XavierHttpClient.class) {
                if (singleton == null) {
                    singleton = new XavierHttpClient();
                }
            }
        }
        return singleton;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(final Context context) {
        if (okHttpClient != null) {
            return;
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // 因为BaseUrl不同所以这里Retrofit不为静态，但是OkHttpClient配置是一样的,静态创建一次即可
        File cacheFile = new File(context.getCacheDir(), CACHE_DIR); // 指定缓存路径
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); // 指定缓存大小100Mb
        // 云端响应头拦截器，用来配置缓存策略
        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected()) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                    Log.e(ConstantPool.Http_TAG, "no network");
                }

                if (login) {
                    request = request.newBuilder().header("token", token).method(request.method()
                            , request.body()).build();
                }

                Response originalResponse = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    int maxAge = 60 * 60;
                    return originalResponse
                            .newBuilder()
                            .header("Cache-Control", "public ,max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    return originalResponse
                            .newBuilder()
                            .header("Cache-Control", "public, " + "only-if-cached," + CACHE_STALE_SEC)
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };

        if (BuildConfig.DEBUG) { // 判断是否为debug
            // 如果为 debug 模式，则添加日志拦截器
            HttpLoggingInterceptor loggingInterceptor =
                    new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override  //日志过滤器
                        public void log(String message) {
                            Log.i(ConstantPool.Http_TAG, message);
                        }
                    });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        okHttpClient = builder.cache(cache)
                .addNetworkInterceptor(rewriteCacheControlInterceptor)
                //.addInterceptor(new HandleErrorInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)    //设置连接超时
                .readTimeout(20, TimeUnit.SECONDS)      //设置读取超时
                .writeTimeout(20, TimeUnit.SECONDS)    // 设置写入超时
                // 默认重试5次取消http请求
                .retryOnConnectionFailure(true) // 在网络请求失败时重试
                .build();

    }

    OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
