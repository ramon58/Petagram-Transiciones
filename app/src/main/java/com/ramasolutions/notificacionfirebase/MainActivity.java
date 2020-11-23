package com.ramasolutions.notificacionfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ramasolutions.notificacionfirebase.restAPIFireBase.ConstantesRestAPI;
import com.ramasolutions.notificacionfirebase.restAPIFireBase.Endpoints;
import com.ramasolutions.notificacionfirebase.restAPIFireBase.adapter.RestApiAdapterIns;
import com.ramasolutions.notificacionfirebase.restAPIFireBase.model.UsuarioResponse;
import com.ramasolutions.notificacionfirebase.activities.BiosActivity;
import com.ramasolutions.notificacionfirebase.activities.ConfigurarCuenta;
import com.ramasolutions.notificacionfirebase.activities.ContactoActivity;
import com.ramasolutions.notificacionfirebase.activities.MisFavoritos;
import com.ramasolutions.notificacionfirebase.main.SectionsPagerAdapter;
import com.ramasolutions.notificacionfirebase.pojo.ProfileItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static ArrayList<ProfileItem> profileItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "¡Tomar fotografía!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
    }

    private void enviarTokenRegistro(String token){
        Log.d("TOKEN", "Token a enviar: " + token);
        RestApiAdapterIns restApiAdapter = new RestApiAdapterIns();
        Endpoints endpoints = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarUsuario(token, ConstantesRestAPI.ID_USER);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("Id_dispositivo", usuarioResponse.getId_dispositivo());
                Log.d("Id_usuario_instagram", usuarioResponse.getId_usuario_instagram());
            }
            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_about:
                Intent goToBios = new Intent(MainActivity.this, BiosActivity.class);
                //para implementar Transiciones:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Slide slide = new Slide(Gravity.BOTTOM);
                    slide.setDuration(1200);
                    getWindow().setExitTransition(slide);
                    startActivity(goToBios,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                    getWindow().setReturnTransition(new Fade());
                }else {
                    startActivity(goToBios);
                }
                break;

            case R.id.action_contact:
                Intent goToContact = new Intent(MainActivity.this, ContactoActivity.class);
                //para implementar Transiciones:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Slide slide = new Slide(Gravity.BOTTOM);
                    slide.setDuration(1200);
                    getWindow().setExitTransition(slide);
                    startActivity(goToContact,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                    getWindow().setReturnTransition(new Fade());
                }else {
                    startActivity(goToContact);
                }
                break;

            case R.id.action_configurar:
                Intent goToConfigurar = new Intent(MainActivity.this, ConfigurarCuenta.class);
                startActivity(goToConfigurar);
                break;

            case R.id.action_favorites:
                Intent goToFavorites = new Intent(MainActivity.this, MisFavoritos.class);
                //para implementar Transiciones:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Slide slide = new Slide(Gravity.BOTTOM);
                    slide.setDuration(1200);
                    getWindow().setExitTransition(slide);
                    startActivity(goToFavorites,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
                    getWindow().setReturnTransition(new Fade());
                }else {
                    startActivity(goToFavorites);
                }
                break;

            case R.id.recibir_notificaciones:
                String token = FirebaseInstanceId.getInstance().getToken();
                enviarTokenRegistro(token);
                Log.d("MENU Token: ", token);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}