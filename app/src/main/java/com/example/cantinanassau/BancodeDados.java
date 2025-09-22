package com.example.cantinanassau;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cantinanassau.Models.Cliente;
import com.example.cantinanassau.Models.Item;
import com.example.cantinanassau.Models.Venda;

public class BancodeDados extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MeuBanco2.db";
    private static final int DATABASE_VERSION = 1;

    public BancodeDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Cliente = "CREATE TABLE IF NOT EXISTS TbCliente (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Cpf INTEGER UNIQUE, " +
                "Nome TEXT NOT NULL, " +
                "Saldo REAL NOT NULL, " +
                "Contato INTEGER " +
                ")";
        db.execSQL(Cliente);

        String Item = "CREATE TABLE IF NOT EXISTS TbItem (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nome TEXT UNIQUE NOT NULL, " +
                "Valor REAL NOT NULL, " +
                "Estoque INTEGER NOT NULL" +
                ")";
        db.execSQL(Item);

        String Vendas = "CREATE TABLE IF NOT EXISTS TbVendas (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Id_Cliente INTEGER NOT NULL, " +
                "Id_Item INTEGER NOT NULL, " +
                "Quantidade INTEGER NOT NULL, " +
                "Valor_Item INTEGER NOT NULL, " +
                "Sub_Total INTEGER NOT NULL, " +
                "Valor_Total INTEGER NOT NULL, " +
                "FOREIGN KEY (Id_Cliente) REFERENCES TbCliente(Id), " +
                "FOREIGN KEY (Id_Item) REFERENCES TbItem(Id)" +
                ")";
        db.execSQL(Vendas);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TbCliente");
        db.execSQL("DROP TABLE IF EXISTS TbItem");
        db.execSQL("DROP TABLE IF EXISTS TbVendas");
        onCreate(db);
    }

    public boolean insertCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Id", cliente.getId());
        values.put("Cpf", cliente.getCpf());
        values.put("Nome", cliente.getNome());
        values.put("Saldo", cliente.getSaldo());
        values.put("Contato", cliente.getContato());

        long result = db.insert("TbCliente", null, values);
        db.close();

        if (result != -1){
            return true; //Deu certo!
        }else{
            return false;
        }
    }

    public boolean insertItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Nome", item.getNome());
        values.put("Valor", item.getValor());
        values.put("Estoque", item.getEstoque());

        long result = db.insert("TbItem", null, values);
        db.close();

        if (result != -1){
            return true; //Deu certo!
        }else{
            return false;
        }
    }

    public void insertVenda(Venda venda){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Id", venda.getId());
        values.put("IdCliente", venda.getIdCliente());
        values.put("IdItem", venda.getIdItem());
        values.put("Quantidade", venda.getQuantidade());
        values.put("SubTotal", venda.getSubTotal());
        values.put("ValorItem", venda.getValorItem());
        values.put("ValorTotal", venda.getValorTotal());

        db.insert("TbVenda", null, values);
        db.close();
    }

}
