package com.example.splityourbillsandroid.ui.auth.register;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.splityourbillsandroid.R;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class SignupFragment extends Fragment {


    TextView loginTV;

    @Inject
    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signup, container, false);

        AndroidSupportInjection.inject(this);


        loginTV = view.findViewById(R.id.tv_login);
        String sourceString = "Already have an account? " + "<b>" +"<font color=#FFFFFF>"+ "Log in" + "</font>" + "</b>";
        loginTV.setText(Html.fromHtml(sourceString));



        return view;
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

}