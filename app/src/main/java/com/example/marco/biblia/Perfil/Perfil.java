package com.example.marco.biblia.Perfil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marco.biblia.Leitura.Livros;
import com.example.marco.biblia.Login.Cadastro;
import com.example.marco.biblia.Login.Login;
import com.example.marco.biblia.Login.MySQLiteHelperCadastro;
import com.example.marco.biblia.Notas.Lista;
import com.example.marco.biblia.Plan.Plan;
import com.example.marco.biblia.Principal;
import com.example.marco.biblia.R;
import com.example.marco.biblia.Teste;
import com.example.marco.biblia.VersiculoMarcado.VersiculosMarcados;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Perfil extends AppCompatActivity {
    public static final String PREFS_VER = "Ver";
    long lastUpdateTime = new Date().getTime();
    MySQLiteHelperCadastro db;
    String data;
    TextView tvNome, tvData, tvVersiculo;
    String randomStr;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        db = new MySQLiteHelperCadastro(this);

        tvVersiculo = (TextView) findViewById(R.id.tvVer);

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        //Toast.makeText(this, hour, Toast.LENGTH_SHORT).show();

        if(hour == 20 && minute == 0 && second == 30){
            String[] array = getApplicationContext().getResources().getStringArray(R.array.randomVer);
            randomStr = array[new Random().nextInt(array.length)];

            SharedPreferences set = getSharedPreferences(Perfil.PREFS_VER, 0);
            SharedPreferences.Editor ed = set.edit();
            ed.putString("Versiculo", randomStr);
            ed.commit();
        }

        SharedPreferences s8 = getSharedPreferences(Perfil.PREFS_VER, 0);
        String versiculo = s8.getString("Versiculo", "bababa");
        tvVersiculo.setText(versiculo);


        SharedPreferences settings = getSharedPreferences(Login.PREFS_EMAIL, 0);
        String emailUser = settings.getString("EmailUser", "bababa");

        SharedPreferences settings2 = getSharedPreferences(Cadastro.PREFS_EMAILCAD, 0);
        String emailUser2 = settings2.getString("EmailUserCad", "bababa");

        SharedPreferences set = getSharedPreferences(Login.PREFS_NAME, 0);
        boolean hasLoggedIn = set.getBoolean("hasLoggedIn", false);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        tvNome = (TextView) findViewById(R.id.nomeUser);
        tvData = (TextView) findViewById(R.id.dataUser);

        if (hasLoggedIn) {
            tvNome.setText(db.getNomeUsuario(emailUser));
            tvData.setText("Usuário desde: "+db.getDataUsuario(emailUser));
        }else{
            tvNome.setText(db.getNomeUsuario(emailUser2));
            tvData.setText("Usuário desde: "+db.getDataUsuario(emailUser2));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opcoes3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sair:

                SharedPreferences settings = getSharedPreferences(Login.PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("hasLoggedIn", false);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), Principal.class);
                startActivity(intent);

                return (true);

            case R.id.edit:
                Intent i = new Intent(getApplicationContext(), EditarPerfil.class);
                startActivity(i);

                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    public void clickVersiculos(View v) {
        Intent i = new Intent(this, VersiculosMarcados.class);
        startActivity(i);
    }

    public void clickTeste(View v) {
        Intent i = new Intent(this, Teste.class);
        startActivity(i);
    }

    public void clickEditar(View v) {
        Intent i = new Intent(this, EditarPerfil.class);
        startActivity(i);
    }

    public void clickPerfil(View v) {
        Intent i = new Intent(this, Perfil.class);
        startActivity(i);
    }

    public void clickLeitura(View v) {
        Intent i = new Intent(this, Livros.class);
        startActivity(i);
    }

    public void clickNotas(View v) {
        Intent i = new Intent(this, Lista.class);
        startActivity(i);
    }

    public void clickPlan(View v) {
        Intent i = new Intent(this, Plan.class);
        startActivity(i);
    }
}
