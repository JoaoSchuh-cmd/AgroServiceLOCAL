package com.example.a1agroservice.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    private String[]colunas = {"ID", "NOME", "CELULAR", "CPF", "USUARIO", "SENHA"};

    //Nome da Tabela
    private String tableName = "PESSOA";

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
        ContentValues valores = new ContentValues();
        valores.put("NOME", obj.getNome());
        valores.put("CELULAR", obj.getCelular());
        valores.put("CPF", obj.getCpf());
        valores.put("USUARIO", obj.getUsuario());
        valores.put("SENHA", obj.getSenha());

        return db.insert(tableName, null, valores) == 1 ? true : false;
    }

    @Override
    public boolean update(long oldPessoaId, Pessoa newPessoa) {
        String[]identificador = {String.valueOf(oldPessoaId)};

        ContentValues valores = new ContentValues();
        valores.put("NOME", newPessoa.getNome());
        valores.put("CELULAR", newPessoa.getCelular());
        valores.put("CPF", newPessoa.getCpf());
        valores.put("USUARIO", newPessoa.getUsuario());
        valores.put("SENHA", newPessoa.getSenha());

        return db.update(tableName, valores,
                "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public boolean delete(Pessoa obj) {
        String[]identificador = {String.valueOf(obj.getId())};

        ContentValues valores = new ContentValues();
        valores.put("NOME", obj.getNome());
        valores.put("CPF", obj.getCpf());
        valores.put("USUARIO", obj.getUsuario());
        valores.put("SENHA", obj.getSenha());
        valores.put("CELULAR", obj.getCelular());

        return db.delete(tableName, "ID = ?", identificador) == 1 ? true : false;
    }

    @Override
    public ArrayList<Pessoa> getAll() {
        ArrayList<Pessoa> listaPessoa = new ArrayList<>();

        Cursor cursor = db.query(tableName, colunas,
                null, null, null, null,
                "ID asc");
        if(cursor.moveToFirst()){
            do{
                Pessoa pessoa = new Pessoa();
                pessoa.setId(cursor.getInt(0));
                pessoa.setNome(cursor.getString(1));
                pessoa.setUsuario(cursor.getString(2));
                pessoa.setSenha(cursor.getString(3));
                pessoa.setCpf(cursor.getString(4));
                pessoa.setCelular(cursor.getString(5));

                listaPessoa.add(pessoa);
            }while(cursor.moveToNext());
        }

        return listaPessoa;
    }

    @Override
    public Pessoa getById(int id) {
        String[] identificadores = {String.valueOf(id)};

        Cursor cursor = db.query(tableName, colunas,
                "ID = ?", identificadores, null, null,
                null);

        Pessoa pessoa = new Pessoa();
        if (cursor.getCount() > 0) {
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setCelular(cursor.getString(22));
            pessoa.setCpf(cursor.getString(3));
            pessoa.setUsuario(cursor.getString(4));
            pessoa.setSenha(cursor.getString(5));
        }

        return pessoa;
    }

    public Pessoa getByUsername(String username) {
        String[] identificadores = {username};

        Cursor cursor = db.query(tableName, colunas,
                "USUARIO = ?", identificadores, null, null,
                null);

        Pessoa pessoa = new Pessoa();
        if (cursor.moveToFirst()) {
            pessoa.setId(cursor.getInt(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setCelular(cursor.getString(2));
            pessoa.setCpf(cursor.getString(3));
            pessoa.setUsuario(cursor.getString(4));
            pessoa.setSenha(cursor.getString(5));
        } else {
            pessoa = null;
        }

        return pessoa;
    }
}
