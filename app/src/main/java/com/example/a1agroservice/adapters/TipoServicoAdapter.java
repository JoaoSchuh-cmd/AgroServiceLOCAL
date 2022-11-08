package com.example.a1agroservice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.a1agroservice.R;
import com.example.a1agroservice.controllers.EnderecoController;
import com.example.a1agroservice.controllers.PessoaController;
import com.example.a1agroservice.controllers.ServicoController;
import com.example.a1agroservice.controllers.TipoServicoController;
import com.example.a1agroservice.models.Anuncio;
import com.example.a1agroservice.models.TipoServico;

import java.util.ArrayList;

public class TipoServicoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TipoServico> listaTipoServico;

    public TipoServicoAdapter(Context context, ArrayList<TipoServico> listaTipoServico) {
        this.context = context;
        this.listaTipoServico = listaTipoServico;
    }

    @Override
    public int getCount() {
        return listaTipoServico.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTipoServico.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaTipoServico.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.tiposervico_list_item, parent, false);

        TextView tvTipoServico = convertView.findViewById(R.id.tvTipoServico);

        TipoServico tipoServico = listaTipoServico.get(position);

        tvTipoServico.setText(tipoServico.getNome());

        return convertView;
    }
}
