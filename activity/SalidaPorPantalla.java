package com.example.android.menudietas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.menudietas.R;
import com.example.android.menudietas.activity.CalculoActivity;

/**
 * Created by angelcastillo on 26/4/17.
 */

public class SalidaPorPantalla extends CalculoActivity {

    //se declaran las variables

    EditText edadTV, pesoET;
    Button btnAceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_dietas);

        //RELACIONAR LAS VARIABLES CON SUS ID
        edadTV = (EditText) findViewById(R.id.edadTv);
        pesoET = (EditText) findViewById(R.id.pesoET);

        btnAceptar = (Button) findViewById(R.id.btnSumar);

    }

    //RELACIONAR EL ONCLICK CON EL BOTÓN
    public void onClickAceptar(View view){
        //Toast.makeText(getApplicationContext(), "Siguiente", Toast.LENGTH_SHORT).show();


        String aux_edad = edadTV.getText().toString();
        String aux_peso = pesoET.getText().toString();



        Intent i = new Intent(this, CalculoActivity.class);

        i.putExtra("edad", aux_edad);
        i.putExtra("peso", aux_peso);
        startActivity(i);
    }

}





