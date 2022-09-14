package com.example.a1agroservice.retrofit;

import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.service.PessoaService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfRetrofit {
    private final Retrofit retrofit;

    public ConfRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.matheuscastiglioni.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PessoaService getPessoaService() {
        return this.retrofit.create(PessoaService.class);
    }
}
