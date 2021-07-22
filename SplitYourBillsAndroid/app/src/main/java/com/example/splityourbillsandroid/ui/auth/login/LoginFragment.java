package com.example.splityourbillsandroid.ui.auth.login;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.ui.auth.register.SignupFragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class LoginFragment extends Fragment {

    TextView registerTV;


    @Inject
    SignupFragment signupFragment;

    @Inject
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        AndroidSupportInjection.inject(this);

        registerTV = view.findViewById(R.id.tv_register);
        String sourceString = "Don't have an account? " + "<b>" +"<font color=#FFFFFF>"+ "Join Now" + "</font>" + "</b>";
        registerTV.setText(Html.fromHtml(sourceString));

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFragments(signupFragment);
            }
        });


        return view;
    }
    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }


    private void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //   transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout_main, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }
}