package com.example.mubeen.law;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class LIST_VIEW_ACTIVITY extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__view__activity);

        RecyclerView rv = findViewById(R.id.recycler_list);
        ADOPTER_VIEW_COLLECTOR adopter_view_collector = new ADOPTER_VIEW_COLLECTOR();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LIST_VIEW_ACTIVITY.this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adopter_view_collector);
    }
}
