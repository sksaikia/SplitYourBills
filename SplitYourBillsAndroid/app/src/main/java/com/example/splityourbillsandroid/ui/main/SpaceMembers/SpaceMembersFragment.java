package com.example.splityourbillsandroid.ui.main.SpaceMembers;

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
import android.widget.TextView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.response.AddNewSpaceResponse;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceMembersResponse;
import com.example.splityourbillsandroid.ui.main.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class SpaceMembersFragment extends Fragment {

    private static final String TAG = "SpaceMembersFragment";

    LinearLayout linearLayout;
    RecyclerView recyclerView;
    @Inject
    SpaceMembersAdapter adapter;
    @Inject
    MainViewModel viewModel;

    TextView invites;

    Long spaceId = Long.valueOf(0);
    List<SpaceMembersResponse> mList;

    int inviteCount = 0 ;


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


    private void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //   transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout_main, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }




    @Inject
    public SpaceMembersFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_space_members, container, false);


        AndroidSupportInjection.inject(this);
        mList  = new ArrayList<>();


        initializeViews(view);




        subscribeForSpaceId();

        setUpRecyclerView(recyclerView, adapter);

        subscribeObserverForSpaceMembers();

        return view;
    }

    private void subscribeObserverForSpaceMembers() {
        viewModel.getSpaceMembers().observe(this, new Observer<List<SpaceMembersResponse>>() {
            @Override
            public void onChanged(List<SpaceMembersResponse> spaceMembersResponses) {
                mList = spaceMembersResponses;

                Log.d(TAG, "onChanged: inviteCount : " + inviteCount);
                adapter.updateListData(mList);
                //TODO fix it the invite thingy
                inviteCount = adapter.getInviteCount();
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

    private void initializeViews(View view) {
        linearLayout = view.findViewById(R.id.linear_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
        invites = view.findViewById(R.id.invite_number);

    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }



    private void setUpRecyclerView(RecyclerView recyclerView, SpaceMembersAdapter adapter) {

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