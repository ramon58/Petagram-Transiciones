package com.ramasolutions.notificacionfirebase.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ramasolutions.notificacionfirebase.MainActivity;
import com.ramasolutions.notificacionfirebase.restAPI.EndpointsAPI;
import com.ramasolutions.notificacionfirebase.restAPI.adapter.RestApiAdapterFir;
import com.ramasolutions.notificacionfirebase.fragments.IProfileFragmentView;
import com.ramasolutions.notificacionfirebase.model.BioResponse;
import com.ramasolutions.notificacionfirebase.model.PetResponse;
import com.ramasolutions.notificacionfirebase.pojo.BioItem;
import com.ramasolutions.notificacionfirebase.pojo.ProfileItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentPresenter implements IProfileFragmentPresenter {

    private IProfileFragmentView iProfileFragmentView;
    private Context context;
    private ArrayList<ProfileItem> profileItems;
    private BioItem bioItem;

    public ProfileFragmentPresenter(IProfileFragmentView iProfileFragmentView, Context context) {
        this.iProfileFragmentView = iProfileFragmentView;
        this.context = context;
        obtenerMediosRecientes();
        obtenerInformacionUsuario();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapterFir restApiAdapter = new RestApiAdapterFir();
        Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializeMediaRecent();
        EndpointsAPI endpointsAPI = restApiAdapter.stablishConnectionRestAPInstagram(gsonMediaRecent);
        Call<PetResponse> petResponseCall = endpointsAPI.getRecentMedia();
        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                PetResponse petResponse = response.body();
                profileItems = petResponse.getProfileItems();
                mostrarContactosRecyclerView();

                MainActivity.profileItems = profileItems;
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión con el servidor", Toast.LENGTH_LONG).show();
                Log.e("Connection failed", t.toString());
            }
        });
    }

    @Override
    public void obtenerInformacionUsuario() {
        RestApiAdapterFir restApiAdapter = new RestApiAdapterFir();
        Gson gsonBioInfo = restApiAdapter.builGsonDeserializerBioInfo();
        EndpointsAPI endpointsAPI = restApiAdapter.stablishConnectionRestAPInstagram(gsonBioInfo);
        Call<BioResponse> bioResponseCall = endpointsAPI.getBioInfo();
        bioResponseCall.enqueue(new Callback<BioResponse>() {
            @Override
            public void onResponse(Call<BioResponse> call, Response<BioResponse> response) {
                BioResponse bioResponse = response.body();
                bioItem = bioResponse.getBioItem();
                iProfileFragmentView.showProfile(bioItem);
            }

            @Override
            public void onFailure(Call<BioResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión con el servidor", Toast.LENGTH_LONG).show();
                Log.e("Connection failed", t.toString());
            }
        });
    }

    @Override
    public void mostrarContactosRecyclerView() {
        iProfileFragmentView.initializeAdapter(iProfileFragmentView.createAdapter(profileItems));
        iProfileFragmentView.generateGridLayout();
    }
}
