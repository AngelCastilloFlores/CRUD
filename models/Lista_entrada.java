package com.example.android.menudietas.models;

/**
 * Created by angelcastillo on 30/3/17.
 */

public class Lista_entrada {
    private int idImagen;
    private String textoEncima;
    private String textoDebajo;

    public Lista_entrada (int idImagen, String textoEncima, String textoDebajo) {
        this.idImagen = idImagen;
        this.textoEncima = textoEncima;
        this.textoDebajo = textoDebajo;
    }

    public Lista_entrada(int nula, String s) {

    }

    public String get_textoEncima() {
        return textoEncima;
    }

    public String get_textoDebajo() {
        return textoDebajo;
    }

    public int get_idImagen() {
        return idImagen;
    }
}