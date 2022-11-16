package com.example.a1agroservice.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.a1agroservice.R;
import com.example.a1agroservice.activities.AnunciosActivity;
import com.example.a1agroservice.activities.PerfilActivity;

public class MenuPerfilFragment extends DialogFragment {
    private Context context;
    private View view;
    private TextView tvSeuPerfil;
    private TextView tvSeusAnuncios;

    public MenuPerfilFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.perfilmenu_fragment_dialog, container, false);

        tvSeuPerfil = view.findViewById(R.id.tvSeuPerfil);
        tvSeusAnuncios = view.findViewById(R.id.tvSeusAnuncios);

        tvSeuPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfilPage = new Intent(context, PerfilActivity.class);
                startActivity(perfilPage);
            }
        });

        tvSeusAnuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anunciosPerfil = new Intent(context, AnunciosActivity.class);
                startActivity(anunciosPerfil);
            }
        });

        return view;
    }

}
