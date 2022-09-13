package com.example.a1agroservice.retrofit;

import com.example.a1agroservice.service.PessoaService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfRetrofit {
    private static Retrofit retrofit;

    private ConfRetrofit() {}

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(PessoaService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }
}
