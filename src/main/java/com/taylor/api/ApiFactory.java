package com.taylor.api;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ApiFactory {

    public static Api create(String baseUrl, Converter.Factory factory) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, SECONDS)
                .readTimeout(10, SECONDS)
                .writeTimeout(10, SECONDS)
                .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .build()
                .create(Api.class);
    }
}