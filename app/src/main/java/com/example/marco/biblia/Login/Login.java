package com.example.marco.biblia.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.marco.biblia.Perfil.Perfil;
import com.example.marco.biblia.Principal;
import com.example.marco.biblia.R;

public class Login extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String PREFS_EMAIL = "EmailUser";
    public static String vEmail, vSenha;
    MySQLiteHelperCadastro db;
    Button bt;
    EditText email, senha;
    Boolean isFirstTime;
    String nome, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new MySQLiteHelperCadastro(this);

        bt = (Button) findViewById(R.id.btEntrar);
        email = (EditText) findViewById(R.id.lgEmail);
        senha = (EditText) findViewById(R.id.lgSenha);



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.validaLogin(email.getText().toString(), senha.getText().toString()) != 0) {

                    SharedPreferences settings = getSharedPreferences(Login.PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("hasLoggedIn", true);
                    editor.commit();

                    SharedPreferences set = getSharedPreferences(Login.PREFS_EMAIL, 0);
                    SharedPreferences.Editor ed = set.edit();
                    ed.putString("EmailUser", email.getText().toString());
                    ed.commit();

                    Intent intent = new Intent(getApplicationContext(), Perfil.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Email ou Senha incorretos!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void carregaEntrada(View view) {
        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }

    public void carregaPrincipal(View view) {
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);
    }
}
