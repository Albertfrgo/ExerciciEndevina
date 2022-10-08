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
    int contadorIntents=0;
    Random aleatori=new Random();
    boolean jocActiu=true;
    int numeroEndevinar= aleatori.nextInt(100);;
    String numEndStr=String.valueOf(numeroEndevinar);
    //Per poder utilitzarlos primer instancia els EditText i TextView fora del onCreate i alla els defineixo
    EditText inputUsuari;
    TextView vistaContador;
    TextView vistaPista;
    AlertDialog.Builder constructorDialeg;
    AlertDialog dialeg;
    boolean partidaJugada;

    boolean llistaIniciada=false;
    ArrayList<RecordsActivity.Jugador> llistaJugadors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        partidaJugada=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent dadesActivity=getIntent();
        llistaIniciada=dadesActivity.getBooleanExtra("llistaIniciada", false);
        if(llistaIniciada==true){
            llistaJugadors= (ArrayList<RecordsActivity.Jugador>) dadesActivity.getSerializableExtra("llistaJugadors");
        }

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

    public void premerRanking(View view1){
        Log.i("INFO", "S'ha apretat el boto de ranking");
        Intent obrirRecords=new Intent(this, RecordsActivity.class);
        String contString=Integer.toString(contadorIntents);
        if (partidaJugada==true){
            obrirRecords.putExtra("partidaJugada", partidaJugada);
            obrirRecords.putExtra("contadorIntents", contString);
            obrirRecords.putExtra("llistaIniciada", llistaIniciada);
            if(llistaIniciada==true){
                obrirRecords.putExtra("llistaJugadors", llistaJugadors);
            }
        }
        startActivity(obrirRecords);

    }

}

