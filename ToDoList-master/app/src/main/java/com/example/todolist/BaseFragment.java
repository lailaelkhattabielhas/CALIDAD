package com.example.todolist;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class BaseFragment extends Fragment {
    protected static final String ARG_PARAM1 = "param1";
    protected static final String ARG_PARAM2 = "param2";

    protected String mParam1;
    protected String mParam2;

    protected HomeScreen homeScreen;
    protected ListManager listManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    protected void loadData() {
        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tasklist", null);
        Type type = new TypeToken<ListManager>() {}.getType();
        listManager = gson.fromJson(json, type);

        if (listManager == null){ //Si la lista está vacía

            //Crear lista y guardar lista de tareas en listManager
            listManager = new ListManager();


            //Mostrar mensaje "se crea"
            int duration = Toast.LENGTH_SHORT;
            CharSequence text = "Se crea lista vacia";
            Toast toast = Toast.makeText(this.getActivity(), text, duration);
            toast.show();

        }
    }

    protected void saveData() {
        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listManager);
        editor.putString("tasklist", json);
        editor.apply();
    }
}
