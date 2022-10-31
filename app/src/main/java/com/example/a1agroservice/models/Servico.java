package com.example.a1agroservice.models;

public class Servico {

    private int id;
    private int id_tipo_servico;
    private String descricao;
    private String data_inicio;
    private String data_fim;
    private Number valorhora;

    public Servico() {
    }

    public Servico(int id, int id_tipo_servico, String descricao, String data_inicio, String data_fim, Number valorhora) {
        this.id = id;
        this.id_tipo_servico = id_tipo_servico;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.valorhora = valorhora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tipo_servico() {
        return id_tipo_servico;
    }

    public void setId_tipo_servico(int id_tipo_servico) {
        this.id_tipo_servico = id_tipo_servico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public Number getValorhora() {
        return valorhora;
    }

    public void setValorhora(Number valorhora) {
        this.valorhora = valorhora;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", id_tipo_servico=" + id_tipo_servico +
                ", descricao='" + descricao + '\'' +
                ", data_inicio=" + data_inicio +
                ", data_fim=" + data_fim +
                ", valorhora=" + valorhora +
                '}';
    }
}
