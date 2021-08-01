package com.example.splityourbillsandroid.ui.main.transactionAmountManual;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.response.AddNewSpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceMembersResponse;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.example.splityourbillsandroid.ui.main.SpaceMembers.SpaceMembersAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class TransactionAmountFragment extends Fragment {


    RecyclerView recyclerView;
    @Inject
    TransactionAmountAdapter adapter;
    @Inject
    MainViewModel viewModel;

    MaterialButton saveBTN;
    LinearLayout parentLayout;

    TextView totalTV;
    Long totalAmount = Long.valueOf(0);

    private static final String TAG = "TransactionAmountFragme";


    Long spaceId = Long.valueOf(0);
    List<SpaceMembersResponse> mList;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Log.d(TAG, "onClick: POsition ::: " + position);


         /*   //Convert it to long in the next fragment
            String spaceId = String.valueOf(mList.get(position).getSpaceId());

            Bundle bundle = new Bundle();
            bundle.putString(Constants.SPACE_ID, spaceId);

            spaceDetailsFragment.setArguments(bundle);

            initializeFragments(spaceDetailsFragment);*/




        }
    };



    @Inject
    public TransactionAmountFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_transaction_amount, container, false);

        AndroidSupportInjection.inject(this);


        mList  = new ArrayList<>();
        subscribeForAmount();

        initializeViews(view);

        viewModel.getSpaceMembersBySpaceId(spaceId);

        subscribeForSpaceId();

        setUpRecyclerView(recyclerView, adapter);

        subscribeObserverForSpaceMembers();


        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTransaction();
            }
        });

        return view;
    }

    private void saveTransaction() {

        int values = adapter.getValues();
        Log.d(TAG, "saveTransaction:  " + values);
        if (values!=totalAmount){
            showToast("These amount should be summed upto the correct amount");
            totalTV.setText(values+" / "+ totalAmount);
            return;
        }else if (values==totalAmount){
            totalTV.setText(values+" / "+ totalAmount);
            showToast("Saving the transaction");
            return ;
        }


    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    private void subscribeObserverForSpaceMembers() {
        viewModel.getSpaceMembers().observe(this, new Observer<List<SpaceMembersResponse>>() {
            @Override
            public void onChanged(List<SpaceMembersResponse> spaceMembersResponses) {
                mList = spaceMembersResponses;

                adapter.updateListData(mList);
                //TODO fix it the invite thingy

            }
        });
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

    private void subscribeForAmount(){
        viewModel.getTransactionAmount().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                totalAmount = aLong;
            }
        });
    }

    private void initializeViews(View view) {

        recyclerView = view.findViewById(R.id.recycler_view);
        totalTV = view.findViewById(R.id.tv_total);
        totalTV.setText("Amount : " + 0 + " / " + totalAmount);
        saveBTN = view.findViewById(R.id.btn_save);
        parentLayout = view.findViewById(R.id.parent_layout);
    }


    private void setUpRecyclerView(RecyclerView recyclerView, TransactionAmountAdapter adapter) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);




    }

    private void showToast(String msg) {
        Snackbar snackbar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_INDEFINITE).
                setDuration(2000);
        snackbar.show();
    }


}