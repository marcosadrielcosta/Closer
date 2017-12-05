package com.example.marco.biblia.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.marco.biblia.Perfil.Perfil;
import com.example.marco.biblia.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Cadastro extends AppCompatActivity {
    public static final String PREFS_EMAILCAD = "EmailUserCad";
    public static String data;
    MySQLiteHelperCadastro db;
    EditText etNome, etEmail, etSenha, etSenha2;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        db = new MySQLiteHelperCadastro(this);

        etNome = (EditText) findViewById(R.id.cdNome);
        etEmail = (EditText) findViewById(R.id.cdEmail);
        etSenha = (EditText) findViewById(R.id.cdSenha);
        etSenha2 = (EditText) findViewById(R.id.cdSenha2);
        bt = (Button) findViewById(R.id.btCadastrar);

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yy");
        data = sdf.format(date);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etSenha.getText().toString().equals(etSenha2.getText().toString())){
                    db.addUser(new Usuario(etNome.getText().toString(),
                            etEmail.getText().toString(), etSenha.getText().toString(),data));

                    SharedPreferences set = getSharedPreferences(Cadastro.PREFS_EMAILCAD, 0);
                    SharedPreferences.Editor ed = set.edit();
                    ed.putString("EmailUserCad", etEmail.getText().toString());
                    ed.commit();

                    Intent intent = new Intent(getApplicationContext(), Perfil.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void carregaEntrada(View view){
        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }
}
