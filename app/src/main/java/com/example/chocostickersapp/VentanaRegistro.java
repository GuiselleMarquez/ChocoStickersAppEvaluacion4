package com.example.chocostickersapp;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VentanaRegistro extends AppCompatActivity
{
    //Se declaran las variables para enlazarlos con los Id's
    private TextView textView;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro); // Cargar el layout activity_registro.xml


        //enlazamos con los Id's
        textView = findViewById(R.id.texto1);
        imageView = findViewById(R.id.nuevo);
        progressBar = findViewById(R.id.barraDeProgreso);
        Button botonRegistro = findViewById(R.id.botonRegistro);

        botonRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //mostrar la progressbar
                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);

                //ejecutar el thread
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        //simulamos una operacion larga
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        //actualizamos la UI en el hilo principal para ocultare el progressbar
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                textView.setText("Usuario creado con exito!");

                            }
                        });
                    }
                }).start();
            }

        });

    }
}

