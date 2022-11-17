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
        if (controller == null)
            controller = new PessoaController(context);
        return controller;
    }

    public Pessoa getPessoaByUsername(String username) {
        return PessoaDao.getInstancia(context).getByUsername(username);
    }

    public Pessoa getPessoaById(long id) {
        return PessoaDao.getInstancia(context).getById(id);
    }

    public ArrayList<Pessoa> getPessoas() {
       return PessoaDao.getInstancia(context).getAll();
    }

    public boolean savePessoa(Pessoa pessoa) {
        return PessoaDao.getInstancia(context).insert(pessoa);
    }

    public boolean updatePessoa(Pessoa oldP, Pessoa newP) {
        return PessoaDao.getInstancia(context).update(oldP.getId(), newP);
    }

    public boolean deletePessoa(Pessoa pessoa) {
        return PessoaDao.getInstancia(context).delete(pessoa);
    }

    public boolean cpfAlreadyRegistered(String cpf) {
        return PessoaDao.getInstancia(context).getCpf(cpf);
    }

    public boolean celularAlreadyRegistered(String celular) {
        return PessoaDao.getInstancia(context).getCelular(celular);
    }

}
