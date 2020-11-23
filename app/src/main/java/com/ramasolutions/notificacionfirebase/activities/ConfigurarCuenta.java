package com.ramasolutions.notificacionfirebase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.ramasolutions.notificacionfirebase.MainActivity;
import com.ramasolutions.notificacionfirebase.R;
import com.ramasolutions.notificacionfirebase.restAPIFireBase.ConstantesRestAPI;

import java.util.Objects;

public class ConfigurarCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button btnGuardarCuenta = (Button) findViewById(R.id.btnGuardarCuenta);
        btnGuardarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText txtNombreUsuario = (TextInputEditText) findViewById(R.id.txtNombreUsuario);
                String txtUsuario = txtNombreUsuario.getText().toString();
                String id_user;
                boolean flag = false;
                switch (txtUsuario.trim()) {
                    case "ramon583":
                        id_user = "123456789";
                        flag = true;
                        break;
                    default:
                        id_user = "";
                        flag = false;
                        break;
                }

                if (flag) {
                    Toast.makeText(ConfigurarCuenta.this, "Usuario " + txtUsuario + " configurado.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ConfigurarCuenta.this, "Usuario no valido... ", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(ConfigurarCuenta.this, MainActivity.class);
                ConstantesRestAPI.ID_USER = id_user;
                startActivity(intent);
                finish();
            }
        });
    }
}

