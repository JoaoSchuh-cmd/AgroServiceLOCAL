package com.example.a1agroservice.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {
    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE PESSOA (" +
                    "ID INTEGER PRIMARY KEY," +
                    "NOME VARCHAR(60) NOT NULL," +
                    "CPF VARCHAR(14) NOT NULL," +
                    "USUARIO VARCHAR(10) NOT NULL," +
                    "SENHA VARCHAR(20) NOT NULL," +
                    "CELULAR VARCHAR(20) NOT NULL" +
                ")");
        db.execSQL(
                "CREATE TABLE ANUNCIO (" +
                    "ID INTEGER PRIMARY KEY," +
                    "ID_PESSOA INTEGER NOT NULL," +
                    "ID_SERVICO INTEGER NOT NULL," +
                    "ID_ENDERECO INTEGER NOT NULL," +
                    "NOME_PROPRIETARIO VARCHAR(30) NOT NULL," +
                    "CELULAR VARCHAR(20) NOT NULL," +
                    "TIPO_PESSOA CHAR NOT NULL," +
                    "CONSTRAINT FK_ID_PESSOA FOREIGN KEY (ID_PESSOA)" +
                        "REFERENCES PESSOA (ID)," +
                    "CONSTRAINT FK_ID_SERVICO FOREIGN KEY (ID_SERVICO)" +
                        "REFERENCES PESSOA (ID)," +
                    "CONSTRAINT FK_ID_ENDERECO FOREIGN KEY (ID_ENDERECO)" +
                        "REFERENCES ENDERECO (ID)" +
                ")");
        db.execSQL(
                "CREATE TABLE ENDERECO (" +
                        "ID INTEGER PRIMARY KEY," +
                        "CIDADE VARCHAR(120) NOT NULL," +
                        "ESTADO VARCHAR(20) NOT NULL"+
                    ")");
        db.execSQL(
                "CREATE TABLE SERVICO (" +
                        "ID INTEGER PRIMARY KEY," +
                        "DESCRICAO VARCHAR(60)," +
                        "DATA_INICIO DATE NOT NULL," +
                        "DATA_FIM DATE NOT NULL," +
                        "VALOR_HORA DOUBLE NOT NULL," +
                        "ID_TIPO_SERVICO INTEGER NOT NULL," +
                        "CONSTRAINT FK_ID_TIPO_SERVICO FOREIGN KEY (ID_TIPO_SERVICO)" +
                            "REFERENCES TIPO_SERVICO (ID)" +
                    ")");
        db.execSQL(
                "CREATE TABLE TIPO_SERVICO (" +
                        "ID INTEGER PRIMARY KEY," +
                        "NOME VARCHAR(20)" +
                    ")");

        db.execSQL("INSERT INTO TIPO_SERVICO (NOME) VALUES " +
                "   ('Colheita')," +
                "   ('Plantio')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
