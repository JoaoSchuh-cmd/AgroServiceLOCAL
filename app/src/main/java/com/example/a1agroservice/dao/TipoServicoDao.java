package com.example.a1agroservice.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a1agroservice.helper.SQLiteDataHelper;
import com.example.a1agroservice.models.TipoServico;

import java.util.ArrayList;

public class TipoServicoDao implements GenericDao<TipoServico> {
    //Abre a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase db;

    //nome das colunas da tabela
    private String[]colunas = {"ID", "NOME"};

    //Nome da Tabela
    private String tableName = "TIPO_SERVICO";

    //Contexto no qual o DAO foi chamado
    private Context context;

    private static TipoServicoDao instancia;

    public static TipoServicoDao getInstancia(Context context){
        return (instancia == null ? new TipoServicoDao(context) : instancia);
    }

    //Construtor
    private TipoServicoDao(Context context) {
        this.context = context;

        //Abrir a conexão com BD
        openHelper = new SQLiteDataHelper(this.context,
                "AGROSERVICE", null, 1);

        db = openHelper.getWritableDatabase();
    }

    @Override
    public boolean insert(TipoServico obj) {
        ContentValues valores = new ContentValues();
        valores.put("NOME", obj.getNome());

        return db.insert(tableName, null, valores) == 1 ? true : false;
    }

    @Override
    public boolean update(TipoServico obj) {
        String[]identificador = {String.valueOf(obj.getId())};

        ContentValues valores = new ContentValues();
        valores.put("NOME", obj.getNome());

        return db.update(tableName, valores,
                "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public boolean delete(TipoServico obj) {
        String[]identificador = {String.valueOf(obj.getId())};

        ContentValues valores = new ContentValues();
        valores.put("NOME", obj.getNome());

        return db.delete(tableName, "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public ArrayList<TipoServico> getAll() {
        ArrayList<TipoServico> listaTipoServico = new ArrayList<>();

        Cursor cursor = db.query(tableName, colunas,
                null, null, null, null,
                "ID asc");
        if(cursor.moveToFirst()){
            do{
                TipoServico TipoServico = new TipoServico();
                TipoServico.setId(cursor.getInt(0));
                TipoServico.setNome(cursor.getString(1));

                listaTipoServico.add(TipoServico);
            }while(cursor.moveToNext());
        }

        return listaTipoServico;
    }

    @Override
    public TipoServico getById(int id) {


        String[] identificadores = {String.valueOf(id)};

        Cursor cursor = db.query(tableName, colunas,
                "ID = ?", identificadores, null, null,
                null);

        TipoServico tipoServico = new TipoServico();
        if (cursor.getCount() > 0) {
            tipoServico.setId(cursor.getInt(0));
            tipoServico.setNome(cursor.getString(1));
        }

        return tipoServico;
    }
}
