package com.example.listadapterforlake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class AdapterLake extends ArrayAdapter<Lake> {
    private Context context;
    private int resource;
    private List<Lake> lakes;
    private LayoutInflater inflater;
    public AdapterLake (Context context,int resource, List<Lake> lakes)
    {
        super(context,resource,lakes);
        this.context = context;
        this.resource = resource;
        this.lakes = lakes;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.resource, parent, false);


        TextView nameView = view.findViewById(R.id.name);
        TextView ageView = view.findViewById(R.id.age);;
        TextView squareView = view.findViewById(R.id.square);
        TextView lenghtView = view.findViewById(R.id.length);

        Lake lake = lakes.get(position);

        nameView.setText(lake.getName());
        ageView.setText(lake.getAge()+" лет");
        squareView.setText(lake.getSquare()+" кв.км");
        lenghtView.setText(lake.getLength()+ "км. км");


        return view;
    }


}
