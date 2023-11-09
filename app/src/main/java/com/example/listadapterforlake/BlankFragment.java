package com.example.listadapterforlake;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ArrayList<Lake> lakes = new ArrayList<>();
    Gson gs = new Gson();

    public MyViewModel myViewModel;
    public SharedPreferences settings;
    public BlankFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         return inflater.inflate(R.layout.fragment_blank, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //myViewModel = new ViewModelProvider(getActivity()).get(MyViewModel.class);
        Button crt = view.findViewById(R.id.createBtn);
        ListView list = view.findViewById(R.id.listExamples);
        //Bundle arg;
        //arg = getArguments();
        SharedPreferences settings = getContext().getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
        String sharedData;
        sharedData = settings.getString("KEY_BD","");
        lakes = gs.fromJson(sharedData,new TypeToken<ArrayList<Lake>>(){}.getType());
        AdapterLake adapterLake = new AdapterLake(view.getContext(), R.layout.list_item,lakes);
        list.setAdapter(adapterLake);
        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(view.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Вы уверены?")
                        .setMessage("Вы хотите удалить эту записть?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Lake lake = lakes.get((int)id);
                                lakes.remove(lake);
                                adapterLake.notifyDataSetChanged();
                            }
                        }).setNegativeButton("Нет",null).show();
                return true;
            }
        };

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openDialog(view, i,adapterLake);
            }
        };
        crt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog(view,adapterLake);
            }
        });
        list.setOnItemClickListener(clickListener);
        list.setOnItemLongClickListener(listener);
    }
    public void openDialog (View view, long id,AdapterLake adapterLake)
    {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_edit);
        final EditText et = dialog.findViewById(R.id.et);
        final EditText et2 = dialog.findViewById(R.id.et2);
        final EditText et3 = dialog.findViewById(R.id.et3);
        final EditText et4 = dialog.findViewById(R.id.et4);
        Lake lake = lakes.get((int) id);
        et.setText(lake.getName());
        et2.setText(""+lake.getAge());
        et3.setText(""+lake.getLength());
        et4.setText(""+lake.getSquare());
        Button btnok = (Button) dialog.findViewById(R.id.btnok);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().equals("")) return;
                if (et2.getText().equals("")) return;
                if (et3.getText().equals("")) return;
                if (et4.getText().equals("")) return;
                lake.setName(et.getText().toString());
                lake.setAge(Integer.parseInt(et2.getText().toString()));
                lake.setLength(Integer.parseInt(et3.getText().toString()));
                lake.setSquare(Integer.parseInt(et4.getText().toString()));
                lakes.set((int)id,lake);
                adapterLake.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        Button btncn = (Button) dialog.findViewById(R.id.btncn);
        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void createDialog(View v,AdapterLake adapterLake)
    {
        Lake lake = new Lake();
        final Dialog dialog = new Dialog(v.getContext());

        dialog.setContentView(R.layout.dialog_edit);
        final EditText et = dialog.findViewById(R.id.et);
        final EditText et2 = dialog.findViewById(R.id.et2);
        final EditText et3 = dialog.findViewById(R.id.et3);
        final EditText et4 = dialog.findViewById(R.id.et4);
        Button btnok = (Button) dialog.findViewById(R.id.btnok);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et.getText().equals("")) return;
                if (et2.getText().equals("")) return;
                if (et3.getText().equals("")) return;
                if (et4.getText().equals("")) return;
                lake.setName(et.getText().toString());
                lake.setAge(Integer.parseInt(et2.getText().toString()));
                lake.setLength(Integer.parseInt(et3.getText().toString()));
                lake.setSquare(Integer.parseInt(et4.getText().toString()));
                lakes.add(lake);
                adapterLake.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        Button btncn = (Button) dialog.findViewById(R.id.btncn);
        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}