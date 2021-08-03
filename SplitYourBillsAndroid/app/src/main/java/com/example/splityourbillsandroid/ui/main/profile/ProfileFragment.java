package com.example.splityourbillsandroid.ui.main.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.authentication.response.ProfileResponse;
import com.example.splityourbillsandroid.ui.auth.AuthActivity;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class ProfileFragment extends Fragment {


    @Inject
    MainViewModel viewModel;

    MaterialButton logoutBTN;
    TextView name,phone;

    private static final String TAG = "ProfileFragment";

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

        initializeViews(view);


        viewModel.getProfileDetails();
        subscribeForProfileResponse();

        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.logout();
                Intent intent = new Intent(getActivity(), AuthActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
   }

    private void initializeViews(View view) {
        name    = view.findViewById(R.id.name);
        phone = view.findViewById(R.id.phone);
        logoutBTN = view.findViewById(R.id.btn_log_out);

    }

    private void subscribeForProfileResponse() {
        viewModel.getProfileResponse().observe(this, new Observer<ProfileResponse>() {
            @Override
            public void onChanged(ProfileResponse profileResponse) {
                name.setText(profileResponse.getName());
                phone.setText(profileResponse.getPhoneNo());
            }
        });
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

}