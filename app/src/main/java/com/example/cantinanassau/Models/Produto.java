package com.example.cantinanassau.Models;

public class Produto {
    // Campos correspondentes à tabela
    private int codigo;
    private String nome;
    private double valor;
    private int estoque;

    // Construtor vazio
    public Produto() {}

    // Construtor completo
    public Produto(int codigo, String nome, double valor, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}

