package com.ramasolutions.notificacionfirebase.restAPIFireBase;


import com.ramasolutions.notificacionfirebase.restAPIFireBase.model.MascotaMediaRecentResponse;
import com.ramasolutions.notificacionfirebase.restAPIFireBase.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Endpoints {

    //Busqueda de usuarios por username
    @GET(ConstantesRestAPI.USERS + "{user_id}" + ConstantesRestAPI.MEDIA_SEARCH + "?" +
            ConstantesRestAPI.KEY_ACCESS_TOKEN + ConstantesRestAPI.ACCESS_TOKEN)
    //@GET("users/search?q=user_id&access_token=access_token")
    Call<MascotaMediaRecentResponse> getUsersSearch(@Path("user_id") String user_id);

    @FormUrlEncoded
    @POST(ConstantesRestAPI.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String id_dispositivo,
                                           @Field("id_usuario_instagram") String id_usuario_instagram);
}
