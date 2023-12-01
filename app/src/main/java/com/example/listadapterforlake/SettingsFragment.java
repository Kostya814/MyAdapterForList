package com.example.listadapterforlake;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM_TOPIC = "topic";
    private static final String PARAM_TIME_ACTIVE = "time";
    private static final String PAREM_LANG = "lang";
    private static final String PARAM_VALUE = "value";
    private static final String PARAM_HEADPHONE = "headphone";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CheckBox checkBox;
    EditText timeEt,langEt;

    SeekBar headphoneSb,valueSb;
    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoadData(view);
        Button btn = view.findViewById(R.id.saveBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData();

            }
        });

    }
    private void LoadData(View view)
    {
        checkBox = view.findViewById(R.id.topicCb);
        timeEt = view.findViewById(R.id.timeEt);
        langEt = view.findViewById(R.id.langSpin);
        valueSb = view.findViewById(R.id.valueSb);
        headphoneSb = view.findViewById(R.id.headphoneSb);
        SharedPreferences settings = getContext()
                .getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
        checkBox.setChecked(settings.getBoolean(ARG_PARAM_TOPIC,false));
        timeEt.setText(settings.getString(PARAM_TIME_ACTIVE,"0"));
        langEt.setText(settings.getString(PAREM_LANG,"ru"));
        headphoneSb.setProgress(settings.getInt(PARAM_HEADPHONE,0));
        valueSb.setProgress(settings.getInt(PARAM_VALUE,0));
    }
    private void SaveData()
    {
        SharedPreferences settings = getContext()
                .getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(ARG_PARAM_TOPIC,checkBox.isChecked());
        editor.putString(PARAM_TIME_ACTIVE,timeEt.getText().toString());
        editor.putString(PAREM_LANG,langEt.getText().toString());
        editor.putInt(PARAM_HEADPHONE,headphoneSb.getProgress());
        editor.putInt(PARAM_VALUE,valueSb.getProgress());
        editor.commit();
        editor.apply();

    }
}