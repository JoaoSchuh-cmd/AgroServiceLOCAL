package com.example.a1agroservice.controllers;

import com.example.a1agroservice.retrofit.ConfRetrofit;
import com.example.a1agroservice.service.PessoaService;

import retrofit2.Call;

public abstract class GenericController<Objeto> {
    public ConfRetrofit retrofit = new ConfRetrofit();

    public abstract void configInicial();

    public abstract Call<Objeto> getById(int id);

    public abstract Call<Objeto> getAll();

    public abstract Call<Objeto> insert(Objeto pessoa);

    public abstract Call<Objeto> update(int id, Objeto pessoa);

    public abstract Call<Objeto> delete(int id);

    public abstract void fromJson(Call<Objeto> obj);
}
