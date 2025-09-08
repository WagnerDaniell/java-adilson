package com.example.cantinanassau.Models;

public class Responsavel {
    private int id;
    private long cpf;
    private String nome;
    private long responsavelContato;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public long getCpf() { return cpf; }
    public void setCpf(long cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public long getResponsavelContato() { return responsavelContato; }
    public void setResponsavelContato(long responsavelContato) { this.responsavelContato = responsavelContato; }
}
