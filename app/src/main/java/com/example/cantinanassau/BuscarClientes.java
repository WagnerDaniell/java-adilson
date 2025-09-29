package com.example.cantinanassau;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinanassau.Models.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        // Referências dos componentes da tela
        edtBusca       = findViewById(R.id.edtBuscaCliente);
        rvClientes     = findViewById(R.id.rvListaClientes);
        layoutDetalhes = findViewById(R.id.layoutDetalhesCliente);
        tvNome         = findViewById(R.id.tvNomeCliente);
        tvContato      = findViewById(R.id.tvTelefone);
        tvCpf          = findViewById(R.id.tvCpfDetalhesCliente);
        tvSaldo        = findViewById(R.id.tvSaldoDetalhesCliente);
        btnVoltar      = findViewById(R.id.btnvoltar);

        // Ação do botão voltar
        btnVoltar.setOnClickListener(v -> voltarParaTelaAnterior());

        // ======== BUSCA NO BANCO DE DADOS ========
        BancodeDados db = new BancodeDados(this);
        listaClientes = db.getClientes(); // Pega a lista de clientes do banco

        // ======== CONFIGURAÇÃO DO RECYCLERVIEW ========
        rvClientes.setLayoutManager(new LinearLayoutManager(this));
        // Inicializa o adapter com a lista de clientes e a ação de clique
        adapter = new ClienteAdapter(new ArrayList<>(listaClientes),
                cliente -> mostrarDetalhes(cliente));
        rvClientes.setAdapter(adapter);

        // ======== FILTRO DE BUSCA POR TEXTO ========
        edtBusca.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                // Chama o método de filtro sempre que o texto mudar
                filtrarClientes(s.toString());
            }
        });
    }

    // ===== MÉTODO PARA VOLTAR PARA A TELA ANTERIOR =====
    private void voltarParaTelaAnterior() {
        finish(); // Fecha a activity atual
    }

    // ===== MÉTODO PARA FILTRAR A LISTA DE CLIENTES =====
    private void filtrarClientes(String texto) {
        List<Cliente> filtrados = new ArrayList<>();
        // Se o texto da busca estiver vazio, mostra todos os clientes
        if (texto.isEmpty()) {
            filtrados.addAll(listaClientes);
        } else {
            // Itera sobre a lista original de clientes
            for (Cliente c : listaClientes) {
                // Compara o nome do cliente (em minúsculas) com o texto da busca
                if (c.getNome().toLowerCase().contains(texto.toLowerCase())) {
                    filtrados.add(c);
                }
            }
        }
        // Atualiza a lista no adapter com os resultados filtrados
        adapter.updateList(filtrados);
    }

    // ===== MÉTODO PARA MOSTRAR OS DETALHES DO CLIENTE SELECIONADO =====
    private void mostrarDetalhes(Cliente c) {
        layoutDetalhes.setVisibility(View.VISIBLE); // Torna o layout de detalhes visível
        tvNome.setText(c.getNome());
        tvContato.setText("Contato: " + c.getContato());
        tvCpf.setText("CPF: " + c.getCpf());
        // Formata o saldo para o padrão de moeda brasileiro
        tvSaldo.setText(String.format(new Locale("pt", "BR"), "Saldo: R$ %.2f", c.getSaldo()));
    }

    // ===================================================================
    // ================== ADAPTER INTERNO PARA CLIENTES ==================
    // ===================================================================
    private static class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

        // Interface para tratar o clique em um item da lista
        public interface OnItemClickListener {
            void onItemClick(Cliente cliente);
        }

        private List<Cliente> clientes;
        private final OnItemClickListener listener;

        // Construtor do Adapter
        public ClienteAdapter(List<Cliente> clientes, OnItemClickListener listener) {
            this.clientes = clientes;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Infla o layout customizado para cada item da lista
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_cliente, parent, false);
            return new ClienteViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
            // Pega o cliente da posição atual
            Cliente cliente = clientes.get(position);
            // "Seta" os dados do cliente nos TextViews do ViewHolder
            holder.bind(cliente, listener);
        }

        @Override
        public int getItemCount() {
            return clientes != null ? clientes.size() : 0;
        }

        // Método para atualizar a lista do adapter e notificar a mudança
        public void updateList(List<Cliente> novaLista) {
            clientes = new ArrayList<>(novaLista);
            notifyDataSetChanged();
        }

        // ================== VIEWHOLDER INTERNO ==================
        static class ClienteViewHolder extends RecyclerView.ViewHolder {
            TextView tvNomeItem, tvCpfItem;

            ClienteViewHolder(View itemView) {
                super(itemView);
                // Referências dos componentes do layout do item (item_cliente.xml)
                tvNomeItem = itemView.findViewById(R.id.tvNomeItemCliente);
                tvCpfItem = itemView.findViewById(R.id.tvCpfItemCliente);
            }

            // Método para vincular os dados e o listener de clique
            public void bind(final Cliente cliente, final OnItemClickListener listener) {
                tvNomeItem.setText(cliente.getNome());
                tvCpfItem.setText("CPF: " + cliente.getCpf());
                itemView.setOnClickListener(v -> listener.onItemClick(cliente));
            }
        }
    }
}

