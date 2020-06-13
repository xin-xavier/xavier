package com.example.xavier.http.client;

import com.example.xavier.http.url.DNSConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitFactory {

    static Retrofit apiServerRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(DNSConfig.getInstance().getApiServerUrl())
                .client(XavierHttpClient.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    static Retrofit apiRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(DNSConfig.getInstance().getApiUrl())
                .client(XavierHttpClient.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    static Retrofit gsonApiServerRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(DNSConfig.getInstance().getApiServerUrl())
                .client(XavierHttpClient.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    static Retrofit scalarsApiServerRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(DNSConfig.getInstance().getApiServerUrl())
                .client(XavierHttpClient.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}