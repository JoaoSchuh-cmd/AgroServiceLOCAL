package com.example.a1agroservice.controllers;

import android.util.Log;

import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.service.PessoaService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PessoaController extends GenericController<Pessoa> {
    private static PessoaController pessoaController;
    private PessoaService service;
    private RequestBody requestBody;

    private int id = 0;
    private Pessoa pessoa;
    private ArrayList<Pessoa> pessoas = new ArrayList();

    public PessoaController() {
        configInicial();
    }

    public static PessoaController getInstance() {
        return pessoaController == null ? pessoaController = new PessoaController() : pessoaController;
    }

    public int retornaProximoId() {
        return id + 1;
    }

    @Override
    public void configInicial() { service = retrofit.getPessoaService(); }

    @Override
    public Call<Pessoa> getById(int id) { return service.getPessoa(id); }

    @Override
    public Call<Pessoa> getAll() { return service.getPessoas(); }

    @Override
    public Call<Pessoa> insert(Pessoa pessoa) {
        pessoas.add(pessoa);

        requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {

            }
        }

        return service.postPessoa(pessoa);
    }

    @Override
    public Call<Pessoa> update(int id, Pessoa pessoa) { return service.patchPessoa(id, pessoa); }

    @Override
    public Call<Pessoa> delete(int id) { return service.deletePessoa(id); }

    @Override
    public void fromJson(Call<Pessoa> obj) {
        obj.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                pessoa = response.body();
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                Log.e("PessoaService  ", "Erro ao buscar pessoa: " + t.getMessage());
            }
        });
    }
}
