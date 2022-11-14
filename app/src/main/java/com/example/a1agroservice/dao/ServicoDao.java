package com.example.a1agroservice.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.a1agroservice.helper.SQLiteDataHelper;
import com.example.a1agroservice.models.Servico;

import java.util.ArrayList;

public class ServicoDao implements GenericDao<Servico> {
    //Abre a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase db;

    //nome das colunas da tabela
    private String[]colunas = {"ID", "DESCRICAO", "DATA_INICIO", "DATA_FIM", "ID_TIPO_SERVICO", "VALOR_HORA"};

    //Nome da Tabela
    private String tableName = "SERVICO";

    //Contexto no qual o DAO foi chamado
    private Context context;

    private static ServicoDao instancia;

    public static ServicoDao getInstancia(Context context){
        if (instancia == null)
            instancia = new ServicoDao(context);
        return instancia;
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
        valores.put("VALOR_HORA", obj.getValorhora());

        return db.insert(tableName, null, valores) == 1 ? true : false;
    }

    @Override
    public boolean update(long oldServicoId, Servico newServico) {
        String[]identificador = {String.valueOf(oldServicoId)};

        ContentValues valores = new ContentValues();
        valores.put("DESCRICAO", newServico.getDescricao());
        valores.put("DATA_INICIO", String.valueOf(newServico.getData_inicio()));
        valores.put("DATA_FIM", String.valueOf(newServico.getData_fim()));
        valores.put("ID_TIPO_SERVICO", newServico.getId_tipo_servico());
        valores.put("VALOR_HORA", newServico.getValorhora());

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
        valores.put("VALOR_HORA", obj.getValorhora());

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
                Servico servico = new Servico();
                servico.setId(cursor.getInt(0));
                servico.setDescricao(cursor.getString(1));
                servico.setData_inicio(cursor.getString(2));
                servico.setData_fim(cursor.getString(3));
                servico.setId_tipo_servico(cursor.getInt(4));
                servico.setValorhora(cursor.getDouble(5));

                listaServico.add(servico);
            }while(cursor.moveToNext());
        }

        return listaServico;
    }

    @Override
    public Servico getById(long id) {
        Servico servico = new Servico();

        String[] identificadores = {String.valueOf(id)};

        Cursor cursor = db.query(tableName, colunas,
                "ID = ?", identificadores, null, null,
                null);

        if (cursor.moveToFirst()) {
            servico.setId(cursor.getLong(0));
            servico.setDescricao(cursor.getString(1));
            servico.setData_inicio(cursor.getString(2));
            servico.setData_fim(cursor.getString(3));
            servico.setId_tipo_servico(cursor.getInt(4));
            servico.setValorhora(cursor.getDouble(5));
        } else {
            servico = null;
        }

        return servico;
    }

    public Servico getLastServico() {
        Servico servico = new Servico();

        Cursor cursor =
                db.query(tableName, colunas, null, null, null, null, "ID DESC");

        if (cursor.moveToFirst()) {
            try {
                servico.setId(cursor.getLong(0));
                servico.setDescricao(cursor.getString(1));
                servico.setData_inicio(cursor.getString(2));
                servico.setData_fim(cursor.getString(3));
                servico.setId_tipo_servico(cursor.getInt(4));
                servico.setValorhora(cursor.getDouble(5));
            } catch (Exception E) {
                Toast.makeText(context, "Erro ao carregar último serviço cadastrado", Toast.LENGTH_SHORT).show();
                Log.e("GetLastSerice", E.getMessage());
            }

        } else {
            servico = null;
        }

        return servico;
    }

}
