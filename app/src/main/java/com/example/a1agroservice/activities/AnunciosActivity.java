package com.example.a1agroservice.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1agroservice.R;
import com.example.a1agroservice.adapters.AnuncioAdapter;
import com.example.a1agroservice.controllers.AnuncioController;
import com.example.a1agroservice.controllers.PessoaController;
import com.example.a1agroservice.fragments.AnunciosCadFragment;
import com.example.a1agroservice.models.Anuncio;
import com.example.a1agroservice.singleton.Login;

import java.util.ArrayList;

public class AnunciosActivity extends AppCompatActivity {

    private ImageButton btHome;
    private ImageButton btPerfil;

    private ArrayList<Anuncio> anuncios;
    private ListView lvListaAnuncios;
    private TextView tvListaVaziaMsg;
    private AnuncioController anuncioController;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios);

        btHome = findViewById(R.id.btHome);
        btPerfil = findViewById(R.id.btPerfil);

        lvListaAnuncios = findViewById(R.id.lvLista);
        tvListaVaziaMsg = findViewById(R.id.tvListaVaziaMsg);

        anuncioController = AnuncioController.getInstance(this);

        swipeRefreshLayout = findViewById(R.id.swipeLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                carregaAnuncios();
                swipeRefreshLayout.setRefreshing(false);
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

        btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfilPage = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(perfilPage);
                finish();
            }
        });

        carregaAnuncios();
    }

    private void carregaAnuncios() {
        atualizaListaAnuncios();
        validaMensagemCentro();
    }

    private void validaMensagemCentro() {
        if (anuncios.isEmpty()) {
            tvListaVaziaMsg.setVisibility(View.VISIBLE);
            lvListaAnuncios.setVisibility(View.GONE);
        }
        else {
            tvListaVaziaMsg.setVisibility(View.GONE);
            lvListaAnuncios.setVisibility(View.VISIBLE);
        }
    }

    private void atualizaListaAnuncios() {
        anuncios = new ArrayList<>();
        anuncios = anuncioController.getAnunciosByUserId(
                PessoaController.getInstance(this).getPessoaByUsername(Login.getUsuarioLogado().getUsuario()).getId());
        AnuncioAdapter adapter = new AnuncioAdapter(
                anuncios, this, this, "SEUS_ANUNCIOS");

        lvListaAnuncios.setAdapter(adapter);
    }

    public void btCriarAnuncioOnClick(View view) {
        try {
            AnunciosCadFragment anunciosCadFragment = new AnunciosCadFragment(this);
            anunciosCadFragment.show(getSupportFragmentManager(), "Cadastro Anúncio");
        } catch (Exception E) {
            Toast.makeText(this, "Erro ao abrir tela de cadastro de anúncios no perfil!", Toast.LENGTH_SHORT).show();
            Log.e("OpenFragment", E.getMessage());
        }
    }

}