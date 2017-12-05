package com.example.marco.biblia.Plan;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import com.example.marco.biblia.R;

import java.util.List;

public class Planos extends AppCompatActivity {
    MySQLiteHelper db;
    TextView tv;
    Plan ca;
    String data2;
    Button bt;
    ListView lv;
    Spinner sp1, sp2;
    String livro;
    RelativeLayout layout;
    List<Planejamento> list;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planos);

        ca = new Plan();
        data2 = Plan.date2;
        tv = (TextView) findViewById(R.id.textView17);
        tv.setText(data2);

        layout = (RelativeLayout) findViewById(R.id.relative);
        db = new MySQLiteHelper(this);
        bt = (Button) findViewById(R.id.btAdd);
        sp1 = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.livros, android.R.layout.simple_list_item_1);

        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource
                (this, R.array.capMateus, android.R.layout.simple_list_item_1);
        final ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource
                (this, R.array.capMarcos, android.R.layout.simple_list_item_1);

        //SPINNERS**********************************************************************************
        //******************************************************************************************
        sp1.setAdapter(adapter);
        sp2 = (Spinner) findViewById(R.id.spCap);


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    sp2.setAdapter(adapter2);
                else if (position == 1)
                    sp2.setAdapter(adapter3);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //DATABASE**********************************************************************************
        //******************************************************************************************

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addPlano(new Planejamento(data2, sp1.getSelectedItem().toString() + " "
                        + sp2.getSelectedItem().toString()));

                livro = sp1.getSelectedItem().toString() + " Capítulo " +
                        sp2.getSelectedItem().toString() + " Adicionado na data: " + data2;

                Snackbar snackbar = Snackbar
                        .make(layout, livro, Snackbar.LENGTH_LONG);
                snackbar.show();
                Intent intent = new Intent(getApplicationContext(), Planos.class);
                startActivity(intent);
            }
        });

        list = db.getAllPlanos();

        lv = (ListView) findViewById(R.id.listEvento);
        ArrayAdapter<Planejamento> itemsAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(itemsAdapter);
        lv.deferNotifyDataSetChanged();
        registerForContextMenu(lv);
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

