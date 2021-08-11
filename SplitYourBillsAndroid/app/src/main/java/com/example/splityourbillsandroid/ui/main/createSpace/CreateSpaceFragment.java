package com.example.splityourbillsandroid.ui.main.createSpace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.body.SpaceBody;
import com.example.splityourbillsandroid.data.models.spaces.response.AddNewSpaceResponse;
import com.example.splityourbillsandroid.ui.auth.AuthActivity;
import com.example.splityourbillsandroid.ui.main.AddPeopleForSpace.AddPeopleFragment;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.example.splityourbillsandroid.utils.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CreateSpaceFragment extends Fragment {

    TextInputEditText spaceNameET,spaceDescriptionET;
    MaterialButton createSpaceBtn;
    LinearLayout linearLayout;
    ProgressBar progressBar;

    @Inject
    MainViewModel viewModel;

    @Inject
    AddPeopleFragment addPeopleFragment;

    long spaceId = 0;

    private static final String TAG = "CreateSpaceFragment";

    @Inject
    public CreateSpaceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_space, container, false);

        AndroidSupportInjection.inject(this);
        initializeViews(view);

        createSpaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                createANewSpace();
            }
        });


        subsribeForSpaceDetails();



        return view;
    }


    private void subsribeForSpaceDetails(){
        viewModel.getSpaceDetails().observe(this, new Observer<AddNewSpaceResponse>() {
            @Override
            public void onChanged(AddNewSpaceResponse addNewSpaceResponse) {
                spaceId =  addNewSpaceResponse.getSpaceId();
                Log.d(TAG, "onChanged: " + addNewSpaceResponse.toString());
                Log.d(TAG, "onChanged: spaceid " + spaceId);
            }
        });
    }

    private void subscribeObserver() {
        viewModel.createStatusSpace().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                int x = integer;
                if (x == 201) {
                      showToast("Space Created");
                    Log.d(TAG, "onChanged: spaceid " + spaceId);

                    //   progressBar.setVisibility(View.GONE)
                   // initializeFragments(addPeopleFragment);
                        initializeFragments(addPeopleFragment);
                } else if (x == 401){
                    showToast("Not Authenticated");
                    Intent intent = new Intent(getActivity(), AuthActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                else if (x == 500)
                    showToast("Somewhere,Somehow Something went wrong");
                // progressBar.setVisibility(View.GONE);
            }
        });
    }


    private void createANewSpace() {
        String spaceName = spaceNameET.getText().toString();
        String spaceDescription = spaceDescriptionET.getText().toString();

        if (spaceName.isEmpty()) {
            progressBar.setVisibility(View.INVISIBLE);
            showToast("Space Name can not be empty");
            return;
        }
        if (spaceDescription.isEmpty()) {
            progressBar.setVisibility(View.INVISIBLE);
            showToast("Space Description can not be empty");
            return;
        }
        SpaceBody spaceBody = new SpaceBody(spaceName,spaceDescription);

        viewModel.createANewSpace(spaceBody);
        subscribeObserver();

    }

    private void initializeViews(View view) {
        spaceNameET = view.findViewById(R.id.et_name);
        spaceDescriptionET = view.findViewById(R.id.et_description);
        createSpaceBtn = view.findViewById(R.id.btn_create_space);
        linearLayout = view.findViewById(R.id.parent_layout);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    private void showToast(String msg) {
        Snackbar snackbar = Snackbar.make(linearLayout, msg, Snackbar.LENGTH_INDEFINITE).
                setDuration(2000);
        snackbar.show();
    }

    private void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();



        Bundle bundle = new Bundle();
        bundle.putString(Constants.SPACE_ID, String.valueOf(spaceId));

        frag.setArguments(bundle);


        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //   transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout_main, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }


}