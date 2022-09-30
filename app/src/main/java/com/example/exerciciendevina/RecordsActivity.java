package com.example.exerciciendevina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
}