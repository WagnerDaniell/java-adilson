package com.example.cantinanassau.Models;

public class Cliente {
    private int id;
    private int cpf;
    private String nome;
    private double saldo;
    private Long contato;

    public Cliente(int cpf, String nome, int saldo, long contato) {
        this.cpf = cpf;
        this.nome = nome;
        this.saldo = saldo;
        this.contato = contato;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCpf() { return cpf; }
    public void setCpf(int cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public Long getContato() { return contato; }
    public void setContato(Long contato) { this.contato = contato; }
}
