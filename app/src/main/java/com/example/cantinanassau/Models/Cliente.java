package com.example.cantinanassau.Models;

public class Cliente {
    private int id;
    private int matricula;
    private String nome;
    private double saldo;
    private Long contato; // pode ser null
    private boolean responsavel;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public Long getContato() { return contato; }
    public void setContato(Long contato) { this.contato = contato; }

    public boolean isResponsavel() { return responsavel; }
    public void setResponsavel(boolean responsavel) { this.responsavel = responsavel; }
}
