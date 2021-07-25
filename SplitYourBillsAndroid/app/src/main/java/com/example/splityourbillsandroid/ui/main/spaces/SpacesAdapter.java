package com.example.splityourbillsandroid.ui.main.spaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splityourbillsandroid.R;
import com.example.splityourbillsandroid.data.models.spaces.SpaceResponse;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SpacesAdapter extends RecyclerView.Adapter<SpacesAdapter.viewholder> {


    private ArrayList<SpaceResponse> mList;
    private View.OnClickListener onItemClickListener;

    private static final String TAG = "HomeAdapter";


    @Inject
    public SpacesAdapter() {
        mList = new ArrayList<>();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_space_home, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {


        holder.name.setText(mList.get(position).getSpaceName());



    }

    @Override
    public int getItemCount() {

            return mList.size();
    }

    public void updateListData(List<SpaceResponse> data) {
        mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();

    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView name;

        public viewholder(@NonNull View itemView) {

            super(itemView);

            itemView.setTag(this);

            name = itemView.findViewById(R.id.space_name);

            itemView.setOnClickListener(onItemClickListener);

        }

    }
}
