package com.example.listadapterforlake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listlakes;
    ArrayList<Lake> lakes = new ArrayList<Lake>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        listlakes = findViewById(R.id.listlakes);
        AdapterLake adapterLake = new AdapterLake(this,R.layout.list_item,lakes);
        listlakes.setAdapter(adapterLake);
    }
    public void Init()
    {
        lakes.add(new Lake("Енисей",12,200,22));
        lakes.add(new Lake("Мисисипи",12,2030,2332));
        lakes.add(new Lake("Волга",12,200,22123));
        lakes.add(new Lake("Обь",8981,20330,2132));
    }
}