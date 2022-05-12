package com.example.ibrahimr.movieum;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IbrahimR on 10/18/2020.
 */

public class RetrofitBuilder {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(String baseUrl){

        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
