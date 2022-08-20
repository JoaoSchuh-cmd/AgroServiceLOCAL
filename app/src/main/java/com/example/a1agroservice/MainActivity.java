package com.example.a1agroservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btCadastrarOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Cadastro.class);
        startActivity(intent);
    }

    public void btEntrarOnClick(View view) {
    }
}