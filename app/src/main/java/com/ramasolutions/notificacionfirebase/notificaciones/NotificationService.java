package com.ramasolutions.notificacionfirebase.notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ramasolutions.notificacionfirebase.MainActivity;
import com.ramasolutions.notificacionfirebase.R;

import java.util.Map;


public class NotificationService extends FirebaseMessagingService {

    public static final String TAG ="Notificacion Firebase";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Map datos = remoteMessage.getData();
        String nomUsuario=datos.get("nomUsu").toString();
        String usuarioID=datos.get("idUsu").toString();
        String urlUsuario=datos.get("urlUsu").toString();

        Log.e(TAG, "From:  " + remoteMessage.getFrom());
        //Log.e(TAG, "To: " + remoteMessage.getTo());
        Log.e(TAG, "Title: " + remoteMessage.getNotification().getTitle());
        Log.e(TAG, "Msg:   " + remoteMessage.getNotification().getBody());

        Log.e(TAG, "nomUsu:  " + nomUsuario);
        Log.e(TAG, "idUsu:  " + usuarioID);
        Log.e(TAG, "urlUsu:  " + urlUsuario);

        int notificacionID=0;

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("usuario", nomUsuario);
        i.putExtra("usuarioID", usuarioID);
        i.putExtra("usuarioURL", urlUsuario);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notificacion)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                ;
//        notificacion.setExtras(parametros);

        NotificationManager notificacionManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificacionManager.notify(notificacionID, notificacion.build());

    }
}
