package com.example.a1agroservice.activities;

import  android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1agroservice.R;
import com.example.a1agroservice.controllers.PessoaController;
import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.singleton.Login;

public class MainActivity extends AppCompatActivity {
    private EditText edUsuario;
    private EditText edSenha;
    private PessoaController pessoaController;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pessoaController = new PessoaController(this);

        importarComponentes();
    }

    public void btCadastrarOnClick(View view) {
        Intent cadastroPage = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(cadastroPage);
    }

    public void btEntrarOnClick(View view) {
        if (checkAllFields()) {
            Login.getUsuarioLogado(edUsuario.getText().toString().trim(), edSenha.getText().toString());

            Intent homePage = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(homePage);
        }
    }

    private boolean checkAllFields() {
        Pessoa pessoa = pessoaController.getPessoaByUsername(edUsuario.getText().toString().trim());
        if (pessoa != null) {
            if (edSenha.getText().toString().equals(pessoa.getSenha())) {
                Login.getUsuarioLogado(edUsuario.getText().toString().trim(), edSenha.getText().toString());
                Toast.makeText(this, "Bem-vindo " + pessoa.getNome(), Toast.LENGTH_SHORT).show();
                return true;
            } else {
                edSenha.setError("Senha incorreta!");
                return false;
            }
        } else {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void importarComponentes() {
        edUsuario = findViewById(R.id.edUsuario);
        edSenha = findViewById(R.id.edSenha);
    }

    private void limpaCampos() {
        edUsuario.setText("");
        edSenha.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.opcion1) {
            Toast.makeText(this, "OPÇÃO", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.opcion2) {
            Toast.makeText(this, "OPÇÃO", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.pessoa) {
            Toast.makeText(this, "PESSOA", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.buscar) {
            Toast.makeText(this, "BUSCAR", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}