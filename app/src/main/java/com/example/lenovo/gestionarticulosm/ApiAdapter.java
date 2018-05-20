package com.example.lenovo.gestionarticulosm;


import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Update by DANIEL GARCIA on 03/08/2017.
 *
 * This class implements the retrofit library using the singleton pattern to prevent an instance from being created twice
 * Esta clase implementa la libreria retrofit usando el patron singleton para evitar que una instancia se cree dos veces
 *
 */
public class ApiAdapter {

    private static ApiService API_SERVICE;

    public static ApiService getApiService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);

        String baseUrl = "http://127.0.0.1:8000/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create(ApiService.class);
        }

        return API_SERVICE;
    }

}
