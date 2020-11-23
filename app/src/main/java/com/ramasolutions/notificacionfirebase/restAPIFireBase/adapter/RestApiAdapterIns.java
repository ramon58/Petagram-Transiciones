package com.ramasolutions.notificacionfirebase.restAPIFireBase.adapter;

import com.ramasolutions.notificacionfirebase.restAPIFireBase.ConstantesRestAPI;
import com.ramasolutions.notificacionfirebase.restAPIFireBase.Endpoints;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapterIns {

    public Endpoints establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL_F)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Endpoints.class);
    }
}

