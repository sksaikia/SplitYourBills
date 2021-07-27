package com.example.splityourbillsandroid.ui.main.createSpace;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.SpaceBody;
import com.example.splityourbillsandroid.ui.main.AddPeopleForSpace.AddPeopleFragment;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CreateSpaceFragment extends Fragment {

    TextInputEditText spaceNameET,spaceDescriptionET;
    MaterialButton createSpaceBtn;
    LinearLayout linearLayout;

    @Inject
    MainViewModel viewModel;

    @Inject
    AddPeopleFragment addPeopleFragment;

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
                createANewSpace();
            }
        });

        subscribeObserver();


        return view;
    }

    private void subscribeObserver() {
        viewModel.createStatusSpace().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;
                if (x == 201) {
                    //  showToast("Data Retrieved");
                    //   progressBar.setVisibility(View.GONE)
                    initializeFragments(addPeopleFragment);
                } else if (x == 401){
                    showToast("Not Authenticated");
                    //TODO Redirect to the authentication page
                }
                else if (x == 500)
                    showToast("Somewhere,Somehow Something went show");
                // progressBar.setVisibility(View.GONE);
            }
        });
    }


    private void createANewSpace() {
        String spaceName = spaceNameET.getText().toString();
        String spaceDescription = spaceDescriptionET.getText().toString();

        SpaceBody spaceBody = new SpaceBody(spaceName,spaceDescription);

        viewModel.createANewSpace(spaceBody);

    }

    private void initializeViews(View view) {
        spaceNameET = view.findViewById(R.id.et_name);
        spaceDescriptionET = view.findViewById(R.id.et_description);
        createSpaceBtn = view.findViewById(R.id.btn_create_space);
        linearLayout = view.findViewById(R.id.parent_layout);
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
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //   transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout_main, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }


}