package com.example.android.menudietas.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.menudietas.models.Contact;
import com.example.android.menudietas.database.DatabaseHelper;
import com.example.android.menudietas.R;

/**
 * Created by angelcastillo on 2/5/17.
 */

public class RegistroActivity extends Activity {

    private Button registro;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_layout);
    }

    public void registroClick(View v) {
        if (v.getId() == R.id.registro_boton) {


            EditText usuario = (EditText) findViewById(R.id.user);
            EditText email = (EditText) findViewById(R.id.email);
            EditText password = (EditText) findViewById(R.id.password);
            EditText repeat_pass = (EditText) findViewById(R.id.repeat);

            String usuariostr = usuario.getText().toString();
            String emailstr = email.getText().toString();
            String passwordstr = password.getText().toString();
            String repeat_passstr = repeat_pass.getText().toString();

            //COMPROBAR TODOS LOS CAMPOS ESTÉN ESCRITOS
            if (usuariostr.equals("")) {
                Toast pass = Toast.makeText(RegistroActivity.this, "El campo Usuario, está vacio.", Toast.LENGTH_SHORT);
                pass.show();
            } else if (emailstr.equals("")) {
                Toast pass = Toast.makeText(RegistroActivity.this, "El campo Email, está vacio.", Toast.LENGTH_SHORT);
                pass.show();
            } else if (passwordstr.equals("")) {
                Toast pass = Toast.makeText(RegistroActivity.this, "El campo Contraseña, está vacio.", Toast.LENGTH_SHORT);
                pass.show();
            } else if (!passwordstr.equals(repeat_passstr)) {
                Toast pass = Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                pass.show();
            } else {
                //CREAR EL NUEVO USUARIO EN LA BASE DE DATOS

                //TODO HACERLO CON UNA ASYNCTASK
                Contact c = new Contact(usuariostr, emailstr, passwordstr);

                Toast pass = Toast.makeText(RegistroActivity.this, "¡Ya estás registrado!", Toast.LENGTH_SHORT);
                pass.show();

                helper.createContact(c);

                //INTENT PARA QUE PASE AL ACTIVITY QUE QUEREMOS
                Intent registro = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(registro);

            }

        }

    }

}

