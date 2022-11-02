package com.example.a1agroservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1agroservice.R;
import com.example.a1agroservice.controllers.PessoaController;
import com.example.a1agroservice.funcoesvalidacao.FuncoesPadrao;
import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.singleton.Login;

public class CadastroActivity extends AppCompatActivity {
    private EditText edNome;
    private EditText edWhatsapp;
    private EditText edCpf;
    private EditText edUsuario;
    private EditText edSenha;
    private PessoaController pessoaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iniciaComponentes();
        pessoaController = new PessoaController(this);
    }

    public void btVoltarOnClick(View view) {
        abrirLoginPage();
    }

    public void btSalvarOnClick(View view) {

        if (FuncoesPadrao.validaCPF(edCpf.getText().toString()) == false) {
            edCpf.setError("CPF INVÁLIDO");
            return;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(edNome.getText().toString());
        pessoa.setCpf(edCpf.getText().toString());
        pessoa.setUsuario(edUsuario.getText().toString());
        pessoa.setSenha(edSenha.getText().toString());
        pessoa.setCelular(edWhatsapp.getText().toString());

        pessoaController.savePessoa(pessoa);
        limpaCampos();
        abrirLoginPage();
    }

    public void abrirLoginPage() {
        Login.getUsuarioLogado(edUsuario.getText().toString(), edSenha.getText().toString());

        Intent loginPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(loginPage);
    }

    public void abrirHomePage() {
//        Intent homePage = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(homePage);
    }

    private void iniciaComponentes() {
        edNome = findViewById(R.id.edNome);
        edWhatsapp = findViewById(R.id.edWhatsapp);
        edCpf = findViewById(R.id.edCpf);
        edUsuario = findViewById(R.id.edUsuario);
        edSenha = findViewById(R.id.edSenha);
    }

    private void limpaCampos() {
        edNome.setText("");
        edWhatsapp.setText("");
        edCpf.setText("");
        edUsuario.setText("");
        edSenha.setText("");
    }
}