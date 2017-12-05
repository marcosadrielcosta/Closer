package com.example.marco.biblia.Plan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.marco.biblia.Leitura.Livros;
import com.example.marco.biblia.Perfil.Perfil;
import com.example.marco.biblia.Notas.Lista;
import com.example.marco.biblia.R;

public class Plan extends AppCompatActivity {
    private static final String TAG = "Plan";
    public static String date;
    public static String date2;
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "/" + (i1 + 1) + "/" + i;
                Log.d(TAG, "onSelectedDayChange: dd/mm/yyyy:" + date);

                if(date.contains("/1/"))
                    date2 = i2 + " de janeiro de " +i;
                else if(date.contains("/2/"))
                    date2 = i2 + " de fevereiro de " +i;
                else if(date.contains("/3/"))
                    date2 = i2 + " de março de " +i;
                else if(date.contains("/4/"))
                    date2 = i2 + " de abril de " +i;
                else if(date.contains("/5/"))
                    date2 = i2 + " de maio de " +i;
                else if(date.contains("/6/"))
                    date2 = i2 + " de junho de " +i;
                else if(date.contains("/7/"))
                    date2 = i2 + " de julho de " +i;
                else if(date.contains("/8/"))
                    date2 = i2 + " de agosto de " +i;
                else if(date.contains("/9/"))
                    date2 = i2 + " de setembro de " +i;
                else if(date.contains("/10/"))
                    date2 = i2 + " de outubro de " +i;
                else if(date.contains("/11/"))
                    date2 = i2 + " de novembro de " +i;
                else if(date.contains("/12/"))
                    date2 = i2 + " de dezembro de " +i;
            }
        });
    }

    //ImageButtons******************************************************************************

    public void carregaLista(View v){
        Intent i = new Intent(this, ListaPlanos.class);
        startActivity(i);
    }

    public void carregaEvento(View v){
        if(date != null) {
            Intent i = new Intent(this, Planos.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Por favor, selecione uma data!", Toast.LENGTH_LONG).show();
        }
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
