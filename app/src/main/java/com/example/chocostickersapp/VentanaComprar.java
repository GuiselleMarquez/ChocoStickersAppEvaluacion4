package com.example.chocostickersapp;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class VentanaComprar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventanacomprar);
    }

    public void onClickmultimedia(View view) {
        Intent intent = new Intent(this, VentanaMultimedia.class);
        startActivity(intent);


        //Configuracion del boton que sonará
        findViewById(R.id.comprar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(VentanaComprar.this, R.raw.dinero);
                mediaPlayer.start();

                //listener para liberar los recursos del MediaPlayer cuando el sonido deja de sonar
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });


            }
        });
    }
}