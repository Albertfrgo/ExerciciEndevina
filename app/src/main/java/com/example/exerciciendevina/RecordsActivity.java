package com.example.exerciciendevina;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RecordsActivity extends AppCompatActivity {
    TextView vistaJugadors;
    TextView ultimJoc;
    Button botoTornar;
    Button botoEntrar;
    boolean jocJugat;
    boolean llistaIniciada=false;
    String ultimaPuntuacio;
    ArrayList<Jugador> llistaJugadors=new ArrayList<Jugador>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        Intent dadesMainAct=getIntent();
        jocJugat=dadesMainAct.getBooleanExtra("partidaJugada", false);
        llistaIniciada=dadesMainAct.getBooleanExtra("llistaInciada", false);
        vistaJugadors=findViewById(R.id.vistaJugadors);
        vistaJugadors.setMovementMethod(new ScrollingMovementMethod());
        ultimJoc=findViewById(R.id.ultimJoc);
        botoEntrar=findViewById(R.id.botoEntrar);

        if(jocJugat==true){
            ultimaPuntuacio=dadesMainAct.getStringExtra("contadorIntents");
            ultimJoc.append(" "+ultimaPuntuacio);
        }else{
            ultimJoc.append(" "+"CAP RESULTAT");
        }
        if(llistaIniciada==true){
            llistaJugadors= (ArrayList<RecordsActivity.Jugador>) dadesMainAct.getSerializableExtra("llistaJugadors");
        }else{
            llistaJugadors.add(new Jugador("Irene", "7"));
            llistaJugadors.add(new Jugador("Sergio", "8"));
            llistaJugadors.add(new Jugador("Edu", "5"));
            llistaIniciada=true;
        }

        mostrarJugadors();

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
        public int getMinimInteger(){
            try{
                return Integer.parseInt(intents);
            }catch(Exception e){
                return 0;
            }
        }
    }

    class ordJugadorsIntents implements Comparator<Jugador>{
        public int compare(Jugador jug1, Jugador jug2) {
            if (jug1.getMinimInteger()>jug2.getMinimInteger()){
                return 1;
            }else if(jug1.getMinimInteger()<jug2.getMinimInteger()){
                return -1;
            }else{
                return 0;
            }
        }
    }

    public void tornarJoc (View view1){
        Log.i("INFO", "S'ha apretat el boto de tornar");
        Intent obrirJoc=new Intent(RecordsActivity.this, MainActivity.class);
        obrirJoc.putExtra("llistaJugadors", llistaJugadors);
        obrirJoc.putExtra("llistaIniciada", llistaIniciada);
        startActivity(obrirJoc);
    }

    public void entrarPuntuacio (View view1){
        if(jocJugat==true){
            Log.i("INFO", "S'ha apretat el boto de registrar la ultima puntuacio");
            AlertDialog.Builder demanarNom=new AlertDialog.Builder(RecordsActivity.this);
            demanarNom.setMessage("Introdueix el teu nom");
            EditText campNom=new EditText(this);
            demanarNom.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String nomJugador=campNom.getText().toString();
                    Jugador jugadorAfegir=new Jugador(nomJugador, ultimaPuntuacio);
                    llistaJugadors.add(jugadorAfegir);
                    mostrarJugadors();
                    jocJugat=false;
                }
            });
            demanarNom.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            demanarNom.setView(campNom);
            demanarNom.show();
        }else{
            AlertDialog.Builder missatgeJocEntrat=new AlertDialog.Builder(RecordsActivity.this);
            missatgeJocEntrat.setMessage("No hi han dades per entrar");
            missatgeJocEntrat.setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            missatgeJocEntrat.show();
        }
    }

    public void mostrarJugadors(){
        vistaJugadors.setText("");
        /*for(int i=0;i<llistaJugadors.size();i++){
            for(int j=0;j<llistaJugadors.size();j++){
                if(Integer.parseInt(llistaJugadors.get(j+1).getMinIntents())< Integer.parseInt(llistaJugadors.get(j).getMinIntents())){
                    Jugador jug=llistaJugadors.get(j);
                    llistaJugadors.set(j)=llistaJugadors.get(j+1);
                }
            }
        }*/
        Collections.sort(llistaJugadors, new ordJugadorsIntents());
        for(Jugador jug:llistaJugadors){
            vistaJugadors.append(jug.getNom()+" - "+jug.getMinIntents()+"\n\n");
        }
    }
}