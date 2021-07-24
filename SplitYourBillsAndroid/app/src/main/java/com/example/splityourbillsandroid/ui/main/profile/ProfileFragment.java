package com.example.splityourbillsandroid.ui.main.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splityourbillsandroid.R;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class ProfileFragment extends Fragment {

   @Inject
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        AndroidSupportInjection.inject(this);

        return view;
   }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

}