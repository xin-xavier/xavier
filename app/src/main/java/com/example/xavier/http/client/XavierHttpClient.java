package com.example.xavier.http.client;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.example.xavier.BuildConfig;
import com.example.xavier.utils.XavierLogUtils;

import org.jetbrains.annotations.NotNull;

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

import static com.example.xavier.app.api.ConstantPool.CHCHE_CONTROL;
import static com.example.xavier.app.api.ConstantPool.OK_CACHE_DIR;
import static com.example.xavier.app.api.ConstantPool.OK_CACHE_SIZE;
import static com.example.xavier.app.api.ConstantPool.OK_CACHE_STALE_SEC;
import static com.example.xavier.app.api.ConstantPool.OK_JSON;
import static com.example.xavier.app.api.ConstantPool.OK_TAG;
import static com.example.xavier.app.api.ConstantPool.PRAGMA;
import static com.example.xavier.app.api.FieldConstant.MODEL;
import static com.example.xavier.app.api.FieldConstant.TOKEN;
import static com.example.xavier.app.api.FieldConstant.VERSION;

public class XavierHttpClient {

    private static volatile XavierHttpClient singleton;

    private boolean login = false;
    private String token = "";

    private OkHttpClient okHttpClient;

    private XavierHttpClient() {
    }

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

    // 初始化
    public void init(final Context context) {
        if (okHttpClient != null) {
            return;
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // 因为BaseUrl不同所以这里Retrofit不为静态，但是OkHttpClient配置是一样的,静态创建一次即可
        File cacheFile = new File(context.getCacheDir(), OK_CACHE_DIR); // 指定缓存路径
        Cache cache = new Cache(cacheFile, OK_CACHE_SIZE); // 指定缓存大小100Mb
        // 云端响应头拦截器，用来配置缓存策略
        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected()) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                    Log.e(OK_TAG, "no network");
                }

                Request.Builder headerBuider = request
                        .newBuilder()
                        .header(VERSION, AppUtils.getAppVersionName())
                        .header(MODEL, DeviceUtils.getManufacturer());

                if (login) {
                    headerBuider.header(TOKEN, token);
                }

                request = headerBuider.method(request.method()
                        , request.body()).build();

                Response originalResponse = chain.proceed(request);
                if (NetworkUtils.isConnected()) {
                    int maxAge = 60 * 60;
                    return originalResponse
                            .newBuilder()
                            .header(CHCHE_CONTROL, "public ,max-age=" + maxAge)
                            .removeHeader(PRAGMA)
                            .build();
                } else {
                    return originalResponse
                            .newBuilder()
                            .header(CHCHE_CONTROL, "public, " + "only-if-cached," + OK_CACHE_STALE_SEC)
                            .removeHeader(PRAGMA)
                            .build();
                }
            }
        };

        if (BuildConfig.DEBUG) { // 判断是否为debug
            // 如果为 debug 模式，则添加日志拦截器
            HttpLoggingInterceptor loggingInterceptor =
                    new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override  //日志过滤器
                        public void log(@NotNull String message) {
                            //Log.i(OK_TAG, message);
                            XavierLogUtils.INSTANCE.longInfo(OK_TAG,message);
                            XavierLogUtils.INSTANCE.jsonInfo(OK_JSON,message);
                        }
                    });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        okHttpClient = builder.cache(cache)
                .addNetworkInterceptor(rewriteCacheControlInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)    //设置连接超时
                .readTimeout(20, TimeUnit.SECONDS)      //设置读取超时
                .writeTimeout(20, TimeUnit.SECONDS)    // 设置写入超时
                // 默认重试5次取消http请求
                .retryOnConnectionFailure(true) // 在网络请求失败时重试
                .build();

    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
