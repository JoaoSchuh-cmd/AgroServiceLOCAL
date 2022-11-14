package com.example.a1agroservice.models;

public class Anuncio {
    private long id;
    private long id_pessoa;
    private long id_servico;
    private long id_endereco;
    private String nomeProprietario;
    private String celular;
    private String tipoPessoa;

    public Anuncio() {}

    public Anuncio(long id, int id_pessoa, int id_servico, int id_endereco, String nomeProprietario, String celular, String tipoPessoa) {
        this.id = id;
        this.id_pessoa = id_pessoa;
        this.id_servico = id_servico;
        this.id_endereco = id_endereco;
        this.nomeProprietario = nomeProprietario;
        this.celular = celular;
        this.tipoPessoa = tipoPessoa;
    }

    public long getId() {
        return id;
    }

    public long getId_pessoa() {
        return id_pessoa;
    }

    public long getId_servico() {
        return id_servico;
    }

    public long getId_endereco() {
        return id_endereco;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId_pessoa(long id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public void setId_servico(long id_servico) {
        this.id_servico = id_servico;
    }

    public void setId_endereco(long id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", id_pessoa=" + id_pessoa +
                ", id_servico=" + id_servico +
                ", id_endereco=" + id_endereco +
                ", nomeProprietario=" + nomeProprietario +
                ", celular=" + celular +
                ", tipoPessoa=" + tipoPessoa +
                '}';
    }
}
