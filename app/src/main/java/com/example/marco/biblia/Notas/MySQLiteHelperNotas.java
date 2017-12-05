package com.example.marco.biblia.Notas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;


public class MySQLiteHelperNotas extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NotasDB";
    private static final String TABLE_NOTAS = "notas";
    private static final String KEY_ID = "id";
    private static final String KEY_TITULO = "titulo";
    private static final String KEY_VERSICULO = "versiculo";
    private static final String KEY_CORPO = "corpo";
    private static final String KEY_DATA = "data";
    private static final String[] COLUMNS = {KEY_ID, KEY_TITULO, KEY_VERSICULO, KEY_CORPO, KEY_DATA};

    public MySQLiteHelperNotas(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE =
                "CREATE TABLE notas ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "titulo TEXT, " +
                        "versiculo TEXT, " +
                        "corpo TEXT, " +
                        "data TEXT )";

        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notas");
        this.onCreate(db);
    }

    public void addNota(Nota nota) {
        Log.d("addNota()", nota.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITULO, nota.getTitulo());
        values.put(KEY_VERSICULO, nota.getVersiculo());
        values.put(KEY_CORPO, nota.getCorpo());
        values.put(KEY_DATA, nota.getData());

        db.insert(TABLE_NOTAS, null, values);

        db.close();
    }

    public List<Nota> getAllNotas() {
        List<Nota> notas = new LinkedList<Nota>();

        String query = "SELECT * FROM " + TABLE_NOTAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Nota nota = null;

        if (cursor.moveToFirst()) {
            do {
                nota = new Nota();
                nota.setId(Integer.parseInt(cursor.getString(0)));
                nota.setTitulo(cursor.getString(1));
                nota.setVersiculo(cursor.getString(2));
                nota.setCorpo(cursor.getString(3));
                nota.setData(cursor.getString(4));

                // Add book to books
                notas.add(nota);
            } while (cursor.moveToNext());
        }

        Log.d("getAllNotas()", notas.toString());

        // return books
        return notas;
    }

    public String getTituloNota(Nota nota){
        String titulo = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT titulo FROM "+TABLE_NOTAS+" WHERE id = '"+nota.getId()+"'", null);

        if (c.moveToFirst()){
            do {
                titulo = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return titulo;
    }

    public String getCorpoNota(Nota nota){
        String corpo = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT corpo FROM "+TABLE_NOTAS+" WHERE id = '"+nota.getId()+"'", null);

        if (c.moveToFirst()){
            do {
                corpo = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return corpo;
    }

    public String getVersiculoNota(Nota nota){
        String versiculo = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT versiculo FROM "+TABLE_NOTAS+" WHERE id = '"+nota.getId()+"'", null);

        if (c.moveToFirst()){
            do {
                versiculo = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return versiculo;
    }

    public void uploadNota(Nota nota, String novoTitulo, String novoCorpo){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "UPDATE "+TABLE_NOTAS+" SET titulo = '"+novoTitulo+"'," +
                "corpo = '"+novoCorpo+"' WHERE id = '"+nota.getId()+"';";
        db.execSQL(query);
        db.close();

        Log.d("uploadNota()", nota.toString());
    }

    public void deleteNota(Nota nota) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NOTAS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(nota.getId())});

        db.close();

        Log.d("deleteNota", nota.toString());
    }

}
