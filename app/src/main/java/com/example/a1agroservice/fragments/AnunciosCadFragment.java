package com.example.a1agroservice.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.a1agroservice.R;
import com.example.a1agroservice.adapters.TipoServicoAdapter;
import com.example.a1agroservice.controllers.TipoServicoController;
import com.example.a1agroservice.models.TipoServico;

import java.util.ArrayList;

public class AnunciosCadFragment extends DialogFragment {
    private Spinner spTipoServico;
    private TipoServicoController tipoServicoController;
    private ArrayList<TipoServico> tipoServicos;
    private Context context;

    public AnunciosCadFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //TODO Problema com o style do Spinner

        View view = LayoutInflater.from(context).inflate(R.layout.anunciocad_fragment_dialog, container, false);

        tipoServicos = new ArrayList();

        spTipoServico = view.findViewById(R.id.spTipoServico);

        tipoServicoController = new TipoServicoController(context);
        tipoServicos = tipoServicoController.getServicos();

        TipoServicoAdapter adapterTipoServico = new TipoServicoAdapter(context, tipoServicos);

        spTipoServico.setAdapter(adapterTipoServico);

        return view;
    }
}
