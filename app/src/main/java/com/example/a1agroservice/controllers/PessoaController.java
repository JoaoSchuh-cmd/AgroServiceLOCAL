package com.example.a1agroservice.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.service.PessoaService;

import java.util.ArrayList;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PessoaController extends GenericController<Pessoa> {
    private Context contexto;
    private static PessoaController pessoaController;
    private PessoaService service;

    private Pessoa pessoa;
    ArrayList<Pessoa> pessoas = new ArrayList();

    public PessoaController() {
        configInicial();
    }

    public static PessoaController getInstance() {
        return pessoaController == null ? pessoaController = new PessoaController() : pessoaController;
    }

    public int retornaProximoId() {
        service.getPessoas().enqueue(new Callback<ArrayList<Pessoa>>() {
            @Override
            public void onResponse(Call<ArrayList<Pessoa>> call, Response<ArrayList<Pessoa>> response) {
                pessoas = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Pessoa>> call, Throwable t) {

            }
        });
        return pessoas.get(pessoas.size() - 1).getId() + 1;
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
                Log.e("PessoaService  ", "Erro ao buscar pessoa pelo username: " + t.getMessage());
            }
        });
        return pessoa.getSenha() == senhaInformada;
    }

    @Override
    public void configInicial() {
        pessoa = new Pessoa();
        pessoas = new ArrayList();
        service = retrofit.getPessoaService();
    }

    public Pessoa getByUsuario(String usuario) {
        service.getPessoa(usuario).enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if (response.isSuccessful())
                    pessoa = response.body();
                else {
                    Log.e("FromJson ", "Erro ao salvar body: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<Pessoa> call, Throwable t)  {
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
    public ArrayList<Pessoa> getAll() {

        service.getPessoas().enqueue(new Callback<ArrayList<Pessoa>>() {
            @Override
            public void onResponse(Call<ArrayList<Pessoa>> call, Response<ArrayList<Pessoa>> response) {
                if (response.isSuccessful())
                    pessoas = response.body();
                else {
                    pessoas = null;
                    Log.e("FromJson ", "Erro ao salvar body: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Pessoa>> call, Throwable t)  {
                Log.e("PessoaService  ", "Erro ao buscar pessoa: " + t.getMessage());
            }
        });
        return null;
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
