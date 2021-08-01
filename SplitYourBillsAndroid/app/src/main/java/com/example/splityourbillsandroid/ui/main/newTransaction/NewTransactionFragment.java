package com.example.splityourbillsandroid.ui.main.newTransaction;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.response.AddNewSpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceMembersResponse;
import com.example.splityourbillsandroid.ui.main.AddPeopleForSpace.Contacts;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.example.splityourbillsandroid.ui.main.transactionAmountManual.TransactionAmountFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class NewTransactionFragment extends Fragment {


    @Inject
    MainViewModel viewModel;

    @Inject
    TransactionAmountFragment transactionAmountFragment;

    TextView manualAmount;

    long spaceId = Long.valueOf(0);
    TextInputEditText etDescription,etAmount;

    List<SpaceMembersResponse> mList;
    private static final String TAG = "NewTransactionFragment";



    @Inject
    public NewTransactionFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_new_transaction, container, false);

        AndroidSupportInjection.inject(this);
        initializaViews(view);
        mList = new ArrayList<>();
        

        subscribeForSpaceId();
        subscribeObserverForSpaceMembers();



        manualAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etAmount.getText().toString();
                if (str=="" || str.isEmpty())
                    str="0";
                viewModel.setTransactionAmount(Long.valueOf(str));
                initializeFragments(transactionAmountFragment);

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mList = new ArrayList<>();
    }


    @Override
    public void onStart() {
        super.onStart();
        mList = new ArrayList<>();
    }

    private void initializaViews(View view) {
        etDescription = view.findViewById(R.id.et_description);
        etAmount = view.findViewById(R.id.et_amount);
        manualAmount = view.findViewById(R.id.manual_tv);
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    private void subscribeForSpaceId() {
        viewModel.getSpaceDetails().observe(this, new Observer<AddNewSpaceResponse>() {
            @Override
            public void onChanged(AddNewSpaceResponse addNewSpaceResponse) {
                spaceId = addNewSpaceResponse.getSpaceId();
                //TODO doing the network call here guyms
                viewModel.getSpaceMembersBySpaceId(spaceId);
            }
        });
    }

    private void subscribeObserverForSpaceMembers() {
        mList = new ArrayList<>();
        viewModel.getSpaceMembers().observe(this, new Observer<List<SpaceMembersResponse>>() {
            @Override
            public void onChanged(List<SpaceMembersResponse> spaceMembersResponses) {

                    mList = spaceMembersResponses;

                Log.d(TAG, "onChanged: size : " + mList.size());
            }
        });
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