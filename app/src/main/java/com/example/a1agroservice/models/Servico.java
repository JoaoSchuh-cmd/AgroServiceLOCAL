package com.example.a1agroservice.models;

public class Servico {

    private long id;
    private long id_tipo_servico;
    private String descricao;
    private String data_inicio;
    private String data_fim;
    private double valorhora;

    public Servico() {
    }

    public Servico(long id, long id_tipo_servico, String descricao, String data_inicio, String data_fim, double valorhora) {
        this.id = id;
        this.id_tipo_servico = id_tipo_servico;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.valorhora = valorhora;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_tipo_servico() {
        return id_tipo_servico;
    }

    public void setId_tipo_servico(long id_tipo_servico) {
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

    public double getValorhora() {
        return valorhora;
    }

    public void setValorhora(double valorhora) {
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
