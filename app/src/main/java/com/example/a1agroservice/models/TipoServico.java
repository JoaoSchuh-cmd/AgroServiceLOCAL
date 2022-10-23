package com.example.a1agroservice.models;

public class TipoServico {

    private int id;
    private String nome;

    public TipoServico() {
    }

    public TipoServico(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TipoServico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
