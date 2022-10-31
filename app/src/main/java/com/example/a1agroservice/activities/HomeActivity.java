package com.example.a1agroservice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.a1agroservice.R;

public class HomeActivity extends AppCompatActivity {
    private ImageButton btMenuPerfil;
    private ImageButton btMenuPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btMenuPerfil = findViewById(R.id.btMenuPerfil);
        btMenuPesquisa = findViewById(R.id.btMenuPesquisa);

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

    }

    public void abrirMenuPerfil(){
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }

    public void abrirMenuPesquisa(){
        //TODO: implementar o método de pesquisa
        Toast.makeText(this, "Clicou no Menu Pesquisa!", Toast.LENGTH_LONG).show();
    }
}