package com.example.cantinanassau.Models;

public class Cliente {
    // Campos correspondentes à tabela
    private int matricula;
    private String nome;
    private double saldo;
    private int contato;
    private boolean responsavel; // Agora é booleano

    // Construtor vazio
    public Cliente() {}

    // Construtor completo
    public Cliente(int matricula, String nome, double saldo, int contato, boolean responsavel) {
        this.matricula = matricula;
        this.nome = nome;
        this.saldo = saldo;
        this.contato = contato;
        this.responsavel = responsavel;
    }

    // Getters e Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getContato() {
        return contato;
    }

    public void setContato(int contato) {
        this.contato = contato;
    }

    public boolean isResponsavel() {
        return responsavel;
    }

    public void setResponsavel(boolean responsavel) {
        this.responsavel = responsavel;
    }

}
