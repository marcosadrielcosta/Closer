package com.example.marco.biblia.Notas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marco.biblia.Leitura.Leitura;
import com.example.marco.biblia.R;

import java.text.SimpleDateFormat;

public class Nova extends AppCompatActivity {
    MySQLiteHelperNotas db;
    Leitura leitura;
    Toolbar toolbar;
    ImageButton bt;
    EditText etTitulo, etCorpo;
    TextView tvVersiculo;
    String data, titulo, corpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova);

        db = new MySQLiteHelperNotas(this);
        leitura = new Leitura();

        //******************************************************************************************
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm");
        String dataa = sdf.format(date);
        data = dataa;

        //******************************************************************************************
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        //******************************************************************************************
        etTitulo = (EditText) findViewById(R.id.titulo3);
        etCorpo = (EditText) findViewById(R.id.corpo);

        //******************************************************************************************
        bt = (ImageButton) findViewById(R.id.voltar);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Lista.class);
                    startActivity(intent);
            }
        });

        //******************************************************************************************
        titulo = etTitulo.getText().toString();
        corpo = etCorpo.getText().toString();
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

                db.addNota(new Nota(etTitulo.getText().toString(), " ",
                        etCorpo.getText().toString(), data));
                Toast.makeText(this, "Nota Salva", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Lista.class);
                startActivity(intent);

                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }
}
