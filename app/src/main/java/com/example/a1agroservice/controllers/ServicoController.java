package com.example.a1agroservice.controllers;

import android.content.Context;

import com.example.a1agroservice.dao.ServicoDao;
import com.example.a1agroservice.models.Servico;

import java.util.ArrayList;

public class ServicoController {
    private static ServicoController controller;
    private Context context;

    public ServicoController(Context context) {
        this.context = context;
    }

    public static ServicoController getInstance(Context context) {
        if (controller == null)
            controller = new ServicoController(context);
        return controller;
    }

    public Servico getServicoById(long id) {
        return ServicoDao.getInstancia(context).getById(id);
    }

    public ArrayList<Servico> getServicos() {
        return ServicoDao.getInstancia(context).getAll();
    }

    public boolean saveServico(Servico Servico) {
        return ServicoDao.getInstancia(context).insert(Servico);
    }

    public Servico getLastServico() {
        return ServicoDao.getInstancia(context).getLastServico();
    }

    public boolean deleteServico(Servico Servico) {
        return ServicoDao.getInstancia(context).delete(Servico);
    }
}
