package com.umdsg.creandosoporteandroidwear;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NotificationHelper{


    private static final String CHANNEL_ID = "my_channel_id";

    @SuppressLint("MissingPermission")
    public static void showNotification(Context context) {
        // Intent para abrir el perfil del usuario
        Intent intentProfile = new Intent(context, MainActivity.class); // Ajusta esto a la actividad que deseas abrir
        PendingIntent pendingIntentProfile = PendingIntent.getActivity(context, 0, intentProfile, PendingIntent.FLAG_UPDATE_CURRENT);

        // Intent para seguir/desseguir al usuario (realizar el Endpoint)
        Intent intentFollow = new Intent(context, FollowActionService.class); // Servicio para ejecutar el follow/unfollow
        PendingIntent pendingIntentFollow = PendingIntent.getService(context, 0, intentFollow, PendingIntent.FLAG_UPDATE_CURRENT);

        // Intent para ver las fotos recientes del usuario
        Intent intentViewUser = new Intent(context, UserPhotosActivity.class); // Actividad para ver fotos recientes
        PendingIntent pendingIntentViewUser = PendingIntent.getActivity(context, 0, intentViewUser, PendingIntent.FLAG_UPDATE_CURRENT);

        // Crear la notificación
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Nueva actividad en tu foto")
                .setContentText("Alguien ha raiteado tu foto.")
                .setSmallIcon(R.drawable.baseline_notifications_24)  // Reemplaza con el ícono que desees
                .addAction(R.drawable.baseline_person_24, "Ver mi perfil", pendingIntentProfile)  // Acción de ver el perfil
                .addAction(R.drawable.baseline_add_24, "Dar Follow/Unfollow", pendingIntentFollow)  // Acción de seguir/desseguir
                .addAction(R.drawable.baseline_remove_red_eye_24, "Ver Usuario", pendingIntentViewUser)  // Acción de ver el usuario
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)  // La notificación se eliminará cuando el usuario la toque
                .build();

        // Mostrar la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, notification);
    }
}