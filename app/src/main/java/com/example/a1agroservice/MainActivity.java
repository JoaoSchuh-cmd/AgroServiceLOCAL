package com.example.a1agroservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.opcion1) {
            Toast.makeText(this, "OPÇÃO", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.opcion2) {
            Toast.makeText(this, "OPÇÃO", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.pessoa) {
            Toast.makeText(this, "PESSOA", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.buscar) {
            Toast.makeText(this, "BUSCAR", Toast.LENGTH_SHORT).show();
        }

        return true;
    }


}