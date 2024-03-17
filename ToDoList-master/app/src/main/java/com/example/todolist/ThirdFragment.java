package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends BaseFragment {

    public ThirdFragment(HomeScreen homeScreen) {
        // Required empty public constructor
        this.homeScreen = homeScreen;
    }

    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2, HomeScreen homeScreen) {
        ThirdFragment fragment = new ThirdFragment(homeScreen);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        ListView listView = view.findViewById(R.id.listView);
        loadData();

        ListAdapter adapter = new ListAdapter(getActivity(),listManager.getTasklist());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                task t = listManager.getTasklist().get(position);
                Intent intent = new Intent(getContext(), EditTask.class);
                intent.putExtra("task", t);
                intent.putExtra("list", listManager);
                intent.putExtra("index", position);

                startActivity(intent);

                getActivity().finish();

            }
        });

        return view;
    }


}