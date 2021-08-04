package com.example.splityourbillsandroid.ui.main.viewDetailsTXN;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.utils.Constants;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class ViewDetailsTransactionFragment extends Fragment {

    private static final String TAG = "ViewDetailsTransactionF";

    String spaceId = "";


    @Inject
    public ViewDetailsTransactionFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_view_details_transaction, container, false);

        AndroidSupportInjection.inject(this);



        spaceId = getArguments().getString(Constants.SPACE_ID);
        Log.d(TAG, "onCreateView: Category Id::: " + spaceId);
        Long spaceIdLong = Long.valueOf(spaceId);



        return view;
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }
}