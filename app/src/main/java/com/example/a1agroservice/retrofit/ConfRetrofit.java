package com.example.a1agroservice.retrofit;

import com.example.a1agroservice.service.PessoaService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfRetrofit {
    private final Retrofit retrofit;
    public static final String BASE_URL = "http://192.168.1.11:27017";

    public ConfRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PessoaService getPessoaService() {
        return this.retrofit.create(PessoaService.class);
    }
}
