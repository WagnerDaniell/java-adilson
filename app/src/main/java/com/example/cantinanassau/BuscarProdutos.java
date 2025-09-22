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

import com.example.cantinanassau.Models.Item;

import java.util.ArrayList;
import java.util.List;

public class BuscarProdutos extends AppCompatActivity {

    private EditText edtBusca;
    private RecyclerView rvProdutos;
    private LinearLayout layoutDetalhes;
    private TextView tvNome, tvValor, tvEstoque;

    private List<Item> listaProdutos;
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_produtos);

        // Referências da tela
        edtBusca      = findViewById(R.id.edtBuscaProduto);
        rvProdutos    = findViewById(R.id.rvListaProdutos);
        layoutDetalhes= findViewById(R.id.layoutDetalhesProduto);
        tvNome        = findViewById(R.id.tvNomeProduto);
        tvValor       = findViewById(R.id.tvValorProduto);
        tvEstoque     = findViewById(R.id.tvEstoqueProduto);

        // ======== BUSCA NO BANCO ========
        BancodeDados db = new BancodeDados(this);
        listaProdutos = db.getProdutos(); // pega os produtos do banco

        // ======== CONFIGURAÇÃO DO RECYCLER ========
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProdutoAdapter(new ArrayList<>(listaProdutos),
                item -> mostrarDetalhes(item));
        rvProdutos.setAdapter(adapter);

        // ======== FILTRO DE TEXTO ========
        edtBusca.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                filtrarProdutos(s.toString());
            }
        });
    }

    // ===== FILTRO DE PRODUTOS =====
    private void filtrarProdutos(String texto) {
        List<Item> filtrados = new ArrayList<>();
        for (Item i : listaProdutos) {
            if (i.getNome().toLowerCase().contains(texto.toLowerCase())) {
                filtrados.add(i);
            }
        }
        adapter.updateList(filtrados);
    }

    // ===== MOSTRAR DETALHES DO PRODUTO =====
    private void mostrarDetalhes(Item i) {
        layoutDetalhes.setVisibility(View.VISIBLE);
        tvNome.setText(i.getNome());
        tvValor.setText("Valor: R$ " + i.getValor());
        tvEstoque.setText("Estoque: " + i.getEstoque());
    }

    // ===== Adapter interno =====
    private static class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

        public interface OnItemClickListener {
            void onItemClick(Item item);
        }

        private List<Item> produtos;
        private OnItemClickListener listener;

        public ProdutoAdapter(List<Item> produtos, OnItemClickListener listener) {
            this.produtos = produtos;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_produto, parent, false); // usa o layout customizado
            return new ProdutoViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
            Item item = produtos.get(position);
            holder.tvNome.setText(item.getNome());
            holder.tvValor.setText("Valor: R$ " + item.getValor());
            holder.tvEstoque.setText("Estoque: " + item.getEstoque());

            holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
        }

        @Override
        public int getItemCount() {
            return produtos.size();
        }

        public void updateList(List<Item> novaLista) {
            produtos = novaLista;
            notifyDataSetChanged();
        }

        static class ProdutoViewHolder extends RecyclerView.ViewHolder {
            TextView tvNome, tvValor, tvEstoque;

            ProdutoViewHolder(View itemView) {
                super(itemView);
                tvNome = itemView.findViewById(R.id.tvNomeItem);
                tvValor = itemView.findViewById(R.id.tvValorItem);
                tvEstoque = itemView.findViewById(R.id.tvEstoqueItem);
            }
        }
    }

}
