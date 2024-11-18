package com.example.chocostickersapp;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//Librerias de SQLite
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;




public class VentanaInventario extends AppCompatActivity {
    //Declarar variables
    Spinner spSpinner;
    String[] tamaño = new String[]{"Chico", "Mediano", "Grande"};
    EditText edtId, edtNom, edtPre, edtDes;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);


        //Defino los campos del formulario
        edtId = (EditText) findViewById(R.id.edtId);
        edtNom = (EditText) findViewById(R.id.edtNom);
        edtPre = (EditText) findViewById(R.id.edtPre);
        edtDes = (EditText) findViewById(R.id.edtDes);
        spSpinner = (Spinner) findViewById(R.id.spSpinner);
        lista = (ListView) findViewById(R.id.lstLista);


        //Poblar Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tamaño);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(spinnerAdapter);
        CargarLista();
    }


    public void CargarLista() {
        DataHelper dh = new DataHelper(this, "inventario.db", null, 2);
        SQLiteDatabase bd = dh.getWritableDatabase();
        Cursor c = bd.rawQuery("Select id, nombre, precio, descripcion, tamaño from inventario"
                , null);
        String[] arr = new String[c.getCount()];
        if (c.moveToFirst()) {
            int i = 0;
            do {
                String linea = "|| " + c.getInt(0) + " ||" + c.getString(1)
                        + " || " + c.getString(2) + " || "+ c.getString(3) + " || "+ c.getString(4) + " ||";
                arr[i] = linea;
                i++;
            } while (c.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_expandable_list_item_1, arr);
            lista.setAdapter(adapter);

            //cierre de recursos
            c.close();
            bd.close();
        }
    }
    public void onClickAgregar (View view){
        DataHelper dh=new DataHelper(this, "inventario.db", null, 2);
        SQLiteDatabase bd= dh.getWritableDatabase();
        ContentValues reg= new ContentValues();
        reg.put("id", edtId.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("precio", edtPre.getText().toString());
        reg.put("descripcion", edtDes.getText().toString());
        reg.put("tamaño", spSpinner.getSelectedItem().toString());
        long resp = bd.insert("inventario", null, reg);
        bd.close();
        if(resp==-1){
            Toast.makeText(this,"No se puede ingresar el producto", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Registro Agregado", Toast.LENGTH_LONG).show();
            CargarLista();
            limpiar();
        }

    }
    //Metodo para limpiar los campos de texto
    public void limpiar(){
        edtId.setText("");
        edtNom.setText("");
        edtPre.setText("");
        edtDes.setText("");
    }


    //Metodo para modificar un campo
    public void onClickModificar(View view) {
        String id = edtId.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(this, "El ID no puede estar vacío", Toast.LENGTH_LONG).show();
            return;
        }


        //Conexion a la base de datos para manipular los registros
        DataHelper dh = new DataHelper(this, "inventario.db", null, 2);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();
        //se envian los datos a modificar
        reg.put("id", edtId.getText().toString());
        reg.put("nombre", edtNom.getText().toString());
        reg.put("precio", edtPre.getText().toString());
        reg.put("descripcion", edtDes.getText().toString());
        reg.put("tamaño", spSpinner.getSelectedItem().toString());

        //Actualizo el registro de la base de datos por el Nombre
        long respuesta = bd.update("inventario", reg, "id=?", new String[]{edtId.getText().toString()});
        bd.close();
        //Envio la respuesta al usuario
        if (respuesta == -1){
            Toast.makeText(this,"Dato No Modificado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Dato Modificado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }

    //Metodo utilizado para Eliminar un Registro
    public void onClickEliminar(View view) {

        String id = edtId.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(this, "El ID no puede estar vacío", Toast.LENGTH_LONG).show();
            return;
        }


        //Conectamos la base de datos para eliminar un registro de tabla inventario
        DataHelper dh = new DataHelper(this, "inventario.db", null, 2);
        SQLiteDatabase bd = dh.getWritableDatabase();


        //Ingreso el id del registro a modificar
        String IdEliminar = edtId.getText().toString();


        //Query para eliminar el registro
        long respuesta = bd.delete("inventario", "id=?", new String[] {IdEliminar});
        bd.close();


        //Verifico que el registro se elimine
        if (respuesta == -1) {
            Toast.makeText(this, "Dato No Eliminado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Dato Eliminado", Toast.LENGTH_LONG).show();
        }
        limpiar();
        CargarLista();
    }


}




