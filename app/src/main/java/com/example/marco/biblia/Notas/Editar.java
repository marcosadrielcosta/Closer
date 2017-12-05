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
import android.widget.Toast;

import com.example.marco.biblia.R;

public class Editar extends AppCompatActivity {
    MySQLiteHelperNotas db;
    ImageButton bt;
    EditText etTitulo, etCorpo;
    TextView tvVersiculo;
    String titulo, corpo, versiculo;
    Lista lista;
    Nota nota;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        db = new MySQLiteHelperNotas(this);

        nota = lista.nota;
        titulo = lista.titulo;
        corpo = lista.corpo;
        versiculo = lista.versiculo;

        etTitulo = (EditText) findViewById(R.id.titulo3);
        etTitulo.setText(titulo);

        etCorpo = (EditText) findViewById(R.id.corpo3);
        etCorpo.setText(corpo);

        tvVersiculo = (TextView) findViewById(R.id.tvVersiculo);
        tvVersiculo.setText(versiculo);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        bt = (ImageButton) findViewById(R.id.voltar);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lista.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNota:
                db.uploadNota(nota, etTitulo.getText().toString(), etCorpo.getText().toString());
                Toast.makeText(this, "Nota Salva", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Lista.class);
                startActivity(intent);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}

