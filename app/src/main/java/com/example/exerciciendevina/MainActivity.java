package com.example.exerciciendevina;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int contadorIntents=0;
    private Random aleatori=new Random();
    private boolean jocActiu=true;
    private int numeroEndevinar= aleatori.nextInt(100);;
    private String numEndStr=String.valueOf(numeroEndevinar);
    private EditText inputUsuari;
    private TextView vistaContador;
    private TextView vistaPista;
    private AlertDialog.Builder constructorDialeg;
    private AlertDialog dialeg;
    private boolean partidaJugada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        partidaJugada=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsuari=findViewById(R.id.inputUsuari);
        vistaContador=findViewById(R.id.contIntents);
        vistaContador.setText(Integer.toString(contadorIntents));
        vistaPista=findViewById(R.id.pistaText);
        vistaPista.setMovementMethod(new ScrollingMovementMethod());

        constructorDialeg=new AlertDialog.Builder(MainActivity.this);
        constructorDialeg.setMessage("Has endevinat el numero").setTitle("CORRECTE").setPositiveButton("ACCEPTAR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                int numeroEndevinar= aleatori.nextInt(100);
                vistaPista.setText("");
            }
        });

        dialeg=constructorDialeg.create();

//      Es pot configurar perque mostri el numero a endevinar per probar
        vistaPista.setText("");
    }

    //INTENTAR ENDEVINAR
    public void premerBoto(View view) {
        if(jocActiu){
            Editable numeroEntrat= inputUsuari.getText();
            if(inputUsuari.getText().toString().equals("")==false){
                int numeroInt=Integer.parseInt(numeroEntrat.toString());
                contadorIntents++;
                vistaContador.setText(Integer.toString(contadorIntents));
                if(numeroInt==numeroEndevinar){
                    vistaPista.setText("\nHAS ENCERTAT EL NUMERO!!!");
                    jocActiu=false;
                    dialeg.show();
                    partidaJugada=true;
                }else if(numeroInt<numeroEndevinar){
                    //vistaPista.setText("\nEl numero "+numeroEntrat+" es inferior");
                    vistaPista.append("\nEl numero "+numeroEntrat+" es inferior");
                    Toast.makeText(getApplicationContext(),"El numero "+numeroEntrat+" es inferior",Toast.LENGTH_SHORT).show();
                    inputUsuari.setText("");
                }else if(numeroInt>numeroEndevinar) {
                    //vistaPista.setText("\nEl numero "+numeroEntrat + " es superior");
                    vistaPista.append("\nEl numero "+numeroEntrat + " es superior");
                    Toast.makeText(getApplicationContext(),"El numero "+numeroEntrat + " es superior",Toast.LENGTH_SHORT).show();
                    inputUsuari.setText("");
                }

            }else{
                vistaPista.append("\nNo has entrat un numero");
                inputUsuari.setText("");

            }
        }
    }

    //CANVI ACTIVITAT I ENVIAR DADES A RECORDS ACTIVITY
    public void premerRanking(View view1){
        Log.i("INFO", "S'ha apretat el boto de ranking");
        Intent obrirRecords=new Intent(this, RecordsActivity.class);
        String contString=Integer.toString(contadorIntents);
        if (partidaJugada==true){
            obrirRecords.putExtra("partidaJugada", partidaJugada);
            obrirRecords.putExtra("contadorIntents", contString);
        }
        startActivity(obrirRecords);
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

    public ArrayList<String> obtenirNoms(ArrayList<Jugador> llistaJugadors){
        ArrayList<String> nomsJugadors=new ArrayList<String>();
        for(int i=0;i<llistaJugadors.size();i++){
            nomsJugadors.add(llistaJugadors.get(i).getNom());
        }
        return nomsJugadors;
    }

    public ArrayList<String> obtenirIntents(ArrayList<Jugador> llistaJugadors){
        ArrayList<String> intentsJugadors=new ArrayList<String>();
        for(int i=0;i<llistaJugadors.size();i++){
            intentsJugadors.add(llistaJugadors.get(i).getMinIntents());
        }
        return intentsJugadors;
    }

    public ArrayList<Jugador> unirDadesJugadors(ArrayList<String> nomsJugadors, ArrayList<String> intentsJugadors) {
        ArrayList<Jugador> llistaJugadors=new ArrayList<Jugador>();
        for(int i=0;i<nomsJugadors.size();i++){
            llistaJugadors.add(new Jugador(nomsJugadors.get(i), intentsJugadors.get(i)));
        }
        return llistaJugadors;
    }
}

