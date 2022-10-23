package com.example.a1agroservice.models;

public class Anuncio {
    private int id;
    private int id_pessoa;
    private int id_servico;
    private int id_endereco;

    public Anuncio() {
    }

    public Anuncio(int id, int id_pessoa, int id_servico, int id_endereco) {
        this.id = id;
        this.id_pessoa = id_pessoa;
        this.id_servico = id_servico;
        this.id_endereco = id_endereco;
    }

    public int getId() {
        return id;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public int getId_servico() {
        return id_servico;
    }

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", id_pessoa=" + id_pessoa +
                ", id_servico=" + id_servico +
                ", id_endereco=" + id_endereco +
                '}';
    }
}
