package com.ramasolutions.notificacionfirebase.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ramasolutions.notificacionfirebase.R;
import com.ramasolutions.notificacionfirebase.adapter.FavAdapter;
import com.ramasolutions.notificacionfirebase.db.Database;
import com.ramasolutions.notificacionfirebase.pojo.Mascota;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MisFavoritos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Database database;
    private List<Mascota> favItemList = new ArrayList<>();
    public FavAdapter favAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        database = new Database(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        loadData();

    }

    private void loadData() {
        if (favItemList != null) {
            favItemList.clear();
        }
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = database.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                String petName = cursor.getString(cursor.getColumnIndex(Database.PET_NAME));
                String id = cursor.getString(cursor.getColumnIndex(Database.KEY_ID));
                int petPic = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Database.PET_PIC)));
                Mascota favItem = new Mascota(petPic, petName, id);
                favItemList.add(favItem);
            }
        } finally {

            if (cursor != null && cursor.isClosed()) {
                cursor.close();
            db.close();
            }

            favAdapter = new FavAdapter(this, favItemList);

            recyclerView.setAdapter(favAdapter);
        }
    }
}