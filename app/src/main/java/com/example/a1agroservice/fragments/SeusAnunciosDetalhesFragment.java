package com.example.a1agroservice.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.a1agroservice.R;
import com.example.a1agroservice.adapters.TipoServicoAdapter;
import com.example.a1agroservice.controllers.AnuncioController;
import com.example.a1agroservice.controllers.EnderecoController;
import com.example.a1agroservice.controllers.ServicoController;
import com.example.a1agroservice.controllers.TipoServicoController;
import com.example.a1agroservice.models.Anuncio;
import com.example.a1agroservice.models.Endereco;
import com.example.a1agroservice.models.Servico;
import com.example.a1agroservice.models.TipoServico;

import java.util.ArrayList;

public class SeusAnunciosDetalhesFragment extends DialogFragment {
    private Context context;
    private Anuncio anuncio;

    private TipoServicoController tipoServicoController;
    private ServicoController servicoController;
    private EnderecoController enderecoController;
    private AnuncioController anuncioController;

    private Spinner spTipoServico;

    private EditText edDescricao;
    private EditText edDataInicial;
    private EditText edDataFinal;
    private EditText edCidade;
    private EditText edEstado;
    private EditText edProprietario;
    private EditText edValorHora;

    private Button btSalvar;
    private Button btExcluir;

    private long idTipoServicoSelected;
    private int firstIdTipoServicoSelected;

    public SeusAnunciosDetalhesFragment(Context context, Anuncio anuncio) {
        this.context = context;
        this.anuncio = anuncio;
        tipoServicoController = TipoServicoController.getInstance(context);
        servicoController = ServicoController.getInstance(context);
        enderecoController = EnderecoController.getInstance(context);
        anuncioController = AnuncioController.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.anunciodetalhe_seusanuncios_fragment_dialog, container, false);

        try {
            ArrayList<TipoServico> tipoServicos = new ArrayList();

            spTipoServico = view.findViewById(R.id.spTipoServico);

            edDescricao = view.findViewById(R.id.edDescricao);
            edDataInicial = view.findViewById(R.id.edDataInicial);
            edDataFinal = view.findViewById(R.id.edDataFinal);
            edCidade = view.findViewById(R.id.edCidade);
            edEstado = view.findViewById(R.id.edEstado);
            edProprietario = view.findViewById(R.id.edProprietario);
            edValorHora = view.findViewById(R.id.edValorHora);

            btSalvar = view.findViewById(R.id.btSalvarAnuncio);
            btExcluir = view.findViewById(R.id.btExcluirAnuncio);

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

            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Endereco endereco = new Endereco();
                        endereco.setEstado(edEstado.getText().toString());
                        endereco.setCidade(edCidade.getText().toString());
                        enderecoController.saveEndereco(endereco);

                        Servico servico = new Servico();
                        servico.setDescricao(edDescricao.getText().toString());

                        servico.setData_inicio(edDataInicial.getText().toString());
                        servico.setData_fim(edDataFinal.getText().toString());
                        servico.setValorhora(Double.parseDouble(edValorHora.getText().toString()));
                        servico.setId_tipo_servico(idTipoServicoSelected);
                        servicoController.saveServico(servico);

                        Anuncio newAnuncio = new Anuncio();
                        newAnuncio.setTipoPessoa(anuncio.getTipoPessoa());
                        newAnuncio.setCelular(anuncio.getCelular());
                        newAnuncio.setNomeProprietario(edProprietario.getText().toString());
                        newAnuncio.setId_endereco(enderecoController.getLastEndereco().getId());
                        newAnuncio.setId_pessoa(anuncio.getId_pessoa());
                        newAnuncio.setId_servico(servicoController.getLastServico().getId());
                        anuncioController.updateAnuncio(anuncio, newAnuncio);

                        Toast.makeText(context, "Anúncio salvo!", Toast.LENGTH_SHORT).show();
                    } catch (Exception E) {
                        Toast.makeText(context, "Erro ao atualizar anúncio!", Toast.LENGTH_SHORT).show();
                        Log.e("OnProfileAdvertisementSaveClick", E.getMessage());
                    }
                }
            });

            btExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        anuncioController.deleteAnuncio(anuncio);
                        Toast.makeText(context, "Anúncio Exclúido!", Toast.LENGTH_SHORT).show();
                        onCancel(getDialog());
                    } catch (Exception E) {
                        Toast.makeText(context, "Erro ao excluir anúncio!", Toast.LENGTH_SHORT).show();
                        Log.e("OnDeleteAdvertisement", E.getMessage());
                    }
                }
            });

            firstIdTipoServicoSelected = Integer.parseInt(
                    String.valueOf(
                            tipoServicoController.getTipoServicoById(
                                    servicoController.getServicoById(anuncio.getId_servico()).getId_tipo_servico()
                            ).getId()
                    ));

            spTipoServico.setSelection(tipoServicos.indexOf(firstIdTipoServicoSelected));

            Servico servico = servicoController.getServicoById(anuncio.getId_servico());
            Endereco endereco = enderecoController.getEnderecoById(anuncio.getId_endereco());

            edDescricao.setText(servico.getDescricao());
            edDataInicial.setText(servico.getData_inicio());
            edDataFinal.setText(servico.getData_fim());
            edCidade.setText(endereco.getCidade());
            edEstado.setText(endereco.getEstado());
            edProprietario.setText(anuncio.getNomeProprietario());
            edValorHora.setText(String.valueOf(servico.getValorhora()));

        } catch (Exception E) {
            Toast.makeText(context, "Erro ao criar tela de detalhes de anuncio na aba Seus Anuncios", Toast.LENGTH_SHORT).show();
            Log.e("AnunciosDetalhesSeusAnunciosOnCreateView", E.getMessage());
        }

        return view;
    }

}
