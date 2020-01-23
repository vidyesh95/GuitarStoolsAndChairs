package com.righttickk.guitarstoolsandchairs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.righttickk.guitarstoolsandchairs.R;
import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {
    private ArrayList<RecyclerViewItem> mRecyclerViewList;

    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> recyclerViewList) {
        mRecyclerViewList = recyclerViewList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(v);
        return recyclerViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewViewHolder holder, int position) {
        RecyclerViewItem currentItem = mRecyclerViewList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mTextView3.setText(currentItem.getText3());
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewList.size();
    }

    public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image);
            mTextView1 = itemView.findViewById(R.id.item_line1);
            mTextView2 = itemView.findViewById(R.id.item_line2);
            mTextView3 = itemView.findViewById(R.id.item_line3);
        }
    }
}
