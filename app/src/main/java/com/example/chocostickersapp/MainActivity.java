package com.example.chocostickersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Boton hacia la ventana de Registro
    public void onClickRegistro(View view) {
        Intent intent = new Intent(this, VentanaRegistro.class);
        startActivity(intent);
    }

    //Boton hacia la ventana login / inicio de sesion

    public void onClickLogin(View view) {
        Intent intent = new Intent(this, VentanaLogin.class);
        startActivity(intent);
    }

    //Boton hacia la ventana Comprar
    public void onClickcomprar(View view) {
        Intent intent = new Intent(this, VentanaComprar.class);
        startActivity(intent);
    }

    //Boton hacia la pagina del mapa de la tienda
    public void onClickMapa(View view) {
        Intent intent = new Intent(this, VentanaMapa.class);
        startActivity(intent);
    }

}