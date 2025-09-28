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

import com.example.cantinanassau.Models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class BuscarClientes extends AppCompatActivity {

    private EditText edtBusca;
    private RecyclerView rvClientes;
    private LinearLayout layoutDetalhes;
    private TextView tvNome, tvContato, tvCpf, tvSaldo;
    private TextView btnVoltar;

    private List<Cliente> listaClientes;
    private ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_clientes);

        edtBusca       = findViewById(R.id.edtBuscaCliente);
        rvClientes     = findViewById(R.id.rvListaClientes);
        layoutDetalhes = findViewById(R.id.layoutDetalhesCliente);
        tvNome         = findViewById(R.id.tvNomeCliente);
        tvContato      = findViewById(R.id.tvTelefone);
        tvCpf          = findViewById(R.id.tvMatricula);
        tvSaldo        = findViewById(R.id.tvResponsavel);
        btnVoltar      = findViewById(R.id.btnvoltar);

        // Clique no botão voltar
        btnVoltar.setOnClickListener(v -> voltarParaTelaAnterior());

        // ======== BUSCA NO BANCO ========
        BancodeDados db = new BancodeDados(this);
        listaClientes = db.getClientes();

        // ======== CONFIGURAÇÃO DO RECYCLER ========
        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClienteAdapter(new ArrayList<>(listaClientes),
                cliente -> mostrarDetalhes(cliente));
        rvClientes.setAdapter(adapter);

        edtBusca.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                filtrarClientes(s.toString());
            }
        });
    }

    // ===== BOTÃO VOLTAR =====
    private void voltarParaTelaAnterior() {
        finish(); // fecha a tela atual e volta
    }

    // ===== FILTRO DE CLIENTES =====
    private void filtrarClientes(String texto) {
        List<Cliente> filtrados = new ArrayList<>();
        for (Cliente c : listaClientes) {
            if (c.getNome().toLowerCase().contains(texto.toLowerCase())) {
                filtrados.add(c);
            }
        }
        adapter.updateList(filtrados);
    }

    private void mostrarDetalhes(Cliente c) {
        layoutDetalhes.setVisibility(View.VISIBLE);
        tvNome.setText(c.getNome());
        tvContato.setText("Contato: " + c.getContato());
        tvCpf.setText("CPF: " + c.getCpf());
        tvSaldo.setText("Saldo: R$ " + c.getSaldo());
    }
}
