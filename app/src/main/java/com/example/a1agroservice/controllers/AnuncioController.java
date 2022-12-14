package com.example.a1agroservice.controllers;

import android.content.Context;

import com.example.a1agroservice.dao.AnuncioDao;
import com.example.a1agroservice.models.Anuncio;

import java.util.ArrayList;

public class AnuncioController {
    private static AnuncioController controller;
    private Context context;

    public AnuncioController(Context context) {
        this.context = context;
    }

    public static AnuncioController getInstance(Context context) {
        if (controller == null)
            controller = new AnuncioController(context);
        return controller;
    }

    public Anuncio getAnuncioById(long id) {
        return AnuncioDao.getInstancia(context).getById(id);
    }

    public ArrayList<Anuncio> getAnunciosByUserId(long id) { return AnuncioDao.getInstancia(context).getByUserId(id); }

    public ArrayList<Anuncio> getAnuncios() {
        return AnuncioDao.getInstancia(context).getAll();
    }

    public boolean saveAnuncio(Anuncio anuncio) {
        return AnuncioDao.getInstancia(context).insert(anuncio);
    }

    public boolean updateAnuncio(Anuncio oldA, Anuncio newA) {
        return AnuncioDao.getInstancia(context).update(oldA.getId(), newA);
    }

    public boolean deleteAnuncio(Anuncio anuncio) {
        return AnuncioDao.getInstancia(context).delete(anuncio);
    }
}
