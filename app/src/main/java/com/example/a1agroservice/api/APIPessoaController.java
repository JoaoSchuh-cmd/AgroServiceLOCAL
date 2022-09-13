package com.example.a1agroservice.api;

import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.retrofit.ConfRetrofit;
import com.example.a1agroservice.service.PessoaService;

import retrofit2.Call;

public class APIPessoaController {
    private static APIPessoaController APIPessoaController;
    private PessoaService service;

    private APIPessoaController() {
        configInicial();
    }

    public static APIPessoaController getInstancia() {
        if (APIPessoaController == null)
            APIPessoaController = new APIPessoaController();
        return APIPessoaController;
    }

    public void configInicial() {
        service = ConfRetrofit.getRetrofitInstance().create(PessoaService.class);
    }

    public Call<Pessoa> retornarPessoa(int codigoPessoa) {return  service.getPessoa(codigoPessoa);}

    public Call<Pessoa> retornarPessoas() {return service.getPessoas();}

    public Call<Pessoa> addPessoa(Pessoa pessoa) {return service.postPessoa(pessoa);}

    public Call<Pessoa> atualizarPessoa(int id, Pessoa pessoa) {return service.patchPessoa(id, pessoa);}

    public Call<Pessoa> deletarPessoa(int id) {return service.deletePessoa(id);}
}
