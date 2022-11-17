package com.example.empresadis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainRegisiter extends AppCompatActivity {
    private EditText regGmail, regPass, regPass2;
    private Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_regisiter);

        regGmail = (EditText)findViewById(R.id.editTextRegGmail);

        regPass = (EditText)findViewById(R.id.editTextPassGmail);
        regPass2 = (EditText)findViewById(R.id.editTextPassGmail2);
        btnReg = (Button)findViewById(R.id.btnReg);

        btnReg.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                validaReg(v);
            }
        });
    }
    public void validaReg (View view) {
        String obtGmail, obtPassReg, obtPassReg2;

        obtGmail = regGmail.getText().toString();
        obtPassReg = regPass.getText().toString();
        obtPassReg2 = regPass2.getText().toString();

        if (obtGmail.length() != 0 && obtPassReg.length() != 0 && obtPassReg2.length() != 0 ) { // SI ES QUE EXISTE ALGUN CARACTER
            if(obtPassReg.equals(obtPassReg2)){// SI ES QUE LA PASSWORD ES IGUAL A LA ANTERIOR
                Intent i = new Intent(MainRegisiter.this, MainMenu.class);
                startActivity(i);
            }
            else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();// SI ES QUE EXISTE ALGUN CARACTER
            }
        }else{
            Toast.makeText(this, "No pueden existir campos vacios", Toast.LENGTH_LONG).show();
        }
    }


}