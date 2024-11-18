package com.example.chocostickersapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.jetbrains.annotations.Nullable;




public class DataHelper extends SQLiteOpenHelper{
    public DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    //Creacion de la tabla Inventario en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE inventario(id INTEGER PRIMARY KEY,nombre TEXT, precio TEXT, descripcion TEXT, tamaño TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS inventario");
        db.execSQL("CREATE TABLE inventario(id INTEGER PRIMARY KEY, nombre TEXT, precio TEXT, descripcion TEXT, tamaño TEXT)");
    }
}