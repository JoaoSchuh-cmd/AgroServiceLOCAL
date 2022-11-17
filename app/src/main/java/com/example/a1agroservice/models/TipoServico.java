package com.example.a1agroservice.models;

public class TipoServico {

    private long id;
    private String nome;

    public TipoServico() {
    }

    public TipoServico(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
