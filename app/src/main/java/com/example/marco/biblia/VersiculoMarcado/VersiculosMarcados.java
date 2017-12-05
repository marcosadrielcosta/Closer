package com.example.marco.biblia.VersiculoMarcado;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.example.marco.biblia.Leitura.Livros;
import com.example.marco.biblia.Notas.Lista;
import com.example.marco.biblia.Perfil.Perfil;
import com.example.marco.biblia.Plan.Plan;
import com.example.marco.biblia.R;

import java.util.List;

public class VersiculosMarcados extends AppCompatActivity {
    MySQLiteHelperVersiculo db;
    ListView lv;
    List<Versiculo> list;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_versiculos_marcados);

        db = new MySQLiteHelperVersiculo(this);

        lv = (ListView) findViewById(R.id.lvVersiculos);

        list = db.getAllVer();

        ArrayAdapter<Versiculo> itemsAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(itemsAdapter);
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Deletar este Versículo?");
        menu.add(0, v.getId(), 0, "Deletar");
        menu.add(0, v.getId(), 0, "Cancelar");//groupId, itemId, order, title

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index = info.position;

        if (item.getTitle() == "Deletar") {
            db.deleteVer(list.get(index));
            finish();
            startActivity(getIntent());

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
