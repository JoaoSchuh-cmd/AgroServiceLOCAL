package com.example.a1agroservice.controllers;

import android.content.Context;

import com.example.a1agroservice.dao.PessoaDao;
import com.example.a1agroservice.models.Pessoa;

import java.util.ArrayList;

public class PessoaController {
    private static PessoaController controller;
    private Context context;

    public PessoaController(Context context) {
        this.context = context;
    }

    public static PessoaController getInstance(Context context) {
        return controller == null ? new PessoaController(context) : controller;
    }

    public Pessoa getPessoaByUsername(String username) {
        return PessoaDao.getInstancia(context).getByUsername(username);
    }

    public Pessoa getPessoaById(int id) {
        return PessoaDao.getInstancia(context).getById(id);
    }

    public ArrayList<Pessoa> getPessoas() {
       return PessoaDao.getInstancia(context).getAll();
    }

    public boolean savePessoa(Pessoa pessoa) {
        return PessoaDao.getInstancia(context).insert(pessoa);
    }

    public boolean deletePessoa(Pessoa pessoa) {
        return PessoaDao.getInstancia(context).delete(pessoa);
    }

}
