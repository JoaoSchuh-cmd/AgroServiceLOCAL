package com.example.a1agroservice.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.example.a1agroservice.R;

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;

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

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    public void abrirMenuPerfil(){
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);

    }

    public void abrirMenuPesquisa(){
        //TODO: implementar o método de pesquisa
        Toast.makeText(this, "Clicou no Menu Pesquisa!", Toast.LENGTH_LONG).show();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if(id == R.id.opcion1) {
//            Toast.makeText(this, "OPÇÃO", Toast.LENGTH_SHORT).show();
//        } else if(id == R.id.opcion2) {
//            Toast.makeText(this, "OPÇÃO", Toast.LENGTH_SHORT).show();
//        } else if(id == R.id.pessoa) {
//            Toast.makeText(this, "PESSOA", Toast.LENGTH_SHORT).show();
//        } else if(id == R.id.buscar) {
//            Toast.makeText(this, "BUSCAR", Toast.LENGTH_SHORT).show();
//        }
//
//        return true;
//    }
}