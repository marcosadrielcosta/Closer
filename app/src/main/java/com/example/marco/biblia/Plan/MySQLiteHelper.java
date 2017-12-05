package com.example.marco.biblia.Plan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PlanosDB";
    private static final String TABLE_BOOKS = "planos";
    private static final String KEY_ID = "id";
    private static final String KEY_DATA = "data";
    private static final String KEY_REF = "ref";
    private static final String[] COLUMNS = {KEY_ID, KEY_DATA, KEY_REF};

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE =
                "CREATE TABLE planos ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "data TEXT, " +
                        "ref TEXT )";

        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS planos");
        this.onCreate(db);
    }

    public void addPlano(Planejamento plan) {
        Log.d("addPlano", plan.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATA, plan.getData());
        values.put(KEY_REF, plan.getRef());

        db.insert(TABLE_BOOKS,
                null,
                values);

        db.close();
    }

    public List<Planejamento> getAllPlanos() {
        List<Planejamento> planos = new LinkedList<Planejamento>();

        String query = "SELECT  * FROM " + TABLE_BOOKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Planejamento plan = null;

        if (cursor.moveToFirst()) {
            do {
                plan = new Planejamento();
                plan.setId(Integer.parseInt(cursor.getString(0)));
                plan.setData(cursor.getString(1));
                plan.setRef(cursor.getString(2));
                planos.add(plan);
            } while (cursor.moveToNext());
        }

        Log.d("getAllPlanos()", planos.toString());

        return planos;
    }

    public void deletePlano(Planejamento plan) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_BOOKS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(plan.getId())});

        db.close();

        Log.d("deletePlano", plan.toString());

    }

}

