package com.example.a1agroservice.models;

public class Pessoa {

    private int id;
    private String nome;
    private String cpf;
    private String usuario;
    private String senha;
    private String celular;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String cpf, String usuario, String senha, String celular) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.usuario = usuario;
        this.senha = senha;
        this.celular = celular;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", celular='" + celular + '\'' +
                '}';
    }
}
