package com.ramasolutions.notificacionfirebase.restAPIFireBase.model;

import com.ramasolutions.notificacionfirebase.pojo.Mascota;

import java.util.ArrayList;

public class MascotaMediaRecentResponse {
    ArrayList<Mascota> mascotas;
    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }
    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

}

