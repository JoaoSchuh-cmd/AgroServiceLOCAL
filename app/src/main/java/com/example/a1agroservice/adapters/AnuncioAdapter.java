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

import java.util.ArrayList;

public class AnuncioAdapter extends BaseAdapter {
    private ArrayList<Anuncio> listaAnuncio;
    private Context context;

    public AnuncioAdapter(ArrayList<Anuncio> listaAnuncio, Context context) {
        this.listaAnuncio = listaAnuncio;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaAnuncio.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAnuncio.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaAnuncio.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.anuncios_list_item, parent, false);

        TextView tvTipoService = convertView.findViewById(R.id.tvTipoServico);
        TextView tvValorHora = convertView.findViewById(R.id.tvValorHora);
        TextView tvDataInicial = convertView.findViewById(R.id.tvDataInicial);
        TextView tvDataFinal = convertView.findViewById(R.id.tvDataFinal);
        TextView tvCidade = convertView.findViewById(R.id.tvCidade);
        TextView tvEstado = convertView.findViewById(R.id.tvEstado);
        TextView tvProprietario = convertView.findViewById(R.id.tvProprietario);

        Anuncio anuncio = listaAnuncio.get(position);
        tvTipoService.setText(
                TipoServicoController.getInstance(context)
                        .getTipoServicoById(
                                ServicoController.getInstance(context)
                                        .getServicoById(anuncio.getId_servico()) .getId_tipo_servico()).getNome()
        );
        tvValorHora.setText(String.valueOf(ServicoController.getInstance(context).getServicoById(anuncio.getId_servico()).getValorhora()));
        tvDataInicial.setText(ServicoController.getInstance(context).getServicoById(anuncio.getId_servico()).getData_inicio());
        tvDataFinal.setText(ServicoController.getInstance(context).getServicoById(anuncio.getId_servico()).getData_fim());
        tvCidade.setText(EnderecoController.getInstance(context).getEnderecoById(anuncio.getId_endereco()).getCidade());
        tvEstado.setText(EnderecoController.getInstance(context).getEnderecoById(anuncio.getId_endereco()).getEstado());
        tvProprietario.setText(PessoaController.getInstance(context).getPessoaById(anuncio.getId_pessoa()).getNome());

        CardView cvAnuncioItem = convertView.findViewById(R.id.cvAnuncioItem);

        cvAnuncioItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicou no anúncio!", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
