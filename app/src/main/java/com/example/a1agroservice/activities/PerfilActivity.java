package com.example.a1agroservice.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1agroservice.R;
import com.example.a1agroservice.controllers.PessoaController;
import com.example.a1agroservice.models.Pessoa;
import com.example.a1agroservice.singleton.Login;

public class PerfilActivity extends AppCompatActivity {

    private PessoaController pessoaController;

    private EditText edNome;
    private com.santalu.maskara.widget.MaskEditText edWhatsapp;
    private com.santalu.maskara.widget.MaskEditText edCpf;
    private EditText edUsuario;
    private EditText edSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        edNome = findViewById(R.id.edNome);
        edWhatsapp = findViewById(R.id.edWhatsapp);
        edCpf = findViewById(R.id.edCpf);
        edUsuario = findViewById(R.id.edUsuario);
        edSenha = findViewById(R.id.edSenha);

        pessoaController = new PessoaController(this);

        carregaInformacoesUsuario();
    }

    private void carregaInformacoesUsuario(){
        try {
            Pessoa pessoa = pessoaController.getPessoaByUsername(Login.getUsuarioLogado().getUsuario());

            edNome.setText(pessoa.getNome());
            edWhatsapp.setText(pessoa.getCelular());
            edCpf.setText(pessoa.getCpf());
            edUsuario.setText(pessoa.getUsuario());
            edSenha.setText(pessoa.getSenha());
        } catch (Exception E) {
            Toast.makeText(this, "Erro ao carregar informações do usuário!", Toast.LENGTH_SHORT).show();
            Log.e("LoadUserInformation:", E.getMessage());
        }
    }

    public void btSalvarOnCick(View view) {
        try {
            Pessoa oldPessoa = pessoaController.getPessoaByUsername(Login.getUsuarioLogado().getUsuario());

            Pessoa newPessoa = new Pessoa();
            newPessoa.setNome(edNome.getText().toString());
            newPessoa.setCelular(edWhatsapp.getText().toString());
            newPessoa.setCpf(edCpf.getText().toString());
            newPessoa.setUsuario(edUsuario.getText().toString());
            newPessoa.setSenha(edSenha.getText().toString());

            pessoaController.updatePessoa(oldPessoa, newPessoa);
            finish();
        } catch (Exception E) {
            Toast.makeText(this, "Erro ao atualizar usuário!", Toast.LENGTH_SHORT).show();
            Log.e("UserUpdate:", E.getMessage());
        }
    }

    public void btVoltarOnClick(View view) {
        Intent homePage = new Intent(this, HomeActivity.class);
        startActivity(homePage);
        finish();
    }

    public void btRemoverClick(View view) {
        try {
            Pessoa pessoa = pessoaController.getPessoaByUsername(Login.getUsuarioLogado().getUsuario());
            pessoaController.deletePessoa(pessoa);

            Intent loginPage = new Intent(this, MainActivity.class);
            startActivity(loginPage);
            onDestroy();
        } catch (Exception E) {
            Toast.makeText(this, "Erro ao excluir usuário!", Toast.LENGTH_SHORT).show();
            Log.e("DeleteUser:", E.getMessage());
        }
    }
}