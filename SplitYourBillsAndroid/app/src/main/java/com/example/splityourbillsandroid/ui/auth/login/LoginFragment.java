package com.example.splityourbillsandroid.ui.auth.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.authentication.body.LoginBody;
import com.example.splityourbillsandroid.ui.auth.AuthViewModel;
import com.example.splityourbillsandroid.ui.auth.register.SignupFragment;
import com.example.splityourbillsandroid.ui.main.MainActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class LoginFragment extends Fragment {

    TextView registerTV,forgotPasswordTV;

    TextInputEditText emailET,passwordEt;
    MaterialButton loginBt;
    ProgressBar progressBar;
    LinearLayout parentLayout;


    @Inject
    AuthViewModel authViewModel;

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
        initializeViews(view);
        subscribeObservers();


        String sourceString = "Don't have an account? " + "<b>" +"<font color=#FFFFFF>"+ "Join Now" + "</font>" + "</b>";
        registerTV.setText(Html.fromHtml(sourceString));

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFragments(signupFragment);
            }
        });


        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });




        return view;
    }

    private void subscribeObservers() {
        authViewModel.getStatusLogin().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;
                if (x == 200) {
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
    private void goToNextActivity() {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);


    }



    private void loginUser() {
        String email = emailET.getText().toString();
        String password = passwordEt.getText().toString();

        LoginBody loginBody = new LoginBody(email,password);
        authViewModel.login(loginBody);


    }
    public void showToast(String msg) {
        Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT).show();
    }



    private void initializeViews(View view) {

        forgotPasswordTV = view.findViewById(R.id.tv_forgot_password);
        registerTV = view.findViewById(R.id.tv_register);
        emailET = view.findViewById(R.id.et_email);
        passwordEt = view.findViewById(R.id.et_password);
        loginBt = view.findViewById(R.id.btn_login);
        progressBar = view.findViewById(R.id.progress_bar);
        parentLayout = view.findViewById(R.id.parent_layout);

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