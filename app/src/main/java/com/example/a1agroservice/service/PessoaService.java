package com.example.a1agroservice.service;

import com.example.a1agroservice.models.Pessoa;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PessoaService {
    public static final String BASE_URL = "http://192.168.1.11:27017";

    @GET("pessoa")
    Call<Pessoa> getPessoas();

    @GET("pessoa/{id}")
    Call<Pessoa> getPessoa(@Path("id") int id);

    @POST("pessoa")
    Call<Pessoa> postPessoa(@Body Pessoa pessoa);

    @PATCH("pessoa/{id}")
    Call<Pessoa> patchPessoa(@Path("id") int id, @Body Pessoa pessoa);

    @DELETE("pessoa/{id}")
    Call<Pessoa> deletePessoa(@Path("id") int id);

}
