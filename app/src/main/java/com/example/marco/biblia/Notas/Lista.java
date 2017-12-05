package com.example.marco.biblia.Notas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marco.biblia.Leitura.Livros;
import com.example.marco.biblia.Perfil.Perfil;
import com.example.marco.biblia.Plan.Plan;
import com.example.marco.biblia.R;

import java.util.List;

public class Lista extends AppCompatActivity {
    static String titulo, corpo, versiculo;
    static Nota nota;
    MySQLiteHelperNotas db;
    Button bt;
    ListView lv;
    List<Nota> list;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        db = new MySQLiteHelperNotas(this);

        lv = (ListView) findViewById(R.id.listView);
        bt = (Button) findViewById(R.id.btAdd);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Nova.class);
                startActivity(intent);
            }
        });

        list = db.getAllNotas();
        ArrayAdapter<Nota> itemsAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(itemsAdapter);
        registerForContextMenu(lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nota = list.get(position);
                titulo = db.getTituloNota(list.get(position));
                corpo = db.getCorpoNota(list.get(position));
                versiculo = db.getVersiculoNota(list.get(position));

                Intent intent = new Intent(getApplicationContext(), Visualizar.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("O que deseja fazer?");
        menu.add(0, v.getId(), 0, "Deletar Nota");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Cancelar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index = info.position;

        if (item.getTitle() == "Deletar Nota") {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Deletar Nota");
            builder.setMessage("Deseja deletar esta nota?");
            builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    db.deleteNota(list.get(index));
                    finish();
                    startActivity(getIntent());
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    finish();
                    startActivity(getIntent());
                }
            });

            alerta = builder.create();
            alerta.show();

        } else if (item.getTitle() == "Cancelar") {
            finish();
            startActivity(getIntent());
        } else {
            return false;
        }
        return true;
    }

    public void clickPerfil(View v){
        Intent i = new Intent(this, Perfil.class);
        startActivity(i);
    }

    public void clickLeitura(View v){
        Intent i = new Intent(this, Livros.class);
        startActivity(i);
    }

    public void clickNotas(View v){
        Intent i = new Intent(this, Lista.class);
        startActivity(i);
    }

    public void clickPlan(View v){
        Intent i = new Intent(this, Plan.class);
        startActivity(i);
    }
}
