package com.example.a1agroservice.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Pessoa implements Parcelable {

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

//  {  PARCEABLE }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(cpf);
        dest.writeString(usuario);
        dest.writeString(senha);
        dest.writeString(celular);
    }

    protected Pessoa(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        cpf = in.readString();
        usuario = in.readString();
        senha = in.readString();
        celular = in.readString();
    }

    public static final Creator<Pessoa> CREATOR = new Creator<Pessoa>() {
        @Override
        public Pessoa createFromParcel(Parcel in) {
            return new Pessoa(in);
        }

        @Override
        public Pessoa[] newArray(int size) {
            return new Pessoa[size];
        }
    };
}
