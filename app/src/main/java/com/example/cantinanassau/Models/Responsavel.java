package com.example.cantinanassau.Models;

public class Responsavel {
    // Campos correspondentes à tabela
    private long cpf; // Usando long para suportar CPF completo
    private String nome;
    private int responsavelContato; // Pode ser 0/1 ou armazenar outro tipo de código de contato

    // Construtor vazio
    public Responsavel() {}

    // Construtor completo
    public Responsavel(long cpf, String nome, int responsavelContato) {
        this.cpf = cpf;
        this.nome = nome;
        this.responsavelContato = responsavelContato;
    }

    // Getters e Setters
    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getResponsavelContato() {
        return responsavelContato;
    }

    public void setResponsavelContato(int responsavelContato) {
        this.responsavelContato = responsavelContato;
    }
}
