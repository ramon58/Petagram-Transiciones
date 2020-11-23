package com.ramasolutions.notificacionfirebase.fragments;

import com.ramasolutions.notificacionfirebase.adapter.ProfileAdapter;
import com.ramasolutions.notificacionfirebase.pojo.BioItem;
import com.ramasolutions.notificacionfirebase.pojo.ProfileItem;

import java.util.ArrayList;

public interface IProfileFragmentView {

    public void generateGridLayout();
    public ProfileAdapter createAdapter(ArrayList<ProfileItem> profileItems);
    public void initializeAdapter(ProfileAdapter profileAdapter);
    public void showProfile(BioItem bioItem);
}
