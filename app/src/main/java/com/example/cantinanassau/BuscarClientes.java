package com.example.cantinanassau;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuscarClientes extends AppCompatActivity {

    private EditText edtBusca;
    private RecyclerView rvClientes;
    private LinearLayout layoutDetalhes;
    private TextView tvNome, tvTelefone, tvMatricula, tvResponsavel;

    private List<Cliente> clientesMock;
    private ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_clientes);

        edtBusca = findViewById(R.id.edtBuscaCliente);
        rvClientes = findViewById(R.id.rvListaClientes);
        layoutDetalhes = findViewById(R.id.layoutDetalhesCliente);
        tvNome = findViewById(R.id.tvNomeCliente);
        tvTelefone = findViewById(R.id.tvTelefone);
        tvMatricula = findViewById(R.id.tvMatricula);
        tvResponsavel = findViewById(R.id.tvResponsavel);

        // Dados falsos para teste
        clientesMock = Arrays.asList(
                new Cliente("Maria Oliveira", "(11) 99999-1111", "MAT123", "João Oliveira"),
                new Cliente("Pedro Santos", "(21) 98888-2222", "MAT456", "Ana Santos"),
                new Cliente("João da Silva", "(31) 97777-3333", "MAT789", "Carlos da Silva")
        );

        // Configuração do RecyclerView
        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClienteAdapter(new ArrayList<>(clientesMock), cliente -> mostrarDetalhes(cliente));
        rvClientes.setAdapter(adapter);

        // Filtrar conforme digita
        edtBusca.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                filtrarClientes(s.toString());
            }
        });
    }

    private void filtrarClientes(String texto) {
        List<Cliente> filtrados = new ArrayList<>();
        for (Cliente c : clientesMock) {
            if (c.getNome().toLowerCase().contains(texto.toLowerCase())) {
                filtrados.add(c);
            }
        }
        adapter.updateList(filtrados);
    }

    private void mostrarDetalhes(Cliente c) {
        layoutDetalhes.setVisibility(View.VISIBLE);
        tvNome.setText(c.getNome());
        tvTelefone.setText(c.getTelefone());
        tvMatricula.setText("Matrícula: " + c.getMatricula());
        tvResponsavel.setText("Responsável: " + c.getResponsavel());
    }
}
