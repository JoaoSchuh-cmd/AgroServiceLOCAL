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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.a1agroservice.R;
import com.example.a1agroservice.adapters.TipoServicoAdapter;
import com.example.a1agroservice.controllers.AnuncioController;
import com.example.a1agroservice.controllers.EnderecoController;
import com.example.a1agroservice.controllers.PessoaController;
import com.example.a1agroservice.controllers.ServicoController;
import com.example.a1agroservice.controllers.TipoServicoController;
import com.example.a1agroservice.models.Anuncio;
import com.example.a1agroservice.models.Endereco;
import com.example.a1agroservice.models.Servico;
import com.example.a1agroservice.models.TipoServico;
import com.example.a1agroservice.singleton.Login;

import java.util.ArrayList;

public class AnunciosCadFragment extends DialogFragment {
    private Button btSalvar;

//    ANUNCIO
    private String tipoPessoa;
    private RadioButton rbFuncionario;
    private RadioButton rbProprietario;

//    PROPRIETARIO
    private EditText edNome;
    private com.santalu.maskara.widget.MaskEditText edCelular;

//    ENDERECO
    private EditText edCidade;
    private EditText edEstado;
    private EnderecoController enderecoController;

//    PERIODO
    private EditText edDataInicial;
    private EditText edDataFinal;

//    TIPO SERVICO
    private Spinner spTipoServico;
    private TipoServicoController tipoServicoController;
    private long idTipoServicoSelected;

//    VALOR HORA
    private EditText edValorHora;

//    DESCRICAO
    private EditText edDescricao;

    private PessoaController pessoaController;
    private ArrayList<TipoServico> tipoServicos;
    private Context context;
    private AnuncioController anuncioController;
    private ServicoController servicoController;

    public AnunciosCadFragment(Context context) {
        this.context = context;
        anuncioController = AnuncioController.getInstance(context);
        tipoServicoController = TipoServicoController.getInstance(context);
        pessoaController = PessoaController.getInstance(context);
        enderecoController = EnderecoController.getInstance(context);
        servicoController = ServicoController.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(R.layout.anunciocad_fragment_dialog, container, false);
        try {
            tipoServicos = new ArrayList();

            spTipoServico = view.findViewById(R.id.spTipoServico);

            edNome = view.findViewById(R.id.edNome);
            edCidade = view.findViewById(R.id.edCidade);
            edEstado = view.findViewById(R.id.edEstado);
            edValorHora = view.findViewById(R.id.edValorHora);
            edDescricao = view.findViewById(R.id.edDescricao);
            edDataInicial = view.findViewById(R.id.edDataInicial);
            edDataFinal = view.findViewById(R.id.edDataFinal);
            edCelular = view.findViewById(R.id.edCelular);
            rbFuncionario = view.findViewById(R.id.rbFuncionario);
            rbProprietario = view.findViewById(R.id.rbProprietario);

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

            btSalvar = view.findViewById(R.id.btSalvar);

            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkFields())
                        CadastrarAnuncio();
                }
            });
        } catch (Exception E) {
            Toast.makeText(context, "Erro ao montar tela de cadastro de anúncio!", Toast.LENGTH_SHORT).show();
            Log.e("OnAdvertisementCreateView", E.getMessage());
        }

        return view;
    }

    private boolean checkFields() {
        boolean result = true;
        String mensagem = "Campo obrigatório";
        if (edNome.getText().toString().isEmpty()) {
            edNome.setError(mensagem);
            result = false;
        }
        if (edCelular.getText().toString().isEmpty()) {
            edCelular.setError(mensagem);
            result = false;
        }
        if (edCidade.getText().toString().isEmpty()) {
            edCidade.setError(mensagem);
            result = false;
        }
        if (edEstado.getText().toString().isEmpty()) {
            edEstado.setError(mensagem);
            result = false;
        }
        if (edValorHora.getText().toString().isEmpty()) {
            edValorHora.setError(mensagem);
            result = false;
        }
        return result;
    }

    private void CadastrarAnuncio() {
        try {
            Endereco endereco = new Endereco();
            endereco.setCidade(edCidade.getText().toString());
            endereco.setEstado(edEstado.getText().toString());
            enderecoController.saveEndereco(endereco);

            Servico servico = new Servico();
            servico.setId_tipo_servico(idTipoServicoSelected);
            servico.setDescricao(edDescricao.getText().toString());
            servico.setData_inicio(edDataInicial.getText().toString());
            servico.setData_fim(edDataFinal.getText().toString());
            servico.setValorhora(Double.parseDouble(edValorHora.getText().toString()));
            servicoController.saveServico(servico);

            // Aqui ele pega atualizado os dados do serviço e endereço recém cadastrados;
            servico = servicoController.getLastServico();
            endereco = enderecoController.getLastEndereco();

            Anuncio anuncio = new Anuncio();
            anuncio.setId_endereco(endereco.getId());
            anuncio.setId_servico(servico.getId());
            anuncio.setId_pessoa(pessoaController.getPessoaByUsername(Login.getUsuarioLogado().getUsuario()).getId());
            anuncio.setNomeProprietario(edNome.getText().toString());
            anuncio.setCelular(edCelular.getText().toString());
            anuncio.setTipoPessoa(rbFuncionario.isChecked() ? "F" : "P");

            anuncioController.saveAnuncio(anuncio);

            Toast.makeText(context, "Anúncio Cadastrado!", Toast.LENGTH_SHORT).show();

        } catch (Exception E) {
            Toast.makeText(context, "Erro ao salvar anúncio!", Toast.LENGTH_SHORT).show();
            Log.e("SaveAdvertisement", E.getMessage());
        }
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
