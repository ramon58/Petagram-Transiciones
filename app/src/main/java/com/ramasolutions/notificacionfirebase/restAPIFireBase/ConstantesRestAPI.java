package com.ramasolutions.notificacionfirebase.restAPIFireBase;

public final class ConstantesRestAPI {

    public static final String VERSION = "/v8.0/";
    public static final String ROOT_URL = "https://graph.facebook.com" + VERSION;
    public static final String ACCESS_TOKEN = "EAAEZBGzKI9bABAHT9z1ZBJsN4ZA3VU3YHUS6R0DiK0ZCIa0GL670ousB1clErePIug9aSvtPA07XVAYre5qhnSMbPZBiTEznK1DvgLbtvxZCNjaN1Q1U2yshlWHo5r6pxuUi1ZCYZCa9VfIrjSX2axu9K1hhIZCIjgqsVwX0WPbzbeAZDZD";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";
    public static final String USER_ID = "17841401163453985";
    public static final String USERS = "users/";
    public static final String MEDIA_SEARCH = "/media/recent/";
    public static final String KEY_USER_MEDIA = "/media?fields=id,caption,media_type,media_url,owner,username,like_count";
    public static final String URL_USER_MEDIA = USER_ID + KEY_USER_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_BIO_URL = "?fields=biography,followers_count,follows_count,name,media_count,username,profile_picture_url";
    public static final String URL_USER_BIO = USER_ID + KEY_BIO_URL+ KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String ROOT_URL_F = "https://serene-peak-24462.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario/";
    public static String ID_USER = "mercecolloto";

}
