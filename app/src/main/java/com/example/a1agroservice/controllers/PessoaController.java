package com.example.a1agroservice.controllers;

import com.example.a1agroservice.api.APIPessoaController;
import com.example.a1agroservice.models.Pessoa;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;

public class PessoaController {
    private Pessoa pessoa;
    @SerializedName("pessoas")
    @Expose
    private ArrayList<Pessoa> pessoas = new ArrayList();

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        Call<Pessoa> pessoasCall = APIPessoaController.getInstancia().retornarPessoas();
    }
}
