package com.righttickk.guitarstoolsandchairs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.righttickk.guitarstoolsandchairs.R;
import com.righttickk.guitarstoolsandchairs.model.RecyclerViewItem;

public class RecyclerViewAdapter extends
        ListAdapter<RecyclerViewItem, RecyclerViewAdapter.RecyclerViewViewHolder> {

    private OnItemClickListener listener;

    public RecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<RecyclerViewItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<RecyclerViewItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull RecyclerViewItem oldItem,
                                       @NonNull RecyclerViewItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull RecyclerViewItem oldItem,
                                          @NonNull RecyclerViewItem newItem) {
            return oldItem.getImageResource()==newItem.getImageResource() &&
                    oldItem.getText1().equals(newItem.getText1()) &&
                    oldItem.getText2().equals(newItem.getText2()) &&
                    oldItem.getText3().equals(newItem.getText3());
        }
    };

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                         int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(itemView);
        return recyclerViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewViewHolder holder, int position) {
        RecyclerViewItem currentItem = getItem(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mTextView3.setText(currentItem.getText3());
    }

    public RecyclerViewItem getRecyclerViewItemAt(int position) {
        return getItem(position);
    }

    public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView1;
        private TextView mTextView2;
        private TextView mTextView3;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image);
            mTextView1 = itemView.findViewById(R.id.item_line1);
            mTextView2 = itemView.findViewById(R.id.item_line2);
            mTextView3 = itemView.findViewById(R.id.item_line3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerViewItem recyclerViewItem);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
