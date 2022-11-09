package com.example.a1agroservice.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a1agroservice.helper.SQLiteDataHelper;
import com.example.a1agroservice.models.Anuncio;

import java.util.ArrayList;

public class AnuncioDao implements GenericDao<Anuncio> {

    //Abre a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase db;

    //nome das colunas da tabela
    private String[]colunas = {"ID", "ID_PESSOA", "ID_SERVICO", "ID_ENDERECO"};

    //Nome da Tabela
    private String tableName = "ANUNCIO";

    //Contexto no qual o DAO foi chamado
    private Context context;

    private static AnuncioDao instancia;

    public static AnuncioDao getInstancia(Context context){
        return (instancia == null ? new AnuncioDao(context) : instancia);
    }

    //Construtor
    private AnuncioDao(Context context) {
        this.context = context;

        //Abrir a conexão com BD
        openHelper = new SQLiteDataHelper(this.context,
                "AGROSERVICE", null, 1);

        db = openHelper.getWritableDatabase();
    }

    @Override
    public boolean insert(Anuncio obj) {
        ContentValues values = new ContentValues();
        values.put("ID", obj.getId());
        values.put("ID_PESSOA", obj.getId_pessoa());
        values.put("ID_SERVICO", obj.getId_servico());
        values.put("ID_ENDERECO", obj.getId_endereco());

        return db.insert(tableName, null, values) == 1 ? true : false;
    }

    @Override
    public boolean update(long oldAnuncioId, Anuncio newAnuncio) {
        String[]identificador = {String.valueOf(oldAnuncioId)};

        ContentValues values = new ContentValues();
        values.put("ID", newAnuncio.getId());
        values.put("ID_PESSOA", newAnuncio.getId_pessoa());
        values.put("ID_SERVICO", newAnuncio.getId_servico());
        values.put("ID_ENDERECO", newAnuncio.getId_endereco());

        return db.update(tableName, values, "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public boolean delete(Anuncio obj) {
        String[]identificador = {String.valueOf(obj.getId())};

        ContentValues values = new ContentValues();
        values.put("ID", obj.getId());
        values.put("ID_PESSOA", obj.getId_pessoa());
        values.put("ID_SERVICO", obj.getId_servico());
        values.put("ID_ENDERECO", obj.getId_endereco());

        return db.delete(tableName, "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public ArrayList<Anuncio> getAll() {
        ArrayList<Anuncio> listaAnuncio = new ArrayList<>();

        Cursor cursor = db.query(tableName, colunas,
                null, null, null, null,
                "ID asc");
        if(cursor.moveToFirst()){
            do{
                Anuncio anuncio = new Anuncio();
                anuncio.setId(cursor.getInt(0));
                anuncio.setId_pessoa(cursor.getInt(1));
                anuncio.setId_servico(cursor.getInt(2));
                anuncio.setId_endereco(cursor.getInt(3));

                listaAnuncio.add(anuncio);
            }while(cursor.moveToNext());
        }

        return listaAnuncio;
    }

    @Override
    public Anuncio getById(int id) {
        String[] identificadores = {String.valueOf(id)};

        Cursor cursor = db.query(tableName, colunas,
                "ID = ?", identificadores, null, null,
                "ID asc");

        Anuncio anuncio = new Anuncio();
        if(cursor.getCount() > 0) {
            anuncio.setId(cursor.getInt(0));
            anuncio.setId_pessoa(cursor.getInt(1));
            anuncio.setId_servico(cursor.getInt(2));
            anuncio.setId_endereco(cursor.getInt(3));
        }

        return anuncio;
    }
}
