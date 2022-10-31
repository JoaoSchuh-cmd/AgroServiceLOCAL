package com.example.a1agroservice.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a1agroservice.helper.SQLiteDataHelper;
import com.example.a1agroservice.models.Servico;

import java.util.ArrayList;

public class ServicoDao implements GenericDao<Servico> {
    //Abre a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase db;

    //nome das colunas da tabela
    private String[]colunas = {"ID", "DESCRICAO", "DATA_INICIO", "DATA_FIM", "ID_TIPO_SERVICO"};

    //Nome da Tabela
    private String tableName = "SERVICO";

    //Contexto no qual o DAO foi chamado
    private Context context;

    private static ServicoDao instancia;

    public static ServicoDao getInstancia(Context context){
        return (instancia == null ? new ServicoDao(context) : instancia);
    }

    //Construtor
    private ServicoDao(Context context) {
        this.context = context;

        //Abrir a conexão com BD
        openHelper = new SQLiteDataHelper(this.context,
                "AGROSERVICE", null, 1);

        db = openHelper.getWritableDatabase();
    }

    @Override
    public boolean insert(Servico obj) {
        ContentValues valores = new ContentValues();
        valores.put("DESCRICAO", obj.getDescricao());
        valores.put("DATA_INICIO", String.valueOf(obj.getData_inicio()));
        valores.put("DATA_FIM", String.valueOf(obj.getData_fim()));
        valores.put("ID_TIPO_SERVICO", obj.getId_tipo_servico());

        return db.insert(tableName, null, valores) == 1 ? true : false;
    }

    @Override
    public boolean update(Servico obj) {
        String[]identificador = {String.valueOf(obj.getId())};

        ContentValues valores = new ContentValues();
        valores.put("DESCRICAO", obj.getDescricao());
        valores.put("DATA_INICIO", String.valueOf(obj.getData_inicio()));
        valores.put("DATA_FIM", String.valueOf(obj.getData_fim()));
        valores.put("ID_TIPO_SERVICO", obj.getId_tipo_servico());

        return db.update(tableName, valores,
                "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public boolean delete(Servico obj) {
        String[]identificador = {String.valueOf(obj.getId())};

        ContentValues valores = new ContentValues();
        valores.put("DESCRICAO", obj.getDescricao());
        valores.put("DATA_INICIO", obj.getData_inicio());
        valores.put("DATA_FIM", obj.getData_fim());
        valores.put("ID_TIPO_SERVICO", obj.getId_tipo_servico());

        return db.delete(tableName, "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public ArrayList<Servico> getAll() {
        ArrayList<Servico> listaServico = new ArrayList<>();

        Cursor cursor = db.query(tableName, colunas,
                null, null, null, null,
                "ID asc");

        if(cursor.moveToFirst()){
            do{
                Servico Servico = new Servico();
                Servico.setId(cursor.getInt(0));
                Servico.setDescricao(cursor.getString(1));
                Servico.setData_inicio(cursor.getString(2));
                Servico.setData_fim(cursor.getString(3));
                Servico.setId_tipo_servico(cursor.getInt(4));

                listaServico.add(Servico);
            }while(cursor.moveToNext());
        }

        return listaServico;
    }

    @Override
    public Servico getById(int id) {
        Servico servico;

        String[] identificadores = {String.valueOf(id)};

        Cursor cursor = db.query(tableName, colunas,
                "ID = ?", identificadores, null, null,
                null);

        servico = null;
        if (cursor.getCount() > 0) {
            servico.setId(cursor.getInt(0));
            servico.setDescricao(cursor.getString(1));
            servico.setData_inicio(cursor.getString(2));
            servico.setData_fim(cursor.getString(3));
            servico.setId_tipo_servico(cursor.getInt(4));
        }

        return servico;
    }

}
