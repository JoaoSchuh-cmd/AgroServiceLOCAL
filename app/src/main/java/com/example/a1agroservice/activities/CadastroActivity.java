package com.example.a1agroservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1agroservice.R;
import com.example.a1agroservice.controllers.PessoaController;
import com.example.a1agroservice.funcoesvalidacao.FuncoesPadrao;
import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.singleton.Login;

public class CadastroActivity extends AppCompatActivity {
    private EditText edNome;
    private EditText edWhatsapp;
    private com.santalu.maskara.widget.MaskEditText edCpf;
    private EditText edUsuario;
    private EditText edSenha;
    private PessoaController pessoaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iniciaComponentes();
        pessoaController = PessoaController.getInstance(this);
    }

    public void btVoltarOnClick(View view) {
        finish();
    }

    public void btSalvarOnClick(View view) {
        if (!validaCampos())
            return;

        if (FuncoesPadrao.validaCPF(edCpf.getUnMasked()) == false) {
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
        abrirHomePage();
        Toast.makeText(this, "Bem-vindo " + pessoa.getNome(), Toast.LENGTH_SHORT).show();
        finish();
    }

    public void abrirLoginPage() {
        Intent loginPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(loginPage);
    }

    public void abrirHomePage() {
        Login.getUsuarioLogado(edUsuario.getText().toString(), edSenha.getText().toString());

        Intent homePage = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homePage);
    }

    private boolean validaCampos() {
        if (!validaCamposVazios())
            return false;
        if (!validaInformacoesCampos())
            return false;
        return true;
    }

    private boolean validaInformacoesCampos() {
        boolean result = true;

        if (pessoaController.getPessoaByUsername(edUsuario.getText().toString()) != null) {
            edUsuario.setError("Usuário informado já foi cadastrado!");
            result = false;
        }
        //TODO CPF e celular tb devem ser únicos

        return result;
    }

    private boolean validaCamposVazios() {
        boolean result = true;
        String mensagem = "Campo obrigatório!";

        if (edNome.getText().toString().isEmpty()) {
            edNome.setError(mensagem);
            result = false;
        }
        if (edCpf.getText().toString().isEmpty()) {
            edCpf.setError(mensagem);
            result = false;
        }
        if (edWhatsapp.getText().toString().isEmpty()) {
            edWhatsapp.setText(mensagem);
            result = false;
        }
        if (edUsuario.getText().toString().isEmpty()) {
            edUsuario.setError(mensagem);
            result = false;
        }
        if (edSenha.getText().toString().isEmpty()) {
            edSenha.setError(mensagem);
            result = false;
        }

        return result;
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