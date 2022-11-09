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
        if (usuarioLogado == null) {
            usuarioLogado = new Login(usuario, senha);
        }
        return usuarioLogado;
    }

    public static Login getUsuarioLogado() {
        return usuarioLogado;
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

}
