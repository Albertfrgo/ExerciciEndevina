package com.example.exerciciendevina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int contadorIntents=0;
    Random aleatori=new Random();
    boolean jocActiu=true;
    int numeroEndevinar= aleatori.nextInt(100);
    String numEndStr=String.valueOf(numeroEndevinar);
    //Per poder utilitzarlos primer instancia els EditText i TextView fora del onCreate i alla els defineixo
    EditText inputUsuari;
    TextView vistaContador;
    TextView vistaPista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsuari=findViewById(R.id.inputUsuari);
        vistaContador=findViewById(R.id.contIntents);
        vistaContador.setText(Integer.toString(contadorIntents));
        vistaPista=findViewById(R.id.pistaText);

//      De momento mostra el numero que s'ha d'endevinar
        vistaPista.setText(Integer.toString(numeroEndevinar));
    }

    public void premerBoto(View view) {

        try{
            String numeroEntrat= inputUsuari.getText().toString();
            int numeroInt=Integer.parseInt(numeroEntrat);
            contadorIntents++;
            vistaContador.setText(Integer.toString(contadorIntents));
            if(numeroInt==numeroEndevinar){
                vistaPista.setText("\nHAS ENCERTAT EL NUMERO!!!");
            }else if(numeroInt<numeroEndevinar){
                vistaPista.setText("\nEl numero "+numeroEntrat+" es inferior");
            }else if(numeroInt>numeroEndevinar) {
                vistaPista.setText("\nEl numero "+numeroEntrat + " es superior");
            }

        }catch (NumberFormatException e) {
            vistaPista.setText("\nNo has entrat un numero");
        }
    }
}