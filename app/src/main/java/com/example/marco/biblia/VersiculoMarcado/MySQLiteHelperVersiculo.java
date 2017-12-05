package com.example.marco.biblia.VersiculoMarcado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelperVersiculo extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "VersiculosDB";
    private static final String TABLE= "versiculos";
    private static final String KEY_ID = "id";
    private static final String KEY_VERSICULO = "versiculo";
    private static final String KEY_DATA = "data";
    private static final String[] COLUMNS = {KEY_ID, KEY_VERSICULO, KEY_DATA};

    public MySQLiteHelperVersiculo(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE =
                "CREATE TABLE "+TABLE+" ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "versiculo TEXT, " +
                        "data TEXT )";

        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+")");
        this.onCreate(db);
    }

    public void addVer(Versiculo ver) {
        Log.d("addVer()", ver.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VERSICULO, ver.getVersiculo());
        values.put(KEY_DATA, ver.getData());

        db.insert(TABLE, null, values);

        db.close();
    }

    public List<Versiculo> getAllVer() {
        List<Versiculo> versiculos = new LinkedList<>();

        String query = "SELECT * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Versiculo user = null;

        if (cursor.moveToFirst()) {
            do {
                user = new Versiculo();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setVersiculo(cursor.getString(1));
                user.setData(cursor.getString(2));

                versiculos.add(user);
            } while (cursor.moveToNext());
        }

        Log.d("getAllVer()", versiculos.toString());

        // return books
        return versiculos;
    }

    public void deleteVer(Versiculo ver) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE,
                KEY_ID + " = ?",
                new String[]{String.valueOf(ver.getId())});

        db.close();

        Log.d("deleteVer", ver.toString());
    }

}

