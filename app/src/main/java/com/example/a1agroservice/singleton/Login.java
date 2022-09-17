package com.example.a1agroservice.singleton;

public class Login {
    private static Login usuarioLogado;
    private String usuario;
    private String senha;

    private Login(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public static Login getUsuarioLogado(String usuario, String senha) {
        return usuarioLogado == null ? new Login(usuario, senha) : usuarioLogado;
    }
}
