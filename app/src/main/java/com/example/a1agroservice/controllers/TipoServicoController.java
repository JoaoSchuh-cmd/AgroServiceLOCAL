package com.example.a1agroservice.controllers;

import android.content.Context;

import com.example.a1agroservice.dao.TipoServicoDao;
import com.example.a1agroservice.models.TipoServico;

import java.util.ArrayList;

public class TipoServicoController {
    private static TipoServicoController controller;
    private Context context;

    public TipoServicoController(Context context) {
        this.context = context;
    }

    public static TipoServicoController getInstance(Context context) {
        if (controller == null)
            controller = new TipoServicoController(context);
        return controller;
    }

    public TipoServico getTipoServicoById(long id) {
        return TipoServicoDao.getInstancia(context).getById(id);
    }

    public ArrayList<TipoServico> getServicos() {
        return TipoServicoDao.getInstancia(context).getAll();
    }

    public boolean saveTipoServico(TipoServico tipoServico) {
        return TipoServicoDao.getInstancia(context).insert(tipoServico);
    }

    public boolean deleteTipoServico(TipoServico tipoServico) {
        return TipoServicoDao.getInstancia(context).delete(tipoServico);
    }
}
