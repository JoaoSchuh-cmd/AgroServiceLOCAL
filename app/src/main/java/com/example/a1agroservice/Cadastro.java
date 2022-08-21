package com.example.a1agroservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a1agroservice.bufferedreader.Conexao;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void btVoltarOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void btSalvarOnCick(View view) {
        Tarefa tarefa = new Tarefa();
        tarefa.execute("http://10.0.2.2:27017/pessoa");
    }

    private class Tarefa extends AsyncTask<String, String, String> {

        public Tarefa() {
            super();
        }

        @Override
        protected String doInBackground(String... strings) {
            return Conexao.getDados(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(Cadastro.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}