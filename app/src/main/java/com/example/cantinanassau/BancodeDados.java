package com.example.cantinanassau;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cantinanassau.Models.Cliente;
import com.example.cantinanassau.Models.ClienteResponsavel;
import com.example.cantinanassau.Models.Item;
import com.example.cantinanassau.Models.Responsavel;
import com.example.cantinanassau.Models.Venda;

public class BancodeDados extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MeuBanco.db";
    private static final int DATABASE_VERSION = 1;

    public BancodeDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Cliente = "CREATE TABLE IF NOT EXISTS TbCliente (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Matricula INTEGER UNIQUE, " +
                "Nome TEXT NOT NULL, " +
                "Saldo REAL NOT NULL, " +
                "Contato INTEGER, " +
                "Responsavel BOOLEAN NOT NULL DEFAULT 0" +
                ")";
        db.execSQL(Cliente);

        String Item = "CREATE TABLE IF NOT EXISTS TbItem (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nome TEXT NOT NULL, " +
                "Valor REAL NOT NULL, " +
                "Estoque INTEGER NOT NULL" +
                ")";
        db.execSQL(Item);

        String Responsavel = "CREATE TABLE IF NOT EXISTS TbResponsavel (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Cpf INTEGER UNIQUE, " +
                "Nome TEXT NOT NULL, " +
                "Responsavel_Contato INTEGER NOT NULL" +
                ")";
        db.execSQL(Responsavel);

        String Cliente_Responsavel = "CREATE TABLE IF NOT EXISTS TbClienteResponsavel (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Id_Cliente INTEGER NOT NULL, " +
                "Id_Responsavel INTEGER NOT NULL, " +
                "FOREIGN KEY (Id_Cliente) REFERENCES TbCliente(Id), " +
                "FOREIGN KEY (Id_Responsavel) REFERENCES TbCliente(Id)" +
                ")";
        db.execSQL(Cliente_Responsavel);


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
        db.execSQL("DROP TABLE IF EXISTS TbProduto");
        db.execSQL("DROP TABLE IF EXISTS TbResponsavel");
        onCreate(db);
    }

    public void insertCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Id", cliente.getId());
        values.put("Matricula", cliente.getMatricula());
        values.put("Nome", cliente.getNome());
        values.put("Saldo", cliente.getSaldo());
        values.put("Contato", cliente.getContato());
        values.put("Responsavel", cliente.isResponsavel());

        db.insert("TbCliente", null, values);
        db.close();
    }

    public void insertItem(Item item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Id", item.getId());
        values.put("Nome", item.getNome());
        values.put("Valor", item.getValor());
        values.put("Estoque", item.getEstoque());

        db.insert("TbItem", null, values);
        db.close();
    }

    public void insertResponsavel(Responsavel responsavel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Id", responsavel.getId());
        values.put("Cpf", responsavel.getCpf());
        values.put("Nome", responsavel.getNome());
        values.put("ResponsavelContato", responsavel.getResponsavelContato());

        db.insert("TbResponsavel", null, values);
        db.close();
    }

    public void insertClienteResponsavel(ClienteResponsavel clienteResponsavel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Id", clienteResponsavel.getId());
        values.put("IdCliente", clienteResponsavel.getIdCliente());
        values.put("IdResponsavel", clienteResponsavel.getIdResponsavel());

        db.insert("TbClienteResponsavel", null, values);
        db.close();
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
