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
        return controller == null ? new AnuncioController(context) : controller;
    }

    public Anuncio getAnuncioById(long id) {
        return AnuncioDao.getInstancia(context).getById(id);
    }

    public ArrayList<Anuncio> getAnuncios() {
        return AnuncioDao.getInstancia(context).getAll();
    }

    public boolean saveAnuncio(Anuncio anuncio) {
        return AnuncioDao.getInstancia(context).insert(anuncio);
    }

    public boolean deleteAnuncio(Anuncio anuncio) {
        return AnuncioDao.getInstancia(context).delete(anuncio);
    }
}
