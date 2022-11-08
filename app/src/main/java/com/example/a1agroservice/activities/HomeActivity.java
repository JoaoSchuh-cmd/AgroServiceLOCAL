package com.example.a1agroservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1agroservice.adapters.AnuncioAdapter;
import com.example.a1agroservice.R;
import com.example.a1agroservice.controllers.AnuncioController;
import com.example.a1agroservice.fragments.FiltrosFragment;
import com.example.a1agroservice.models.Anuncio;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ImageButton btMenuPerfil;
    private ImageButton btMenuPesquisa;

    private ArrayList<Anuncio> anuncios;
    private ListView lvListaAnuncios;
    private TextView tvListaVaziaMsg;
    private AnuncioController anuncioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btMenuPerfil = findViewById(R.id.btMenuPerfil);
        btMenuPesquisa = findViewById(R.id.btMenuPesquisa);
        lvListaAnuncios = findViewById(R.id.lvLista);
        tvListaVaziaMsg = findViewById(R.id.tvListaVaziaMsg);

        anuncioController = new AnuncioController(this);

        btMenuPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPerfil();
            }
        });

        btMenuPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMenuPesquisa();
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
        anuncios = anuncioController.getAnuncios();
        AnuncioAdapter adapter = new AnuncioAdapter(
                anuncios, this);

        lvListaAnuncios.setAdapter(adapter);
    }

    public void abrirMenuPerfil(){
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }

    public void abrirMenuPesquisa(){
        FiltrosFragment filtrosFragment = new FiltrosFragment(this);
        filtrosFragment.show(getSupportFragmentManager(), "Filtros");
    }
}