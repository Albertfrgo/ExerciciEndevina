package com.example.exerciciendevina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        vistaPista.setMovementMethod(new ScrollingMovementMethod());

//      De momento mostra el numero que s'ha d'endevinar
        vistaPista.setText(Integer.toString(numeroEndevinar));
    }

    public void premerBoto(View view) {
        String textToast="Boto apretat";
        Toast.makeText(getApplicationContext(),textToast,Toast.LENGTH_SHORT).show();
        Editable numeroEntrat= inputUsuari.getText();
        if(inputUsuari.getText().toString().equals("")==false){
            int numeroInt=Integer.parseInt(numeroEntrat.toString());
            contadorIntents++;
            vistaContador.setText(Integer.toString(contadorIntents));
            if(numeroInt==numeroEndevinar){
                vistaPista.setText("\nHAS ENCERTAT EL NUMERO!!!");
            }else if(numeroInt<numeroEndevinar){
                //vistaPista.setText("\nEl numero "+numeroEntrat+" es inferior");
                vistaPista.append("\nEl numero "+numeroEntrat+" es inferior");
                inputUsuari.setText("");
            }else if(numeroInt>numeroEndevinar) {
                //vistaPista.setText("\nEl numero "+numeroEntrat + " es superior");
                vistaPista.append("\nEl numero "+numeroEntrat + " es superior");
                inputUsuari.setText("");
        }

        }else{
        vistaPista.append("\nNo has entrat un numero");
        inputUsuari.setText("");
        }
    }
}