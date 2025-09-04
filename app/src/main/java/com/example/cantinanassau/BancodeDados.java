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
                "_Matricula INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "saldo REAL NOT NULL, " +
                "contato INTEGER, " +
                "Responsavel INTEGER DEFAULT 0 CHECK(Responsavel IN (0,1))" +
                ")";
        db.execSQL(Cliente);

        String Produto = "CREATE TABLE IF NOT EXISTS TbProduto (" +
                "_Codigo INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "valor REAL NOT NULL, " +
                "estoque INTEGER NOT NULL" +
                ")";
        db.execSQL(Produto);

        String Responsavel = "CREATE TABLE IF NOT EXISTS TbResponsavel (" +
                "_Cpf INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "responsavel_contato INTEGER NOT NULL" +
                ")";
        db.execSQL(Responsavel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TbCliente");
        db.execSQL("DROP TABLE IF EXISTS TbProduto");
        db.execSQL("DROP TABLE IF EXISTS TbResponsavel");
        onCreate(db);
    }
}
