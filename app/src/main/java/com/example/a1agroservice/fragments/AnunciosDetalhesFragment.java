package com.example.a1agroservice.fragments;

import android.content.Context;
import android.content.DialogInterface;
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
import com.example.a1agroservice.controllers.AnuncioController;
import com.example.a1agroservice.controllers.EnderecoController;
import com.example.a1agroservice.controllers.PessoaController;
import com.example.a1agroservice.controllers.ServicoController;
import com.example.a1agroservice.controllers.TipoServicoController;
import com.example.a1agroservice.models.Anuncio;

import org.w3c.dom.Text;

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

    public AnunciosDetalhesFragment(Context context, Anuncio anuncio) {
        this.context = context;
        this.anuncio = anuncio;
        tipoServicoController = new TipoServicoController(context);
        enderecoController = new EnderecoController(context);
        servicoController = new ServicoController(context);
        anuncioController = new AnuncioController(context);
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

        } catch (Exception E) {
            Toast.makeText(context, "Erro ao carregar tela de detalhes de anúncio", Toast.LENGTH_SHORT).show();
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
