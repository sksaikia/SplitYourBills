package com.example.splityourbillsandroid.ui.auth.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.authentication.RegisterBody;
import com.example.splityourbillsandroid.ui.auth.AuthViewModel;
import com.example.splityourbillsandroid.ui.main.MainActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class SignupFragment extends Fragment {


    TextView loginTV;
    TextInputEditText emailTV,phoneTV,passwordTV,nameTV;
    MaterialButton registerBtn;
    LinearLayout parentLayout;
    ProgressBar progressBar;



    @Inject
    AuthViewModel authViewModel;

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
        initializeViews(view);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        subscribeObserver();





        return view;
    }

    //Have to handle the scenario, when the user is invited
    private void registerUser() {

        String email = emailTV.getText().toString();
        String phone = phoneTV.getText().toString();
        String password = passwordTV.getText().toString();
        String name = nameTV.getText().toString();

        RegisterBody registerBody = new RegisterBody(name,phone,email,password);
        authViewModel.registerNewUser(registerBody);


    }


    private void subscribeObserver() {
        authViewModel.getStatusRegister().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                int x = integer;
                if (x == 201) {
                    showToast("Successfully Registered");
                    progressBar.setVisibility(View.GONE);
                    goToNextActivity();
                } else if (x == 409)
                    showToast("Email or Phone Already exist");
                else if (x == 500)
                   showToast("Somewhere,Somehow Something went show");
                progressBar.setVisibility(View.GONE);

            }
        });
    }


    public void initializeViews(View view){
        emailTV = view.findViewById(R.id.et_email);
        phoneTV = view.findViewById(R.id.et_phone);
        passwordTV = view.findViewById(R.id.et_password);
        nameTV = view.findViewById(R.id.et_name);
        registerBtn = view.findViewById(R.id.btn_register);
        progressBar = view.findViewById(R.id.progress_bar);
        parentLayout = view.findViewById(R.id.parent_layout);

        loginTV = view.findViewById(R.id.tv_login);
        String sourceString = "Already have an account? " + "<b>" +"<font color=#FFFFFF>"+ "Log in" + "</font>" + "</b>";
        loginTV.setText(Html.fromHtml(sourceString));
    }

    public void showToast(String msg) {
        Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT).show();
    }





    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    private void goToNextActivity() {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);


    }


}