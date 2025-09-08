package com.example.cantinanassau;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                "nome TEXT NOT NULL, " +
                "saldo REAL NOT NULL, " +
                "contato INTEGER, " +
                "Responsavel BOOLEAN NOT NULL DEFAULT 0" +
                ")";
        db.execSQL(Cliente);

        String Item = "CREATE TABLE IF NOT EXISTS TbItem (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "valor REAL NOT NULL, " +
                "estoque INTEGER NOT NULL" +
                ")";
        db.execSQL(Item);

        String Responsavel = "CREATE TABLE IF NOT EXISTS TbResponsavel (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Cpf INTEGER UNIQUE, " +
                "nome TEXT NOT NULL, " +
                "responsavel_contato INTEGER NOT NULL" +
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
}
