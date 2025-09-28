package com.example.cantinanassau;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinanassau.Models.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Cliente cliente);
    }

    private List<Cliente> clientes;
    private OnItemClickListener listener;

    public ClienteAdapter(List<Cliente> clientes, OnItemClickListener listener) {
        this.clientes = clientes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cliente, parent, false);
        return new ClienteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder holder, int position) {
        Cliente c = clientes.get(position);
        holder.tvNome.setText(c.getNome());

        // aplica a cor configurada no colors.xml
        holder.tvNome.setTextColor(
                ContextCompat.getColor(holder.itemView.getContext(), R.color.corNomeCliente)
        );

        holder.itemView.setOnClickListener(v -> listener.onItemClick(c));
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public void updateList(List<Cliente> novaLista) {
        clientes = novaLista;
        notifyDataSetChanged();
    }

    static class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        ClienteViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNomeClienteItem);
        }
    }
}
