package com.example.marco.biblia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.marco.biblia.Login.Cadastro;
import com.example.marco.biblia.Login.Login;
import com.example.marco.biblia.Perfil.Perfil;

public class Principal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        SharedPreferences settings = getSharedPreferences(Login.PREFS_NAME, 0);
        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", true);

        if (hasLoggedIn) {
            Intent intent = new Intent();
            intent.setClass(Principal.this, Perfil.class);
            startActivity(intent);
            Principal.this.finish();
        }
    }

    public void carregaCadastro(View view) {
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
    }

    public void carregaLogin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

}
