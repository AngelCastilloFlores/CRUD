package com.example.android.menudietas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.menudietas.R;

/**
 * Created by angelcastillo on 5/4/17.
 */

public class CalculoActivity extends AppCompatActivity {

    TextView edad_value, peso_value, altura_value, sexo_value, actividad_value, tmb_value, calorias_value;

    private Button crearDieta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.respuesta_dieta);

        sexo_value = (TextView) findViewById(R.id.sexo_value);
        edad_value = (TextView) findViewById(R.id.edad_value);
        peso_value = (TextView) findViewById(R.id.peso_value);
        altura_value = (TextView) findViewById(R.id.altura_value);
        actividad_value = (TextView) findViewById(R.id.actividad_value);

        tmb_value = (TextView) findViewById(R.id.tmb_value);
        calorias_value = (TextView) findViewById(R.id.calorias_value);


        //DATOS INTRODUCIDOS
        Bundle extras = getIntent().getExtras();

        String xsexo = extras.getString("calculo_sexo");
        int xedad = extras.getInt("calculo_edad");
        int xpeso = (int) extras.getFloat("calculo_peso", 0);
        int altura = (int) extras.getFloat("calculo_altura", 0);
        String xactividad = extras.getString("calculo_actividad");
        int tmb = (int) extras.getFloat("calculo_tmb", 0);
        int calorias = (int) extras.getFloat("calculo_calorias", 0);




        //DATOS INTRODUCIDOS
        sexo_value.setText(xsexo + "");
        edad_value.setText(xedad + " Años ");
        peso_value.setText(xpeso + " Kilos ");
        altura_value.setText(altura + " Cm ");
        actividad_value.setText(xactividad + "");
        tmb_value.setText(tmb + "");
        calorias_value.setText(calorias + " Kcal");

        crearDieta = (Button) findViewById(R.id.crear);
        crearDieta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //INTENT PARA QUE PASE AL ACTIVITY QUE QUEREMOS
                Intent crearDieta = new Intent(CalculoActivity.this, AlimentosActivity.class);
                startActivity(crearDieta);
            }
        });
    }
}



