package com.ramasolutions.notificacionfirebase.restAPI.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ramasolutions.notificacionfirebase.restAPI.ConstantsRestApi;
import com.ramasolutions.notificacionfirebase.restAPI.EndpointsAPI;
import com.ramasolutions.notificacionfirebase.restAPI.deserializador.BioDeserializer;
import com.ramasolutions.notificacionfirebase.restAPI.deserializador.PetDeserializador;
import com.ramasolutions.notificacionfirebase.model.BioResponse;
import com.ramasolutions.notificacionfirebase.model.PetResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapterFir {

    public EndpointsAPI stablishConnectionRestAPInstagram(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsAPI.class);
    }

    public Gson buildGsonDeserializeMediaRecent() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetResponse.class, new PetDeserializador());
        return gsonBuilder.create();
    }

    public Gson builGsonDeserializerBioInfo() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BioResponse.class, new BioDeserializer());
        return gsonBuilder.create();
    }
}
