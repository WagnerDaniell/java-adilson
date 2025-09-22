package com.example.cantinanassau;

//CLASSE CRIADA APENAS PARA TESTE
public class Cliente {
    private String nome;
    private String telefone;
    private String matricula;
    private String responsavel;

    public Cliente(String nome, String telefone, String matricula, String responsavel) {
        this.nome = nome;
        this.telefone = telefone;
        this.matricula = matricula;
        this.responsavel = responsavel;
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getMatricula() { return matricula; }
    public String getResponsavel() { return responsavel; }
}
