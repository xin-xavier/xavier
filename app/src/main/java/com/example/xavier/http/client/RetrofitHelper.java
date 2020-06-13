package com.example.xavier.http.client;

import android.util.Log;

import com.example.xavier.http.Server;

/**
 * des 实例化Retrofit,获取ApiService
 */
public class RetrofitHelper {

    private static Server apiService;

    private static Server api;

    static {
        apiService = RetrofitFactory.apiServerRetrofit().create(Server.class);
    }

    private RetrofitHelper() { }

    public static Server getApiService() {
        return apiService;
    }

    public static Server getApi() {
        if (api == null) {
            Log.i("RetrofitHelper", "getApi: ");
            synchronized (Server.class) {
                if (api == null) {
                    api = RetrofitFactory.apiRetrofit().create(Server.class);
                }
            }
        }
        return api;
    }

}
