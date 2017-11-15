package com.example.mercer.bluecareer.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mercer.bluecareer.R;

/**
 * author: Husen
 * date: 2017.11.15
 * description：
 */

public class MyOccuFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.second_fragment_my_occupation, container, false);

        return view;
    }
}
