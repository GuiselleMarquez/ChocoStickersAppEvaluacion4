package com.example.chocostickersapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VentanaClave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);
    }


    //Boton hacia la ventana de inventario
        public void onClickCrud(View view) {
            Intent intent = new Intent(this, VentanaInventario.class);
            startActivity(intent);
        }




    }


