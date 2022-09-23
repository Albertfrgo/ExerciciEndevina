package com.example.exerciciendevina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random aleatori=new Random();

        int contadorIntents=0;

        boolean jocActiu=true;

        EditText inputUsuari=findViewById(R.id.inputUsuari);
        String numeroIntent=inputUsuari.toString();

        TextView vistaContador=findViewById(R.id.contIntents);
        vistaContador.setText(Integer.toString(contadorIntents));
        TextView vistaPista=findViewById(R.id.pistaText);

        int numeroEndevinar= aleatori.nextInt(100);
        vistaPista.setText(Integer.toString(numeroEndevinar));

    }
}