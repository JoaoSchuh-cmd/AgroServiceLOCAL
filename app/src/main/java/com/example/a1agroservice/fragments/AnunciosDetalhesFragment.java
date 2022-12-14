package com.example.a1agroservice.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.a1agroservice.R;
import com.example.a1agroservice.controllers.AnuncioController;
import com.example.a1agroservice.controllers.EnderecoController;
import com.example.a1agroservice.controllers.ServicoController;
import com.example.a1agroservice.controllers.TipoServicoController;
import com.example.a1agroservice.models.Anuncio;

public class AnunciosDetalhesFragment extends DialogFragment {
    private Context context;
    private Anuncio anuncio;
    private TipoServicoController tipoServicoController;
    private EnderecoController enderecoController;
    private ServicoController servicoController;
    private AnuncioController anuncioController;

    private TextView tvTipoServico;
    private TextView tvDescricao;
    private TextView tvDataInicial;
    private TextView tvDataFinal;
    private TextView tvCidade;
    private TextView tvEstado;
    private TextView tvProprietario;
    private TextView tvValorHora;

    private Button btEnviarMensagem;

    public AnunciosDetalhesFragment(Context context, Anuncio anuncio) {
        this.context = context;
        this.anuncio = anuncio;
        tipoServicoController = TipoServicoController.getInstance(context);
        enderecoController = EnderecoController.getInstance(context);
        servicoController = ServicoController.getInstance(context);
        anuncioController = AnuncioController.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.anunciodetalhe_fragment_dialog, container, false);

        try {
            tvTipoServico = view.findViewById(R.id.tvTipoServico);
            tvDescricao = view.findViewById(R.id.tvDescricao);
            tvDataInicial = view.findViewById(R.id.tvDataInicial);
            tvDataFinal = view.findViewById(R.id.tvDataFinal);
            tvCidade = view.findViewById(R.id.tvCidade);
            tvEstado = view.findViewById(R.id.tvEstado);
            tvProprietario = view.findViewById(R.id.tvProprietario);
            tvValorHora = view.findViewById(R.id.tvValorHora);

            tvTipoServico.setText(tipoServicoController.getTipoServicoById(servicoController.getServicoById(anuncio.getId_servico()).getId_tipo_servico()).getNome());
            tvDescricao.setText(servicoController.getServicoById(anuncio.getId_servico()).getDescricao());
            tvDataInicial.setText(servicoController.getServicoById(anuncio.getId_servico()).getData_inicio());
            tvDataFinal.setText(servicoController.getServicoById(anuncio.getId_servico()).getData_fim());
            tvCidade.setText(enderecoController.getEnderecoById(anuncio.getId_endereco()).getCidade());
            tvEstado.setText(enderecoController.getEnderecoById(anuncio.getId_endereco()).getEstado());
            tvProprietario.setText(anuncio.getNomeProprietario());
            tvValorHora.setText(String.valueOf(servicoController.getServicoById(anuncio.getId_servico()).getValorhora()));

            btEnviarMensagem = view.findViewById(R.id.btEnviarMensagem);

            btEnviarMensagem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phone = anuncio.getCelular();
                    try {
                        Intent whatsapp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+phone));
                        startActivity(whatsapp);
                    } catch (Exception E) {
                        Toast.makeText(context, "Erro ao tentar abrir WhatsApp", Toast.LENGTH_SHORT).show();
                        Log.e("SendMessageClick", E.getMessage());
                    }
                }
            });

        } catch (Exception E) {
            Toast.makeText(context, "Erro ao carregar tela de detalhes de an??ncio", Toast.LENGTH_SHORT).show();
            Log.e("OnAnunciosDetalhesCreateView", E.getMessage());
        }

        return view;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
