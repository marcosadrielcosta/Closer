package com.example.marco.biblia.Leitura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.marco.biblia.Notas.Lista;
import com.example.marco.biblia.Perfil.Perfil;
import com.example.marco.biblia.Plan.Plan;
import com.example.marco.biblia.R;

public class Livros extends AppCompatActivity {
    ListView lv;
    Button bt1, bt2;
    public static String livro;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros);

        lv = (ListView) findViewById(R.id.lvLivros);
        bt1 = (Button) findViewById(R.id.btVelho);
        bt2 = (Button) findViewById(R.id.btNovo);


        final ArrayAdapter<CharSequence> adVelho = ArrayAdapter.createFromResource
                (this, R.array.velho, android.R.layout.simple_list_item_1);

        final ArrayAdapter<CharSequence> adNovo = ArrayAdapter.createFromResource
                (this, R.array.novo, android.R.layout.simple_list_item_1);

        lv.setAdapter(adVelho);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.setAdapter(adVelho);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.setAdapter(adNovo);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        livro = "genesis";
                        intent = new Intent(getApplicationContext(), Leitura.class);
                        startActivity(intent);
                        break;
                    case 1:
                        livro = "exodo";
                        intent = new Intent(getApplicationContext(), Leitura.class);
                        startActivity(intent);
                }
            }
        });
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
