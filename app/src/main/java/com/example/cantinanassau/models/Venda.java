package com.example.cantinanassau.models;

public class Venda {
    private int id;
    private int idCliente;
    private int idItem;
    private int quantidade;
    private int valorItem;
    private int subTotal;
    private int valorTotal;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdItem() { return idItem; }
    public void setIdItem(int idItem) { this.idItem = idItem; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public int getValorItem() { return valorItem; }
    public void setValorItem(int valorItem) { this.valorItem = valorItem; }

    public int getSubTotal() { return subTotal; }
    public void setSubTotal(int subTotal) { this.subTotal = subTotal; }

    public int getValorTotal() { return valorTotal; }
    public void setValorTotal(int valorTotal) { this.valorTotal = valorTotal; }
}

