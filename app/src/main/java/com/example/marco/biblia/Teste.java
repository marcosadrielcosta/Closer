package com.example.marco.biblia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.marco.biblia.Login.MySQLiteHelperCadastro;
import com.example.marco.biblia.Login.Usuario;
import com.example.marco.biblia.R;

import java.util.List;

public class Teste extends AppCompatActivity {
    MySQLiteHelperCadastro db;
    ListView lv;
    List<Usuario> listUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        db = new MySQLiteHelperCadastro(this);

        listUsuarios = db.getAllUsers();
        lv = (ListView) findViewById(R.id.usuarios);
        ArrayAdapter<Usuario> itemsAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, listUsuarios);
        lv.setAdapter(itemsAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db.deleteUser(listUsuarios.get(position));
                finish();
                startActivity(getIntent());
            }
        });

    }
}
