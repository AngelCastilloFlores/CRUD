package com.example.android.menudietas.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.android.menudietas.R;


/**
 * Created by angelcastillo on 4/5/17.
 */

public class PreferenciasActivity extends AppCompatActivity {


    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

    email = (EditText) findViewById(R.id.email);

    SharedPreferences prefe = getSharedPreferences("datos",Context.MODE_PRIVATE);
        email.setText(prefe.getString("mail",""));
}

   @Override
   public boolean onCreateOptionsMenu(Menu menu){
       getMenuInflater().inflate(R.menu.main, menu);
       return true;
   }



    public void Registrar(View view){
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefe.edit();
        editor.putString("mail", email.getText().toString());
        editor.commit();
        finish();
    }
}

