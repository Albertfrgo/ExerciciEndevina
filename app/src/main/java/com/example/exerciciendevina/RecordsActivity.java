package com.example.exerciciendevina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        ArrayList<Jugador> llistaJugadors=new ArrayList<Jugador>();
        llistaJugadors.add(new Jugador("Sergiio", 5));
        llistaJugadors.add(new Jugador("Sergio", 6));
        llistaJugadors.add(new Jugador("Edu", 4));

        TextView vistaJugadors=findViewById(R.id.vistaJugadors);
        for(Jugador jug:llistaJugadors){
            vistaJugadors.append(jug.getNom()+" - "+jug.getMinIntents()+"\n\n");
        }

        vistaJugadors.setMovementMethod(new ScrollingMovementMethod());
    }

    class Jugador{
        String nom;
        int intents;

        public Jugador(String nom, int intents){
            this.nom=nom;
            this.intents=intents;
        }

        public String getNom(){
            return nom;
        }

        public int getMinIntents(){
            return intents;
        }
    }

    public void premerBotoTornar (View view1){
        Log.i("INFO", "S'ha apretat el boto de tornar");
        Intent obrirJoc=new Intent(this, MainActivity.class);
        startActivity(obrirJoc);
    }
}