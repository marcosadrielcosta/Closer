package com.example.marco.biblia.Perfil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.marco.biblia.Login.Cadastro;
import com.example.marco.biblia.Login.Login;
import com.example.marco.biblia.Login.MySQLiteHelperCadastro;
import com.example.marco.biblia.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class EditarPerfil extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    MySQLiteHelperCadastro db;
    EditText nome, email, senha, novasenha;
    Button btSalvar, btMudar;
    ImageView imgPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        db = new MySQLiteHelperCadastro(this);

        nome = (EditText) findViewById(R.id.etNomeMudar);
        senha = (EditText) findViewById(R.id.etSenhaAtual);
        novasenha = (EditText) findViewById(R.id.etNovaSenha);
        email = (EditText) findViewById(R.id.etEmailMudar);
        btSalvar = (Button) findViewById(R.id.btSalvarEdit);
        btMudar = (Button) findViewById(R.id.btMudaSenha);


        SharedPreferences settings = getSharedPreferences(Login.PREFS_EMAIL, 0);
        final String emailUser = settings.getString("EmailUser", "bababa");

        SharedPreferences settings2 = getSharedPreferences(Cadastro.PREFS_EMAILCAD, 0);
        final String emailUser2 = settings2.getString("EmailUserCad", "bababa");

        SharedPreferences set = getSharedPreferences(Login.PREFS_NAME, 0);
        final boolean hasLoggedIn = set.getBoolean("hasLoggedIn", false);

        if (hasLoggedIn == true) {
            nome.setHint(db.getNomeUsuario(emailUser));
            email.setHint(db.getEmailUsuario(emailUser));
        }else{
            nome.setHint(db.getNomeUsuario(emailUser2));
            email.setHint(db.getEmailUsuario(emailUser2));
        }

        if (hasLoggedIn == true) {
            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nome.getText().toString().matches("")) {
                        if (email.getText().toString().matches("")) {
                            Toast.makeText(EditarPerfil.this, "Não houveram alterações.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            db.novoEmail(emailUser, email.getText().toString());
                            Toast.makeText(EditarPerfil.this, "Email alterado com sucesso.",
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {
                        if (email.getText().toString().matches("")) {
                            db.novoNome(emailUser, nome.getText().toString());
                            Toast.makeText(EditarPerfil.this, "Nome alterado com sucesso.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            db.novoEmail(emailUser, email.getText().toString());
                            db.novoNome(emailUser, nome.getText().toString());
                            Toast.makeText(EditarPerfil.this, "Alterações aplicadas com sucesso.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }else{
            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nome.getText().toString().matches("")) {
                        if (email.getText().toString().matches("")) {
                            Toast.makeText(EditarPerfil.this, "Não houveram alterações.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            db.novoEmail(emailUser2, email.getText().toString());
                            Toast.makeText(EditarPerfil.this, "Email alterado com sucesso.",
                                    Toast.LENGTH_LONG).show();
                        }

                    } else {
                        if (email.getText().toString().matches("")) {
                            db.novoNome(emailUser2, nome.getText().toString());
                            Toast.makeText(EditarPerfil.this, "Nome alterado com sucesso.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            db.novoEmail(emailUser2, email.getText().toString());
                            db.novoNome(emailUser2, nome.getText().toString());
                            Toast.makeText(EditarPerfil.this, "Alterações aplicadas com sucesso.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }

        btMudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasLoggedIn == true){
                    if(senha.getText().toString().equals(db.getSenhaUsuario(emailUser))){
                        db.novaSenha(emailUser, novasenha.getText().toString());
                        Toast.makeText(EditarPerfil.this, "Senha alterada com sucesso.",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(EditarPerfil.this, "Senha atual incorreta.",
                                Toast.LENGTH_SHORT).show();

                    }
                }else{
                    if(senha.getText().toString().equals(db.getSenhaUsuario(emailUser2))){
                        db.novaSenha(emailUser2, novasenha.getText().toString());
                        Toast.makeText(EditarPerfil.this, "Senha alterada com sucesso.",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(EditarPerfil.this, "Senha atual incorreta.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void clickPerfil(View v) {
        Intent i = new Intent(this, Perfil.class);
        startActivity(i);
    }
}
