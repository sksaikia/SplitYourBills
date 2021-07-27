package com.example.splityourbillsandroid.ui.main.spaceDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.TransactionsResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.viewholder> {


    private ArrayList<TransactionsResponse> mList;
    private View.OnClickListener onItemClickListener;

    private static final String TAG = "HomeAdapter";


    @Inject
    public TransactionsAdapter() {
        mList = new ArrayList<>();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_transactions, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {


        String str = mList.get(position).getUserDetails().getUserName() + " paid " + mList.get(position).getAmount().toString();
        holder.name.setText(str);
        holder.description.setText(mList.get(position).getDescription());



    }

    @Override
    public int getItemCount() {

            return mList.size();
    }

    public void updateListData(List<TransactionsResponse> data) {
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
