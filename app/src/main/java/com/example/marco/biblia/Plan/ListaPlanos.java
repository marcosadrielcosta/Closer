package com.example.marco.biblia.Plan;

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
import android.widget.ListView;
import android.widget.Toast;

import com.example.marco.biblia.Leitura.Livros;
import com.example.marco.biblia.Notas.Lista;
import com.example.marco.biblia.Perfil.Perfil;
import com.example.marco.biblia.R;
import com.example.marco.biblia.Plan.MySQLiteHelper;
import com.example.marco.biblia.Plan.Planejamento;
import com.example.marco.biblia.Plan.Planos;

import java.util.List;

public class ListaPlanos extends AppCompatActivity {
    List<Planejamento> list;
    MySQLiteHelper db;
    ListView lv2;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_planos);

        db = new MySQLiteHelper(this);
        list = db.getAllPlanos();

        lv2 = (ListView) findViewById(R.id.listview);
        ArrayAdapter<Planejamento> itemsAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, list);
        lv2.setAdapter(itemsAdapter);
        lv2.deferNotifyDataSetChanged();
        registerForContextMenu(lv2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("O que deseja fazer?");
        menu.add(0, v.getId(), 0, "Deletar Plano");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Voltar");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index = info.position;

        if (item.getTitle() == "Deletar Plano") {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Deletar Plano");
            builder.setMessage("Deseja deletar este plano?");
            builder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    db.deletePlano(list.get(index));
                    Intent intent = new Intent(getApplicationContext(), Planos.class);
                    startActivity(intent);
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

        } else if (item.getTitle() == "Voltar") {
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

