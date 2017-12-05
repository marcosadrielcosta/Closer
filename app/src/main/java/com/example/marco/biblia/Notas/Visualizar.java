package com.example.marco.biblia.Notas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.marco.biblia.R;

public class Visualizar extends AppCompatActivity {
    MySQLiteHelperNotas db;
    EditText etTitulo, etCorpo;
    TextView tvVersiculo2;
    String titulo, corpo, versiculo;
    Lista lista;
    ImageButton bt;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        db = new MySQLiteHelperNotas(this);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        titulo = lista.titulo;
        corpo = lista.corpo;
        versiculo = lista.versiculo;

        etTitulo = (EditText) findViewById(R.id.titulo3);
        etTitulo.setText(titulo);

        etCorpo = (EditText) findViewById(R.id.corpo2);
        etCorpo.setText(corpo);

        tvVersiculo2 = (TextView) findViewById(R.id.tvVersiculo);
        tvVersiculo2.setText(versiculo);

        //******************************************************************************************
        bt = (ImageButton) findViewById(R.id.btVoltar);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lista.class);
                startActivity(intent);
            }
        });

        //******************************************************************************************
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opcoes2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:

                Intent intent = new Intent(getApplicationContext(), Editar.class);
                startActivity(intent);

                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
}
