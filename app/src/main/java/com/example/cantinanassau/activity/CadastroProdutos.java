package com.example.cantinanassau.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cantinanassau.R;
import com.example.cantinanassau.database.BancodeDados;
import com.example.cantinanassau.model.Item;

public class CadastroProdutos extends AppCompatActivity {

    private EditText edtNomeProduto;
    private EditText edtValorProduto;
    private EditText edtEstoque;
    private Button submitButton;
    private BancodeDados Database;
    private TextView btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_produtos);

        edtNomeProduto = findViewById(R.id.edtNomeProduto);
        edtValorProduto = findViewById(R.id.edtValorProduto);
        edtEstoque = findViewById(R.id.edtEstoque);
        submitButton = findViewById(R.id.submitButton);
        btnVoltar = findViewById(R.id.btnvoltar);

        Database = new BancodeDados(this);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarParaTelaAnterior();
            }
        });

        submitButton.setOnClickListener(v -> addNewItem());
    }
    private void voltarParaTelaAnterior() {
        finish();
    }

        public void addNewItem (){
        String nomeItem = edtNomeProduto.getText().toString().trim(); //.trim remove espaços extras
        String valorItem = edtValorProduto.getText().toString().trim();
        String estoqueItem = edtEstoque.getText().toString().trim();

        // Só posso verificar se está vazio se tranformar tudo em string
        if (nomeItem.isEmpty() || valorItem.isEmpty() || estoqueItem.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        double valorDoItem;
        int estoqueDoItem;

        try {
            valorDoItem = Double.parseDouble(valorItem);
            estoqueDoItem = Integer.parseInt(estoqueItem);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor ou estoque inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        Item newItem = new Item(nomeItem, valorDoItem, estoqueDoItem);

        boolean result = Database.insertItem(newItem);

        if(result){
            Toast.makeText(this, "Item inserido com sucesso!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Erro ao adicionar o produto!", Toast.LENGTH_SHORT).show();
        }


    };


}
