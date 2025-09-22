package com.example.cantinanassau.Models;

public class Cliente {
    private int id;
    private long cpf;
    private String nome;
    private double saldo;
    private long contato;

    public Cliente() { }

    public Cliente(long cpf, String nome, double saldo, long contato) {
        this.cpf = cpf;
        this.nome = nome;
        this.saldo = saldo;
        this.contato = contato;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public long getCpf() { return cpf; }
    public void setCpf(long cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public Long getContato() { return contato; }
    public void setContato(Long contato) { this.contato = contato; }
}
