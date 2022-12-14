package com.example.a1agroservice.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

    private ImageButton btAnuncio;
    private ImageButton btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        edNome = findViewById(R.id.edNome);
        edWhatsapp = findViewById(R.id.edWhatsapp);
        edCpf = findViewById(R.id.edCpf);
        edUsuario = findViewById(R.id.edUsuario);
        edSenha = findViewById(R.id.edSenha);

        btAnuncio = findViewById(R.id.btAnuncios);
        btHome = findViewById(R.id.btHome);

        pessoaController = PessoaController.getInstance(this);

        btAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anunciosPage = new Intent(getApplicationContext(), AnunciosActivity.class);
                startActivity(anunciosPage);
                finish();
            }
        });

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homePage = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(homePage);
                finish();
            }
        });

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
            Toast.makeText(this, "Erro ao carregar informa????es do usu??rio!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Erro ao atualizar usu??rio!", Toast.LENGTH_SHORT).show();
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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("Tem certeza que deseja excluir?")
                    .setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Pessoa pessoa = pessoaController.getPessoaByUsername(Login.getUsuarioLogado().getUsuario());
                            pessoaController.deletePessoa(pessoa);

                            Intent loginPage = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(loginPage);
                            finish();
                        }
                    })
                    .setPositiveButton("N??o", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButtonIcon(getDrawable(R.drawable.ic_cancel))
                    .setNegativeButtonIcon(getDrawable(R.drawable.ic_check))
                    .show();
        } catch (Exception E) {
            Toast.makeText(this, "Erro ao excluir usu??rio!", Toast.LENGTH_SHORT).show();
            Log.e("DeleteUser:", E.getMessage());
        }
    }
}