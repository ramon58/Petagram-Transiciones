package com.ramasolutions.notificacionfirebase.notificaciones;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class NotificacionIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FB_TOKEN";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token){
        Toast.makeText(NotificacionIDTokenService.this, token, Toast.LENGTH_SHORT).show();
        Log.d(TAG,token);
    }
}
