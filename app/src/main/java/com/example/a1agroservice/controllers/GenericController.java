package com.example.a1agroservice.controllers;

import android.content.Context;

import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.retrofit.ConfRetrofit;

import java.util.ArrayList;

import retrofit2.Call;

public abstract class GenericController<Objeto> {
    public ConfRetrofit retrofit = new ConfRetrofit();

    public abstract void configInicial();

    public abstract Call<Objeto> getById(int id);

    public abstract ArrayList<Pessoa> getAll();

    public abstract void insert(Context contex, Objeto pessoa);

    public abstract Call<Objeto> update(int id, Objeto pessoa);

    public abstract Call<Objeto> delete(int id);
}
