package com.example.empresadis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ListViewSelect extends AppCompatActivity {

    TextView txt1, txt2, txtTarifa, txtPriceTotal;
    EditText editKilometro;
    Button btnCalculePrice;
    Integer[] prices = new Integer[]{30000, 25000, 49999, 55000, 40000, 15000 };
    Integer[] tarifas = new Integer[]{0, 150, 300};
    Integer[] maxPrice = new Integer[] {24999, 25000, 49999, 50000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_select);

        txt1=findViewById(R.id.textViewSelect);// Captar textView
        txt2=findViewById(R.id.textViewPrice);// Captar textView
        editKilometro=findViewById(R.id.editTextKilometro);
        txtTarifa=findViewById(R.id.textViewTarifa);
        txtPriceTotal=findViewById(R.id.textViewPriceSelect);

        btnCalculePrice=findViewById(R.id.buttonPrecio);



        txt1.setText(getIntent().getExtras().getString("name"));// Obtener valor String de ListView
        int options= getIntent().getIntExtra("pos",0);// Obtener valor Integer del listView

        btnCalculePrice.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                switch(options) {
                    case 0:  crack(prices[0],tarifas[1]); break;// Desde aqui se setean los valores, Herencia.
                    case 1:  crack(prices[1],tarifas[1]); break;
                    case 2:  crack(prices[2],tarifas[1]); break;
                    case 3:  crack(prices[3],tarifas[0]); break;
                    case 4:  crack(prices[4],tarifas[1]); break;
                    case 5:  crack(prices[5],tarifas[2]); break;
                }
            }
        });

        Integer i;
        switch (options) {
            case 0:
                i = prices[0];
                txt2.setText("$ " + i);
                txtTarifa.setText("$ "+ tarifas[1]);
                break;
            case 1:
                i = prices[1];
                txt2.setText("$ " + i);
                txtTarifa.setText("$ "+ tarifas[1]);
                Log.i("lol", "El precio es: " + i);
                break;
            case 2:
                i = prices[2];
                txt2.setText("$ " + i);
                txtTarifa.setText("$ "+ tarifas[1]);
                break;
            case 3:
                i = prices[3];
                txt2.setText("$ " + i);
                txtTarifa.setText("$ "+ tarifas[0]);
                break;
            case 4:
                i = prices[4];
                txt2.setText("$ " + i);
                txtTarifa.setText("$ "+ tarifas[1]);
                break;
            case 5:
                i = prices[5];
                txt2.setText("$ " + i);
                txtTarifa.setText("$ "+ tarifas[2]);
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }
    }

    public void crack(int price, int tarifa){
        String objectKilometraje;
        objectKilometraje = editKilometro.getText().toString();

        if(objectKilometraje.length() != 0){
            if (price <= maxPrice[0]) {// SI EL PRECIO ES MENOR O IGUAL QUE 24999
                if(tarifa == tarifas[2]){// Desde aqui se controla la entrada de la tarifa
                    int num = Integer.parseInt(objectKilometraje);
                    // Hacer -- multiplication
                    int formula = (tarifas[2] * num) + price;
                    txtPriceTotal.setText("$ " + formula);
                }
            }
            if (price >= maxPrice[1] || price <= maxPrice[2]) {// SI EL PRECIO ES MAYOR O IGUAL QUE 25000 || SI EL PRECIO ES MENOR O IGUAL QUE 49999
                if(tarifa == tarifas[1]){// Desde aqui se controla la entrada de la tarifa
                    int num = Integer.parseInt(objectKilometraje);
                    // Hacer -- multiplication
                    int formula = (tarifas[1] * num) + price;
                    txtPriceTotal.setText("$ " + formula);
                }
            }
            if (price >= maxPrice[3]) {// SI EL PRECIO ES MAYOR O IGUAL QUE 25000 || SI EL PRECIO ES MENOR O IGUAL QUE 49999
                if(tarifa == tarifas[0]){// Desde aqui se controla la entrada de la tarifa
                    int num = Integer.parseInt(objectKilometraje);
                    // Hacer -- multiplication
                    int formula = (tarifas[0] * num) + price;
                    txtPriceTotal.setText("$ " + formula);
                }
            }
        }else{
            Toast.makeText(this, "Escribir contenido", Toast.LENGTH_LONG).show();// SI ES QUE EXISTE ALGUN CARACTER
        }

    }

}