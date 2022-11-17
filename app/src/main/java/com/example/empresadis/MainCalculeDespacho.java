package com.example.empresadis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainCalculeDespacho extends AppCompatActivity {
    private Button btnNotificar;

    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calcule_despacho);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("myCh", "My Channel", NotificationManager.IMPORTANCE_DEFAULT); // myCh es un identificador

            NotificationManager manager = getSystemService(NotificationManager.class);//Se axcede a una clase servicio externa
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myCh")// myCh es un identificador
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setContentTitle("Control de temperatura")// Titulo de notificacion
                .setContentText("Alerta: Check al container, excede el limite de grados permitidos."); //Descripcion notificacion

        notification = builder.build();

        notificationManagerCompat = NotificationManagerCompat.from(this); //Acceder a la variable

        btnNotificar= (Button) findViewById(R.id.btnNotification);

        btnNotificar.setOnClickListener(new View.OnClickListener() { // si surge un click en boton
            @Override
            public void onClick(View v) { nose(v); }// evento en la posicion v

        });
    }
    public void nose(View view){
            notificationManagerCompat.notify(1, notification);
    }
}