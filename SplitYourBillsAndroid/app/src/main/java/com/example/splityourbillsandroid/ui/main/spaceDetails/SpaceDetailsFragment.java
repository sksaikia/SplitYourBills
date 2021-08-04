package com.example.splityourbillsandroid.ui.main.spaceDetails;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.transactions.TransactionsResponse;
import com.example.splityourbillsandroid.ui.main.AddPeopleForSpace.AddPeopleFragment;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.example.splityourbillsandroid.ui.main.SpaceMembers.SpaceMembersFragment;
import com.example.splityourbillsandroid.ui.main.newTransaction.NewTransactionFragment;
import com.example.splityourbillsandroid.ui.main.viewDetailsTXN.ViewDetailsTransactionFragment;
import com.example.splityourbillsandroid.utils.Constants;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class SpaceDetailsFragment extends Fragment {

    private static final String TAG = "SpaceDetailsFragment";

    @Inject
    MainViewModel viewModel;

    @Inject
    TransactionsAdapter adapter;

    @Inject
    AddPeopleFragment addPeopleFragment;

    @Inject
    SpaceMembersFragment spaceMembersFragment;

    @Inject
    NewTransactionFragment newTransactionFragment;

    @Inject
    ViewDetailsTransactionFragment viewDetailsTransactionFragment;

    MaterialButton addNewPeopleBTN,viewMembersBTN,newTransactionBTN,viewDetailsBTN;


    List<TransactionsResponse> mList;

    RecyclerView recyclerView;
    LinearLayout linearLayout;
    String spaceId = "";



    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Log.d(TAG, "onClick: POsition ::: " + position);

        }
    };
    private void initializeFragments(Fragment frag) {


        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //   transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout_main, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }

    private void initializeFragments(Fragment frag,String id) {


        Bundle bundle = new Bundle();
        bundle.putString(Constants.SPACE_ID, id);

        frag.setArguments(bundle);

        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //   transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout_main, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }

    @Inject
    public SpaceDetailsFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_space_details, container, false);

        AndroidSupportInjection.inject(this);
        initializeView(view);

        spaceId = getArguments().getString(Constants.SPACE_ID);
        Log.d(TAG, "onCreateView: Category Id::: " + spaceId);
        Long spaceIdLong = Long.valueOf(spaceId);


        mList = new ArrayList<>();

        viewModel.getTransactionBySpaceId(spaceIdLong);

        setUpRecyclerView(recyclerView, adapter);

        //
        subscribeForTransactionStatus();
        subscribeForTransactionData();

        addNewPeopleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFragments(addPeopleFragment);
            }
        });
        viewMembersBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFragments(spaceMembersFragment);
            }
        });
        newTransactionBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFragments(newTransactionFragment);
            }
        });
        viewDetailsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFragments(viewDetailsTransactionFragment,spaceId);
            }
        });



        return view ;

    }

    private void initializeView(View view){
        recyclerView = view.findViewById(R.id.recycler_view);
        linearLayout = view.findViewById(R.id.linear_layout);
        addNewPeopleBTN = view.findViewById(R.id.btn_add_members);
        viewMembersBTN = view.findViewById(R.id.btn_view_members);
        newTransactionBTN = view.findViewById(R.id.btn_add_transaction);
        viewDetailsBTN = view.findViewById(R.id.btn_view_details);

    }

    private void subscribeForTransactionStatus() {
        viewModel.getTransactionResponseStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;
                if (x == 200) {
                    //  showToast("Data Retrieved");
                    //   progressBar.setVisibility(View.GONE);
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

    private void subscribeForTransactionData() {
        viewModel.getTransactionResponse().observe(this, new Observer<List<TransactionsResponse>>() {
            @Override
            public void onChanged(List<TransactionsResponse> transactionsResponses) {
                mList = transactionsResponses;
                Log.d(TAG, "onChanged: " + mList.size());
                adapter.updateListData(mList);

            }
        });
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }


    private void setUpRecyclerView(RecyclerView recyclerView, TransactionsAdapter adapter) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);


    }

    private void showToast(String msg) {
        Snackbar snackbar = Snackbar.make(linearLayout, msg, Snackbar.LENGTH_INDEFINITE).
                setDuration(2000);
        snackbar.show();
    }



}