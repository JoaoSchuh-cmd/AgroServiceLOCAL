package com.example.a1agroservice.funcoesvalidacao;

public class FuncoesPadrao {
    public static boolean validaCPF(String cpf) {
        return ValidaCPF.isCPF(cpf);
    }
}
