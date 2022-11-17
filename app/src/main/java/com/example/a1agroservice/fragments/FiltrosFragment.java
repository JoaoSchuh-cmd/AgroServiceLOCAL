package com.example.a1agroservice.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.a1agroservice.R;
import com.example.a1agroservice.adapters.TipoServicoAdapter;
import com.example.a1agroservice.controllers.TipoServicoController;
import com.example.a1agroservice.models.TipoServico;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FiltrosFragment extends DialogFragment {
    private Context context;

    private TipoServicoController tipoServicoController;
    private ArrayList<TipoServico> tipoServicos;

    private RadioButton rbFuncionario;
    private RadioButton rbProprietario;
    private RadioButton rbTodos;
    private Spinner getSpTipoServico;
    private EditText edValorHrMin;
    private EditText edValorHrMax;
    private com.santalu.maskara.widget.MaskEditText edDataInicial;
    private com.santalu.maskara.widget.MaskEditText edDataFinal;
    private EditText edCidade;
    private EditText edEstado;

    private Spinner spTipoServico;

    private Button btAplicar;

    private long idTipoServicoSelected;

    public FiltrosFragment(Context context) {
        this.context = context;
        tipoServicoController = TipoServicoController.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.filtros_fragment_dialog, container, false);
        try {
            tipoServicos = new ArrayList();

            spTipoServico = view.findViewById(R.id.spTipoServico);

            tipoServicos = tipoServicoController.getServicos();

            TipoServicoAdapter adapterTipoServico = new TipoServicoAdapter(context, tipoServicos);

            spTipoServico.setAdapter(adapterTipoServico);

            spTipoServico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    idTipoServicoSelected = tipoServicoController.getTipoServicoById(id).getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            rbFuncionario = view.findViewById(R.id.rbFuncionario);
            rbProprietario = view.findViewById(R.id.rbProprietario);
            rbTodos = view.findViewById(R.id.rbTodos);
            edValorHrMin = view.findViewById(R.id.edValorHrMin);
            edValorHrMax = view.findViewById(R.id.edValorHrMax);
            edDataInicial = view.findViewById(R.id.edDataInicial);
            edDataFinal = view.findViewById(R.id.edDataFinal);
            edCidade = view.findViewById(R.id.edCidade);
            edEstado = view.findViewById(R.id.edEstado);

            btAplicar = view.findViewById(R.id.btAplicar);

            btAplicar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO Aplicar filtros

                }
            });

        } catch (Exception E) {
            Toast.makeText(context, "Erro ao montar tela de filtros de anúncios!", Toast.LENGTH_SHORT).show();
            Log.e("OnAdvertisementFilterView", E.getMessage());
        }

        return view;
    }

    public ArrayList<String> getFiltrosSelected() {
        ArrayList<String> filtros = new ArrayList();

        if (!rbTodos.isChecked()) {
            if (rbFuncionario.isChecked())
                filtros.add("F");
            if (rbProprietario.isChecked())
                filtros.add("P");
        } else filtros.add("");
        filtros.add(String.valueOf(idTipoServicoSelected));
        filtros.add(edValorHrMin.getText().toString());
        filtros.add(edValorHrMax.getText().toString());
        filtros.add(edCidade.getText().toString());
        filtros.add(edEstado.getText().toString());

        return filtros;
    }
}
