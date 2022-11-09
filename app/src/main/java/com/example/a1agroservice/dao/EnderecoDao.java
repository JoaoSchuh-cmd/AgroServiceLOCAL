package com.example.a1agroservice.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a1agroservice.helper.SQLiteDataHelper;
import com.example.a1agroservice.models.Endereco;

import java.util.ArrayList;

public class EnderecoDao implements GenericDao<Endereco> {
    //Abre a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase db;

    //nome das colunas da tabela
    private String[]colunas = {"ID", "CIDADE", "ESTADO", "CEP"};

    //Nome da Tabela
    private String tableName = "ENDERECO";

    //Contexto no qual o DAO foi chamado
    private Context context;

    private static EnderecoDao instancia;

    public static EnderecoDao getInstancia(Context context){
        return (instancia == null ? new EnderecoDao(context) : instancia);
    }

    //Construtor
    private EnderecoDao(Context context) {
        this.context = context;

        //Abrir a conexão com BD
        openHelper = new SQLiteDataHelper(this.context,
                "AGROSERVICE", null, 1);

        db = openHelper.getWritableDatabase();
    }

    @Override
    public boolean insert(Endereco obj) {
        ContentValues valores = new ContentValues();
        valores.put("CIDADE", obj.getCidade());
        valores.put("ESTADO", obj.getEstado());
        valores.put("CEP", obj.getCep());

        return db.insert(tableName, null, valores) == 1 ? true : false;
    }

    @Override
    public boolean update(long oldEnderecoId, Endereco newEndereco) {
        String[]identificador = {String.valueOf(oldEnderecoId)};

        ContentValues valores = new ContentValues();
        valores.put("CIDADE", newEndereco.getCidade());
        valores.put("ESTADO", newEndereco.getEstado());
        valores.put("CEP", newEndereco.getCep());

        return db.update(tableName, valores,
                "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public boolean delete(Endereco obj) {
        String[]identificador = {String.valueOf(obj.getId())};

        ContentValues valores = new ContentValues();
        valores.put("CIDADE", obj.getCidade());
        valores.put("ESTADO", obj.getEstado());
        valores.put("CEP", obj.getCep());

        return db.delete(tableName, "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public ArrayList<Endereco> getAll() {
        ArrayList<Endereco> listaEndereco = new ArrayList<>();

        Cursor cursor = db.query(tableName, colunas,
                null, null, null, null,
                "ID asc");

        if(cursor.moveToFirst()){
            do{
                Endereco endereco = new Endereco();
                endereco.setId(cursor.getInt(0));
                endereco.setCidade(cursor.getString(1));
                endereco.setEstado(cursor.getString(2));
                endereco.setCep(cursor.getString(3));

                listaEndereco.add(endereco);
            }while(cursor.moveToNext());
        }

        return listaEndereco;
    }

    @Override
    public Endereco getById(int id) {
        String[] identificadores = {String.valueOf(id)};

        Cursor cursor = db.query(tableName, colunas,
                "USUARIO = ?", identificadores, null, null,
                null);

        Endereco endereco = new Endereco();
        if (cursor.getCount() > 0) {
            endereco.setId(cursor.getInt(0));
            endereco.setCidade(cursor.getString(1));
            endereco.setEstado(cursor.getString(2));
            endereco.setCep(cursor.getString(3));
        }

        return endereco;
    }
}
