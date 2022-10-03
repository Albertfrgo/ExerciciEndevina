package com.example.exerciciendevina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {
    TextView vistaJugadors;
    Button botoTornar;
    Button botoEntrar;
    String ultimaPuntuacio;
    ArrayList<Jugador> llistaJugadors=new ArrayList<Jugador>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        llistaJugadors.add(new Jugador("Sergiio", "5"));
        llistaJugadors.add(new Jugador("Sergio", "6"));
        llistaJugadors.add(new Jugador("Edu", "4"));

        vistaJugadors=findViewById(R.id.vistaJugadors);
        vistaJugadors.setMovementMethod(new ScrollingMovementMethod());
        mostrarJugadors();

        Intent dadesMainAct=getIntent();
        ultimaPuntuacio=dadesMainAct.getStringExtra("contadorIntents");

        /*
        botoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("INFO", "S'ha apretat el boto de registrar la ultima puntuacio");
                llistaJugadors.add(new Jugador("Jugador R", ultimaPuntuacio));
            }
        });
         */

        /*
        botoTornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("INFO", "S'ha apretat el boto de tornar");
                Intent obrirJoc=new Intent(RecordsActivity.this, MainActivity.class);
                startActivity(obrirJoc);
            }
        });
        */
    }

    class Jugador{
        String nom;
        String intents;

        public Jugador(String nom, String intents){
            this.nom=nom;
            this.intents=intents;
        }

        public String getNom(){
            return nom;
        }

        public String getMinIntents(){
            return intents;
        }
    }

    public void tornarJoc (View view1){
        Log.i("INFO", "S'ha apretat el boto de tornar");
        Intent obrirJoc=new Intent(RecordsActivity.this, MainActivity.class);
        startActivity(obrirJoc);
    }

    public void entrarPuntuacio (View view1){
        Log.i("INFO", "S'ha apretat el boto de registrar la ultima puntuacio");
        llistaJugadors.add(new Jugador("Jugador R", ultimaPuntuacio));
        mostrarJugadors();
    }

    public void mostrarJugadors(){
        vistaJugadors.setText("");
        for(Jugador jug:llistaJugadors){
            vistaJugadors.append(jug.getNom()+" - "+jug.getMinIntents()+"\n\n");
        }
    }
}