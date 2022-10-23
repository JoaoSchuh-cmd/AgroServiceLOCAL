package com.example.a1agroservice.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.a1agroservice.helper.SQLiteDataHelper;
import com.example.a1agroservice.models.Pessoa;

import java.util.ArrayList;

public class PessoaDao implements GenericDao<Pessoa> {
    //Abre a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase db;

    //nome das colunas da tabela
    private String[]colunas = {"RA_ALUNO", "NOME_ALUNO"};

    //Nome da Tabela
    private String tableName = "ALUNO";

    //Contexto no qual o DAO foi chamado
    private Context context;

    private static PessoaDao instancia;

    public static PessoaDao getInstancia(Context context){
        return (instancia == null ? new PessoaDao(context) : instancia);
    }

    //Construtor
    private PessoaDao(Context context) {
        this.context = context;

        //Abrir a conexão com BD
        openHelper = new SQLiteDataHelper(this.context,
                "AGROSERVICE", null, 1);

        db = openHelper.getWritableDatabase();
    }

    @Override
    public boolean insert(Pessoa obj) {
        return false;
    }

    @Override
    public boolean update(Pessoa obj) {
        return false;
    }

    @Override
    public boolean delete(Pessoa obj) {
        return false;
    }

    @Override
    public ArrayList<Pessoa> getAll() {
        return null;
    }

    @Override
    public Pessoa getById(int id) {
        return null;
    }
}
