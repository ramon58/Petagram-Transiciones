package com.ramasolutions.notificacionfirebase.restAPI;

import com.ramasolutions.notificacionfirebase.model.BioResponse;
import com.ramasolutions.notificacionfirebase.model.PetResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointsAPI {

    @GET(ConstantsRestApi.URL_USER_MEDIA) Call<PetResponse> getRecentMedia();

    @GET(ConstantsRestApi.URL_USER_BIO) Call<BioResponse> getBioInfo();
}
