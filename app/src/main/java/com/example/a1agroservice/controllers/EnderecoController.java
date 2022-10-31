package com.example.a1agroservice.controllers;

import android.content.Context;

import com.example.a1agroservice.dao.EnderecoDao;
import com.example.a1agroservice.models.Endereco;

import java.util.ArrayList;

public class EnderecoController {
    private static EnderecoController controller;
    private Context context;

    public EnderecoController(Context context) {
        this.context = context;
    }

    public static EnderecoController getInstance(Context context) {
        return controller == null ? new EnderecoController(context) : controller;
    }

    public Endereco getEnderecoById(int id) {
        return EnderecoDao.getInstancia(context).getById(id);
    }

    public ArrayList<Endereco> getEnderecos() {
        return EnderecoDao.getInstancia(context).getAll();
    }

    public boolean saveEndereco(Endereco endereco) {
        return EnderecoDao.getInstancia(context).insert(endereco);
    }

    public boolean deleteEndereco(Endereco endereco) {
        return EnderecoDao.getInstancia(context).delete(endereco);
    }
}
