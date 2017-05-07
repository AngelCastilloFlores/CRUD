package com.example.android.menudietas.models;

import android.app.Activity;
import android.widget.EditText;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

/**
 * Created by angelcastillo on 2/5/17.
 */

public class Contact{

    private int id;
    private String usuario;
    private String email;
    private  String password;

    public Contact() {
    }

    public Contact(String usuario, String email, String password) {
        this.usuario = usuario;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public String getUsuario(){
        return usuario;
    }


    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }


    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

}



