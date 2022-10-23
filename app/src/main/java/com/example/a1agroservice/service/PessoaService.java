package com.example.a1agroservice.service;

import com.example.a1agroservice.models.Pessoa;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PessoaService {

    @GET("pessoa")
    Call<Pessoa> getPessoas();

    @GET("pessoa/id={id}")
    Call<Pessoa> getPessoa(@Path("id") int id);

    @GET("pessoa/usuario={usuario}")
    Call<Pessoa> getPessoa(@Path("usuario") String nome);

    @POST("pessoa")
    Call<Pessoa> postPessoa(@Body Pessoa pessoa);

    @PATCH("pessoa/{id}")
    Call<Pessoa> patchPessoa(@Path("id") int id, @Body Pessoa pessoa);

    @DELETE("pessoa/{id}")
    Call<Pessoa> deletePessoa(@Path("id") int id);

}
