package com.example.splityourbillsandroid.ui.main.transactionAmountManual;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.response.SpaceMembersResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class TransactionAmountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<SpaceMembersResponse> mList;
    private View.OnClickListener onItemClickListener;
    private List<Integer> values;

    private static final String TAG = "HomeAdapter";
    int invites = 0;

    private HashMap<Integer,Pair> map;
    private HashMap<Integer,Integer> hashMap;


    @Inject
    public TransactionAmountAdapter() {
        mList = new ArrayList<>();
        values = new ArrayList<>();
        hashMap = new HashMap<>();
        map = new HashMap<>();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_amount, parent, false);
            return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


            viewholder viewholder = (viewholder) holder;
            if (mList.get(position).getUserId()!=-1) {
                viewholder.name.setText(mList.get(position).getUserDetails().getUserName());
            }else{
                viewholder.name.setText(mList.get(position).getInvites().getName());
            }


    }

    @Override
    public int getItemCount() {

//        int size  = 0 ;
//        for (SpaceMembersResponse s : mList){
//            if (s.getUserId()!=-1)
//                size++;
//        }
//        invites = mList.size()-size;
//        return size;
        return mList.size();
    }

    public int getInviteCount(){
        return invites;
    }

    public int getValues(){
//        int sum=0;
//        for (int c:values) {
//            Log.d(TAG, "getValues: " + c);
//            sum += c;
//
//        }
//        Log.d(TAG, "getValues: " + sum);
//        Log.d(TAG, "getValues: Empty");

        int ans = 0;

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry mapElement = (Map.Entry) it.next();

            int value = (int) mapElement.getValue();
            ans += value;

            Log.d(TAG, "getValues: HashMap " + value + " ans : " + ans);
        }


        return ans;
    }

    public HashMap<Integer,Pair> getHashMapOfValues(){
        return map;
    }


    public void updateListData(List<SpaceMembersResponse> data) {
        mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();

    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView name;
        EditText amount;

        public viewholder(@NonNull View itemView) {

            super(itemView);

            itemView.setTag(this);


            name = itemView.findViewById(R.id.transaction_name);
            amount = itemView.findViewById(R.id.et_amount);

            itemView.setOnClickListener(onItemClickListener);


            amount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d(TAG, "onTextChanged: here : " + s + " start " + start + " before "  + before + " count : " + count+ " positon " + getAdapterPosition());
                    hashMap.put(getAdapterPosition(),Integer.parseInt(s.toString()));
                    map.put(getAdapterPosition(),new Pair(Long.parseLong(s.toString()),
                            mList.get(getAdapterPosition()).getUserId(),mList.get(getAdapterPosition()).getPhoneNo()));
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


        }

    }

    private class EmptyViewHolder extends  RecyclerView.ViewHolder {
        public EmptyViewHolder( @NonNull View view) {
            super(view);
        }
    }

    public class Pair {

       private Long amount;
        private long userId;
        private String phoneNo;


        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        public Pair(Long amount, long userId, String phoneNo) {
            this.amount = amount;
            this.userId = userId;
            this.phoneNo = phoneNo;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }
    }


}
