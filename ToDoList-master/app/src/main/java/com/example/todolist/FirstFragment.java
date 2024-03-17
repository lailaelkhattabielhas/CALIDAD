package com.example.todolist;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    String textousu, fechausu, fechausu2;
    private int day;
    private int month;
    private int year;
    private String color;
    private Spinner spinner;
    private ArrayList<String> colors;


    public FirstFragment(HomeScreen homeScreen) {
        // Required empty public constructor
        this.homeScreen = homeScreen;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2, HomeScreen homeScreen) {
        FirstFragment fragment = new FirstFragment(homeScreen);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        EditText texto = view.findViewById(R.id.textousuario);
        DatePicker fecha= view.findViewById(R.id.fechausuario);
        Button btn = view.findViewById(R.id.aceptar);
        Button btn1 = view.findViewById(R.id.cancelar);
        spinner = view.findViewById(R.id.spinner);

        loadData(); //Carga la lista de tareas

        colors = new ArrayList<>();
        colors.add("Blanco");
        colors.add("Rosa");
        colors.add("Azul");
        colors.add("Verde");
        colors.add("Amarillo");
        colors.add("Rojo");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,colors);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                color = colors.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(getContext(), SecondFragment.class);
                textousu = texto.getText().toString();
                day = fecha.getDayOfMonth();
                month = fecha.getMonth(); // Los meses comienzan en 0
                year = fecha.getYear();

                // Formatea la fecha seleccionada
                fechausu = String.format("%02d/%02d/%04d", day, month+1, year);
                fechausu2 = String.format("%04d/%02d/%02d", year, month + 1, day);







                addTask();
                saveData();
                Toast.makeText(getContext(), "Seleccionaste: " + color, Toast.LENGTH_SHORT).show();


                //Volver al 2ndo Fragmento
                homeScreen.loadFragment(homeScreen.secondFragment);
                homeScreen.getNavigation().getMenu().getItem(1).setChecked(true);
                //para probar que las tareas se guardan se muestra por consola
                //Log.d(TAG, "Las tareas guardadas son: " + listManager.getTasklist().get(0).getText() + listManager.getTasklist().get(0).getDate() + listManager.getTasklist().get(1).getText() + listManager.getTasklist().get(1).getDate() + listManager.getTasklist().get(2).getText() + listManager.getTasklist().get(2).getDate());

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Volver al 2ndo Fragmento
                homeScreen.loadFragment(homeScreen.secondFragment);
                homeScreen.getNavigation().getMenu().getItem(1).setChecked(true);


                //Mostrar mensaje "Cancelado"
                int duration = Toast.LENGTH_SHORT;
                CharSequence text = "Se ha cancelado la operación";
                Toast toast = Toast.makeText(getContext(), text, duration);
                toast.show();
            }
        });

        return view;
    }

    private void addTask(){




        //Crear nueva tarea
        task tareaNueva = new task(textousu, fechausu,year,month,day,fechausu2,color);

        listManager.addTask(tareaNueva);



        //Mostrar  mensaje tarea guardada
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "Tarea guardada";
        Toast toast = Toast.makeText(this.getActivity(), text, duration);
        toast.show();

    }
}