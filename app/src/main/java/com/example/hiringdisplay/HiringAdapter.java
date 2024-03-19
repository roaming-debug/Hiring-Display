package com.example.hiringdisplay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Adapter for the hiring list
 */
public class HiringAdapter extends RecyclerView.Adapter<HiringAdapter.ViewHolder> {
    ArrayList<HiringItem> data;

    /**
     * This method is called when the adapter is created.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hiring_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This method is called when the adapter is created.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setText(data.get(position));
    }

    /**
     * This method is called when the adapter is created.
     * @return The total number of items in the data set held by the adapter.
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * ViewHolder for the hiring list
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView idTextView;
        private final TextView listidTextView;
        private final TextView nameTextView;
        /**
         * Constructor for the ViewHolder
         * @param view The view for the ViewHolder
         */
        public ViewHolder(View view) {
            super(view);
            idTextView = (TextView) view.findViewById(R.id.hiring_item_id);
            listidTextView = (TextView) view.findViewById(R.id.hiring_item_list_id);
            nameTextView = (TextView) view.findViewById(R.id.hiring_item_name);
        }
        /**
         * This method sets the text for the ViewHolder
         * @param item The hiring item to set the text for
         */
        public void setText(HiringItem item) {
            idTextView.setText(String.valueOf(item.getID()));
            listidTextView.setText(String.valueOf(item.getListID()));
            nameTextView.setText(item.getName());
        }
    }

    /**
     * Constructor for the HiringAdapter
     * @param data The data for the HiringAdapter
     */
    public HiringAdapter(ArrayList<HiringItem> data) {
        this.data = data;
    }

}
