package com.example.cantinanassau.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cantinanassau.R;


public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar, btnBuscar, btnVendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        btnCadastrar = findViewById(R.id.buttonCadastro);
        btnBuscar = findViewById(R.id.buttonBuscar);
        btnVendas = findViewById(R.id.buttonVendas);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MenuCadastro.class));
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MenuBuscar.class));
            }
        });

        btnVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Vendas.class));
            }
        });
    }
}