package com.example.marco.biblia.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;


public class MySQLiteHelperCadastro extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UsuariosDB";
    private static final String TABLE= "usuario";
    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_DATA = "data";
    private static final String[] COLUMNS = {KEY_ID, KEY_NOME, KEY_EMAIL, KEY_SENHA, KEY_DATA};

    public MySQLiteHelperCadastro(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE =
                "CREATE TABLE "+TABLE+" ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nome TEXT, " +
                        "email TEXT, " +
                        "senha TEXT, " +
                        "data TEXT )";

        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+")");
        this.onCreate(db);
    }

    public void addUser(Usuario user) {
        Log.d("addUser()", user.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, user.getNome());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_SENHA, user.getSenha());
        values.put(KEY_DATA, user.getData());

        db.insert(TABLE, null, values);

        db.close();
    }

    public int validaLogin(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = new String[]{email, password};
        try
        {
            int i = 0;
            Cursor c = null;
            c = db.rawQuery("select * from "+TABLE+" where email=? and senha=?", selectionArgs);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return i;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }


    public List<Usuario> getAllUsers() {
        List<Usuario> users = new LinkedList<>();

        String query = "SELECT * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Usuario user = null;

        if (cursor.moveToFirst()) {
            do {
                user = new Usuario();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setNome(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setSenha(cursor.getString(3));
                user.setData(cursor.getString(4));

                // Add book to books
                users.add(user);
            } while (cursor.moveToNext());
        }

        Log.d("getAllUsers()", users.toString());

        // return books
        return users;
    }

    public String getNomeUsuario(String email){
        String nome = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT nome FROM "+TABLE+" WHERE email = '"+email+"'", null);

        if (c.moveToFirst()){
            do {
                nome = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return nome;
    }

    public String getEmailUsuario(String email2){
        String email = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT email FROM "+TABLE+" WHERE email = '"+email2+"'", null);

        if (c.moveToFirst()){
            do {
                email = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return email;
    }

    public String getSenhaUsuario(String email){
        String senha = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT senha FROM "+TABLE+" WHERE email = '"+email+"'", null);

        if (c.moveToFirst()){
            do {
                senha = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return senha;
    }

    public String getDataUsuario(String email){
        String data = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT data FROM "+TABLE+" WHERE email = '"+email+"'", null);

        if (c.moveToFirst()){
            do {
                data = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return data;
    }

    public void novoNome(String email, String novoNome){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "UPDATE "+TABLE+" SET nome = '"+novoNome+"' WHERE email = '"+email+"';";
        db.execSQL(query);
        db.close();
    }

    public void novoEmail(String email, String novoEmail){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "UPDATE "+TABLE+" SET email = '"+novoEmail+"' WHERE email = '"+email+"';";
        db.execSQL(query);
        db.close();
    }

    public void novaSenha(String email, String novaSenha){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "UPDATE "+TABLE+" SET senha = '"+novaSenha+"' WHERE email = '"+email+"';";
        db.execSQL(query);
        db.close();
    }

    public void deleteUser(Usuario user) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE,
                KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});

        db.close();

        Log.d("deleteUser", user.toString());
    }

}
