package com.example.listadapterforlake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ListView listlakes;
    ArrayList<Lake> lakes = new ArrayList<Lake>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init();
        setContentView(R.layout.activity_main);
        Bundle arg = new Bundle();
        arg.putSerializable("list",lakes);
        BlankFragment fragment = new BlankFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(arg);
        fragmentTransaction.add(R.id.main_fragment,fragment,"blank_fragment");
        fragmentTransaction.addToBackStack("blank_fragment_transuctiom");
        fragmentTransaction.commit();

        /*listlakes = findViewById(R.id.listlakes);
        AdapterLake adapterLake = new AdapterLake(this,R.layout.list_item,lakes);
        listlakes.setAdapter(adapterLake);
        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Lake lake = lakes.get((int)id);
                lakes.remove(lake);
                adapterLake.notifyDataSetChanged();
                return false;
            }
        };
        listlakes.setOnItemLongClickListener(listener);*/
    }
    public void Init()
    {

        lakes.add(new Lake("Енисей",12,200,22));
        lakes.add(new Lake("Мисисипи",12,2030,2332));
        lakes.add(new Lake("Волга",12,200,22123));
        lakes.add(new Lake("Обь",8981,20330,2132));

    }
}