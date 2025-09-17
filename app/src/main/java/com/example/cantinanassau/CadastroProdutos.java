package com.example.cantinanassau;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cantinanassau.Models.Item;

import java.io.Console;

public class CadastroProdutos extends AppCompatActivity {

    private EditText edtNomeProduto;
    private EditText edtValorProduto;
    private EditText edtEstoque;
    private Button submitButton;
    private BancodeDados Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_produtos);

        edtNomeProduto = findViewById(R.id.edtNomeProduto);
        edtValorProduto = findViewById(R.id.edtValorProduto);
        edtEstoque = findViewById(R.id.edtEstoque);
        submitButton = findViewById(R.id.submitButton);

        Database = new BancodeDados(this);

        submitButton.setOnClickListener(v -> addNewItem());
    }

    public void addNewItem (){
        String nomeItem = edtNomeProduto.getText().toString();
        double valorItem = Double.parseDouble(edtValorProduto.getText().toString());
        int estoqueItem = Integer.parseInt(edtEstoque.getText().toString());

        Item newItem = new Item(nomeItem, valorItem, estoqueItem);

        boolean result = Database.insertItem(newItem);

        if(result){
            Toast.makeText(this, "Item inserido com sucesso!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Erro ao adicionar o produto!", Toast.LENGTH_SHORT).show();
        }

    };

}
