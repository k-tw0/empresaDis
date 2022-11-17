package com.example.empresadis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Menu principal(ATRIBUTOS)
    private EditText mail, contr;
    private String Admin = "root";
    private String AdminPass = "123";

    private String GestionUser = "gestion";
    private String GestionPass = "123";

    private String User = "usuario";
    private String UserPass = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail = (EditText)findViewById(R.id.editTextTextEmailAddress);
        contr = (EditText)findViewById(R.id.editTextTextPassword);

        Button btnLogin =  (Button) findViewById(R.id.btnLogin); // OBTENER BOTON
        Button btnRegister =  (Button) findViewById(R.id.btnReg); // OBTENER BOTON

        btnLogin.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                validar(v);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { // evento en la posicion v
                register(v);
            }
        });
    }
    public void validar (View view) {
        String obtenerUser, obtenerPass;

        obtenerUser = mail.getText().toString();
        obtenerPass = contr.getText().toString();

        if(obtenerUser.length() != 0) { // SI ES QUE EXISTE ALGUN CARACTER
            if (obtenerUser.equals(Admin)) { // SI, CAMPO OBTENER USER ES IGUAL AL USER 'root'
                if (obtenerPass.length() != 0) { // SI EXISTE ALGUN CARACTER EN LA CONTRASENIA
                    if (obtenerPass.equals(AdminPass)) {//  SI, CAMPO OBTENER PASSWORD ES IGUAL AL PASSWORD
                        Toast.makeText(this, "Ingresaste a la cuenta Administradora", Toast.LENGTH_LONG).show(); // MENSAJE DE LOGIN

                        Intent i = new Intent(MainActivity.this, MainAdministration.class);
                        startActivity(i);

                    }
                }
            }
        }
        if(obtenerUser.length() != 0) { // SI ES QUE EXISTE ALGUN CARACTER
            if (obtenerUser.equals(GestionUser)) { // SI, CAMPO OBTENER USER ES IGUAL AL USER 'root'
                if (obtenerPass.length() != 0) { // SI EXISTE ALGUN CARACTER EN LA CONTRASENIA
                    if (obtenerPass.equals(GestionPass)) {//  SI, CAMPO OBTENER PASSWORD ES IGUAL AL PASSWORD
                        Toast.makeText(this, "Ingresaste a la cuenta de gestion", Toast.LENGTH_LONG).show(); // MENSAJE DE LOGIN

                        Intent i = new Intent(MainActivity.this, MainGestionDespacho.class);
                        startActivity(i);

                    }
                }
            }
        }
        if(obtenerUser.length() != 0) { // SI ES QUE EXISTE ALGUN CARACTER
            if(obtenerUser.equals(User)){ // SI, CAMPO OBTENER USER ES IGUAL AL USER 'root'
                if(obtenerPass.length() != 0){ // SI EXISTE ALGUN CARACTER EN LA CONTRASENIA
                    if(obtenerPass.equals(UserPass)){//  SI, CAMPO OBTENER PASSWORD ES IGUAL AL PASSWORD
                        Toast.makeText(this, "Ingresaste a la cuenta", Toast.LENGTH_LONG).show(); // MENSAJE DE LOGIN

                        Intent i = new Intent(MainActivity.this, MainMenu.class);
                        startActivity(i);

                    }else {
                        Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show(); // Mensaje de error de contrasenia
                    }
                }else {
                    Toast.makeText(this, "Password no puede tener campos vacios", Toast.LENGTH_LONG).show(); // CAPTURAR VACIO EN EL INPUT
                }

            }else {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show(); // Mensaje de error de usuario incorrecto
            }
        }else{
            Toast.makeText(this, "Usuario no puede tener campos vacios", Toast.LENGTH_LONG).show(); // CAPTURAR VACIO EN EL INPUT
        }
    }
    public void register(View view) {
        Intent i = new Intent(MainActivity.this, MainRegisiter.class);
        startActivity(i);
    }
}