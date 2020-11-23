package com.ramasolutions.notificacionfirebase.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ramasolutions.notificacionfirebase.R;
import com.ramasolutions.notificacionfirebase.adapter.PetAdapter;
import com.ramasolutions.notificacionfirebase.pojo.PetItem;

import java.util.ArrayList;

public class FragmentHome extends Fragment {

    private ArrayList<PetItem> petItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PetAdapter(petItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        petItems.add(new PetItem(R.drawable.perro_uno, "Nube", "0", "0"));
        petItems.add(new PetItem(R.drawable.perro_dos, "Fido", "1", "0"));
        petItems.add(new PetItem(R.drawable.perro_tres, "Patan", "2", "0"));
        petItems.add(new PetItem(R.drawable.perro_cuatro, "Leo", "3", "0"));
        petItems.add(new PetItem(R.drawable.perro_cinco, "Tom", "4", "0"));
        petItems.add(new PetItem(R.drawable.perro_seis, "Linda", "5", "0"));
        petItems.add(new PetItem(R.drawable.perro_siete, "Super", "6", "0"));
        petItems.add(new PetItem(R.drawable.perro_ocho, "Pulgas", "7", "0"));
        petItems.add(new PetItem(R.drawable.perro_nueve, "Estrella", "8", "0"));

        return view;
    }
}
