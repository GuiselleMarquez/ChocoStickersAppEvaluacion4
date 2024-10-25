package com.example.chocostickersapp;

//librerias
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;


import androidx.appcompat.app.AppCompatActivity;

public class VentanaMapa extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_mapa);

        //Cargar las configuraciones del mapa usasndo las preferencias predeterminadas.
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        //Obtiene la referencia al componente Mapview del layout
        MapView mapView = findViewById(R.id.mapView);

        //Establece la fuente de los mapas a utilizar en el MapView, en este caso el mapa predeterminado de OpenStreetMap (MAPNIK)
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        //Activa los controles de zoom integrados en el MapView
        mapView.setBuiltInZoomControls(true);

        //Habilita los controles multitáctiles para permitir gestos como el zoom con dos dedos
        mapView.setMultiTouchControls(true);


        //coordenadas de nuestra tienda
        double tiendaLatitud = -33.443960870466526; //Latitud de nuestra tienda
        double tiendaLongitud = -70.65371178507924; // Longitud de nuestra tienda


        //GeoPoint para las coordenadas de nuestra tienda
        GeoPoint TiendaPoint = new GeoPoint(tiendaLatitud, tiendaLongitud);
        //Configuramos la vista incial con un nivel de zoom de 20.
        mapView.getController().setZoom(17.0);
        //centramos el mapa en el punto de nuestra tienda
        mapView.getController().setCenter(TiendaPoint);

        // creamos los marcadores que nos señalaran en el mapa
        Marker marcadorTienda = new Marker(mapView);
        marcadorTienda.setPosition(TiendaPoint);
        marcadorTienda.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        //titulo del marcador
        marcadorTienda.setTitle("Nuestra tienda!");
        marcadorTienda.setSnippet("A pasos del metro moneda");
        //agregamos los marcadores al mapa
        mapView.getOverlays().add(marcadorTienda);



        //---agregamos un segundo marcador para indicar la cercania con el metro ---

        //coordenadas metro
        double metroLatitud = -33.444877320495436;
        double metroLongitud = -70.65481939946211;
        //objeto geopoint y marcadores
        GeoPoint metroPoint = new GeoPoint(metroLatitud, metroLongitud);
        Marker marcadorMetro = new Marker(mapView);
        marcadorMetro.setPosition(metroPoint);
        marcadorMetro.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcadorMetro.setTitle("Metro La Moneda");
        mapView.getOverlays().add(marcadorMetro);

        //creamos una linea que una los dos marcadores
        Polyline linea = new Polyline();
        linea.addPoint(TiendaPoint);
        linea.addPoint(metroPoint);
        linea.setColor(0xFF0000FF);
        linea.setWidth(5);
        mapView.getOverlayManager().add(linea);


    }
}
