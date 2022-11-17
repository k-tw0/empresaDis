package com.example.empresadis;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class ListView extends AppCompatActivity {

    android.widget.ListView listView;// Llamar la dependencia ListView y almacenarla en listView la variable

    String[] products = new String[]{"Caja de verduras", "Caja de frutas", "Caja de Mariscos", "Caja de lacteos", "Caja de Líquidos y Bebidas","Saco de 25kg de Harina"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView=findViewById(R.id.list_layout);// Establecer el identificador de el list_layout del XML

        //adapter es el nuevo objeto, el cual se establece una lista simple integrada y le integraremos el array products.
        ArrayAdapter adapter=new ArrayAdapter<String>(ListView.this, android.R.layout.simple_list_item_1,products);

        listView.setAdapter(adapter);//Setearemos el listView y le pasaremos el adapter

        /*Llamamos al método setOnItemClicListener de la clase ListView y le pasamos como parámetro una clase
        anónima que implementa la interfaz
        OnItemClickListener (dicha interfaz define el método onItemClick que se dispara cuando seleccionamos un elemento del ListView): lv1.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String st=listView.getAdapter().getItem(position).toString();// Se obtiene la posicion y el nombre de el arreglo seleccionado
                Log.i("lol", "posicion: " + products[position] + "pp:" + position);
                //Aqui se envia el nombre para la siguiente interfas
                Intent intent=new Intent(getApplicationContext(),ListViewSelect.class);
                intent.putExtra("name", st);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
    }
}