package com.example.splityourbillsandroid.ui.main.viewDetailsTXN;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceResponse;
import com.example.splityourbillsandroid.data.models.transactions.PersonDetailsTXN;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ViewTXNDetailsAdapter extends RecyclerView.Adapter<ViewTXNDetailsAdapter.viewholder> {


    private ArrayList<PersonDetailsTXN> mList;
    private View.OnClickListener onItemClickListener;
    private long perPersonAmount =0;

    public void setPerPersonAmount(long perPersonAmount) {
        this.perPersonAmount = perPersonAmount;
    }

    private static final String TAG = "HomeAdapter";


    @Inject
    public ViewTXNDetailsAdapter() {
        mList = new ArrayList<>();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_view_txn_details, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {



        if (mList.get(position).getInviteId()==-1){
            //for person
            holder.name.setText(mList.get(position).getUserResponse().getUserName() + " paid " + mList.get(position).getTotalPaid().toString());
          //  holder.description.setText(mList.get(position).getTotalPaid().toString());
        }else if (mList.get(position).getPersonId()==-1){
            //for invites
            holder.name.setText(mList.get(position).getInviteResponse().getName() + " paid " + mList.get(position).getTotalPaid().toString());
           // holder.description.setText(mList.get(position).getTotalPaid().toString());
        }

        if (mList.get(position).getTotalPaid()==perPersonAmount){
            holder.description.setText("Settled up!!!");
        }else if (mList.get(position).getTotalPaid()>perPersonAmount){
            holder.description.setText("Owes " + (mList.get(position).getTotalPaid()-perPersonAmount));
            holder.description.setTextColor(Color.parseColor("#37465B"));
        }else if (mList.get(position).getTotalPaid()<perPersonAmount){
            holder.description.setText("Needs to pay : " +(perPersonAmount-mList.get(position).getTotalPaid()));
            holder.description.setTextColor(Color.parseColor("#FF0000"));
        }


    }

    @Override
    public int getItemCount() {

            return mList.size();
    }

    public void updateListData(List<PersonDetailsTXN> data) {
        mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();

    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView name,description;

        public viewholder(@NonNull View itemView) {

            super(itemView);

            itemView.setTag(this);

            name = itemView.findViewById(R.id.transaction_name);
            description = itemView.findViewById(R.id.transaction_description);

            itemView.setOnClickListener(onItemClickListener);

        }

    }
}
