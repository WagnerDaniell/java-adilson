package com.example.cantinanassau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuBuscar extends AppCompatActivity {

    private Button btnBuscarCliente, btnBuscarProduto, btnHistoricoDeVendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_buscar);

        btnBuscarCliente = findViewById(R.id.buttonBuscarCliente);
        btnBuscarProduto = findViewById(R.id.buttonBuscarProduto);
        btnHistoricoDeVendas = findViewById(R.id.buttonHistoricoVendas);

        btnBuscarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuBuscar.this, BuscarClientes.class));
            }
        });

        btnBuscarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuBuscar.this, BuscarProdutos.class));
            }
        });
    }
}
