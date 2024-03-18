package com.example.hiringdisplay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HiringAdapter extends RecyclerView.Adapter<HiringAdapter.ViewHolder> {
    ArrayList<HiringItem> data;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hiring_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView idTextView;
        private final TextView listidTextView;
        private final TextView nameTextView;
        public ViewHolder(View view) {
            super(view);
            idTextView = (TextView) view.findViewById(R.id.hiring_item_id);
            listidTextView = (TextView) view.findViewById(R.id.hiring_item_list_id);
            nameTextView = (TextView) view.findViewById(R.id.hiring_item_name);
        }
        public void setText(HiringItem item) {
            idTextView.setText(String.valueOf(item.getID()));
            listidTextView.setText(String.valueOf(item.getListID()));
            nameTextView.setText(item.getName());
        }
    }



    public HiringAdapter(ArrayList<HiringItem> data) {
        this.data = data;
    }

}
