package com.example.chocostickersapp;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

//librerias de video
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.Toast;

//librerias para el enlace web
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VentanaMultimedia extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);


    //Configuracion para reproducir un video local
    VideoView videoView = findViewById(R.id.videoView);

    String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.trailer;
    Uri uri = Uri.parse(videoPath);
    videoView.setVideoURI(uri);

    //agregar controles de reproduccion al video
    MediaController mediaController = new MediaController(this);
    videoView.setMediaController(mediaController);
    mediaController.setAnchorView(videoView);
    videoView.start();


    //configuracion del webview para cargar un video desde youtube.
    WebView webView = findViewById(R.id.webView);
    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    String videoUrl = "https://www.youtube.com/watch?v=jqOx32Cv-XI";
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl(videoUrl);

    }

}
