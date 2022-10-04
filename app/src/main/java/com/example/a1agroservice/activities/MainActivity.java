package com.example.a1agroservice.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1agroservice.R;
import com.example.a1agroservice.controllers.PessoaController;

public class MainActivity extends AppCompatActivity {
    private EditText edUsuario;
    private EditText edSenha;
    private PessoaController pessoaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pessoaController = new PessoaController();

        importarComponentes();
    }

    public void btCadastrarOnClick(View view) {
        Intent cadastroPage = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(cadastroPage);
    }

    public void btEntrarOnClick(View view) throws InterruptedException {
        if (edUsuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe um usuário!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pessoaController.getByUsuario(edUsuario.getText().toString().trim()) == null) {
            Toast.makeText(this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edSenha.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe a senha!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pessoaController.validaSenha(edUsuario.getText().toString(), edSenha.getText().toString())) {
            Toast.makeText(this, "Senha incorreta!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Bem-Vindo " + pessoaController.getByUsuario(edUsuario.getText().toString()).getNome(), Toast.LENGTH_SHORT).show();

        //TODO abrir Home Page
//        Intent homePage = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(homePage);
    }

    private void importarComponentes() {
        edUsuario = findViewById(R.id.edUsuario);
        edSenha = findViewById(R.id.edSenha);
    }

    private void limpaCampos() {
        edUsuario.setText("");
        edSenha.setText("");
    }
}