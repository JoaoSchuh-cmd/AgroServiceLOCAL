package com.example.a1agroservice.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.service.PessoaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PessoaController extends GenericController<Pessoa> {
    private static PessoaController pessoaController;
    private PessoaService service;

    private Pessoa pessoa;

    public PessoaController() {
        configInicial();
    }

    public static PessoaController getInstance() {
        return pessoaController == null ? pessoaController = new PessoaController() : pessoaController;
    }

    public int retornaProximoId() {
        int proximoId = 0;
        int idAnterior = 0;


        service.getPessoas().enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                pessoa = response.body();
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {

            }
        });
        return  + 1;
    }

    public boolean validaSenha(String usuario, String senhaInformada) {
        service.getPessoa(usuario).enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if (response.isSuccessful())
                    pessoa = response.body();
                else Log.e("FromJson ", "Erro ao salvar body: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                Log.e("PessoaService  ", "Erro ao buscar pessoa pelo nome: " + t.getMessage());
            }
        });
        return pessoa.getSenha() == senhaInformada;
    }

    @Override
    public void configInicial() {
        pessoa = new Pessoa();
        service = retrofit.getPessoaService();
    }

    public Pessoa getByUsuario(String usuario) {
        service.getPessoa(usuario).enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if (response.isSuccessful())
                    pessoa = response.body();
                else Log.e("FromJson ", "Erro ao salvar body: " + response.errorBody());
            }
            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                Log.e("PessoaService  ", "Erro ao buscar pessoa: " + t.getMessage());
            }
        });
        return pessoa;
    }

    @Override
    public Call<Pessoa> getById(int id) {
        return service.getPessoa(id);
    }

    @Override
    public Call<Pessoa> getAll() {
        return service.getPessoas();
    }

    @Override
    public void insert(Context context, Pessoa pessoa) {
        Call<Pessoa> obj = service.postPessoa(pessoa);

        obj.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                Toast.makeText(context, "Cadastro efetuado!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                Toast.makeText(context, "Erro ao efetuar cadastro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public Call<Pessoa> update(int id, Pessoa pessoa) { return service.patchPessoa(id, pessoa); }

    @Override
    public Call<Pessoa> delete(int id) { return service.deletePessoa(id); }
}
