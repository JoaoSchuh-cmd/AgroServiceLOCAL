package com.example.a1agroservice.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.a1agroservice.R;
import com.example.a1agroservice.activities.AnunciosActivity;
import com.example.a1agroservice.activities.PerfilActivity;

public class MenuPerfilFragment extends DialogFragment {
    private Context context;
    private TextView tvSeuPerfil;
    private TextView tvSeusAnuncios;

    public MenuPerfilFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfilmenu_fragment_dialog, container, false);

        try {
            tvSeuPerfil = view.findViewById(R.id.tvSeuPerfil);
            tvSeusAnuncios = view.findViewById(R.id.tvSeusAnuncios);

            tvSeuPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent perfilPage = new Intent(context, PerfilActivity.class);
                        startActivity(perfilPage);
                    } catch (Exception E) {
                        Toast.makeText(context, "Erro ao abrir aba de Perfil!", Toast.LENGTH_SHORT).show();
                        Log.e("PerfilOnClick", E.getMessage());
                    }
                }
            });

            tvSeusAnuncios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent anunciosPerfil = new Intent(context, AnunciosActivity.class);
                        startActivity(anunciosPerfil);
                    } catch (Exception E) {
                        Toast.makeText(context, "Erro ao abrir aba de Seus Anúncios!", Toast.LENGTH_SHORT).show();
                        Log.e("SeusAnunciosOnClick", E.getMessage());
                    }
                }
            });
        } catch (Exception E) {
            Toast.makeText(context, "Erro ao criar menu de perfil!", Toast.LENGTH_SHORT).show();
            Log.e("MenuPerfilFragmentOnCreateView", E.getMessage());
        }

        return view;
    }

}
