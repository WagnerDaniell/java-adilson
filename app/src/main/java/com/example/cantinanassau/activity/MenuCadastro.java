package com.example.cantinanassau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cantinanassau.R;

public class MenuCadastro extends AppCompatActivity {

    private Button btnCadastroCliente, btnCadastroProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cadastro);

        btnCadastroCliente = findViewById(R.id.buttonCadastroCliente);
        btnCadastroProduto = findViewById(R.id.buttonCadastroProduto);

        btnCadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuCadastro.this, Cadastro.class));
            }
        });

        btnCadastroProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuCadastro.this, CadastroProdutos.class));
            }
        });
    }
}
