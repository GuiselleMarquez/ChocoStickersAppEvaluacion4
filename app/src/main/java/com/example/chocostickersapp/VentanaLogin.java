package com.example.chocostickersapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VentanaLogin extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //Metodo para el boton inventario//
    public void onClickInventario(View view) {
        Intent intent = new Intent(this, VentanaClave.class);
        startActivity(intent);
    }


}
