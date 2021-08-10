package com.example.splityourbillsandroid.ui.main.transactionDetails;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.transactions.TransactionBody;
import com.example.splityourbillsandroid.data.models.transactions.TransactionsResponse;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.example.splityourbillsandroid.utils.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class TransactionDetailsFragment extends Fragment {

    @Inject
    MainViewModel viewModel;

    TextInputEditText amount,description;

    MaterialButton updateTXN;
    String txnId = "";
    Long id = Long.valueOf(0);
    LinearLayout linearLayout;

    @Inject
    public TransactionDetailsFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_transaction_details, container, false);

        AndroidSupportInjection.inject(this);

        initializeViews(view);

         txnId = getArguments().getString(Constants.TXN_ID);

         id = Long.valueOf(txnId);

        viewModel.getTXNDetails(id);
        subsribeObserverForGETTXN();
        subsriveForUpdateTXN();

        updateTXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTXNForId();
            }
        });

        return view;
    }

    private void subsriveForUpdateTXN() {
        viewModel.getTXNUpdateResponseStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer==200)
                 showToast("Updated suceessfully");
            }
        });
    }

    private void updateTXNForId() {
        String strAmount = amount.getText().toString();
        String strDescription = description.getText().toString();

        TransactionBody body = new TransactionBody(Long.valueOf(strAmount),strDescription);
        viewModel.updateTXNDetailsByID(id,body);

    }

    private void initializeViews(View view) {

        description = view.findViewById(R.id.et_description);
        amount = view.findViewById(R.id.et_amount);
        updateTXN = view.findViewById(R.id.btn_update_txn);
        linearLayout = view.findViewById(R.id.linear_layout);
    }

    private void subsribeObserverForGETTXN() {
        viewModel.getTransactionDetailsByID().observe(this, new Observer<TransactionsResponse>() {
            @Override
            public void onChanged(TransactionsResponse transactionsResponse) {
                //set text and edit texts
                String strDescription = transactionsResponse.getDescription();
                Long amountSTR = transactionsResponse.getAmount();
                description.setText(strDescription);
                amount.setText(String.valueOf(amountSTR));
            }
        });
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
}