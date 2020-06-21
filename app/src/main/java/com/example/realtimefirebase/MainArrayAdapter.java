package com.example.realtimefirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainArrayAdapter extends ArrayAdapter<String> {
    public MainArrayAdapter(Context context, List<String> fruits){
        super(context, 0,fruits);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View itemView = convertView;
        ViewHolder viewHolder;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_main,parent, false);

            viewHolder = new ViewHolder(itemView);

            itemView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) itemView.getTag();
        }
        String fruitName = getItem(position);
        viewHolder.fruitNameTextView.setText(fruitName);

        return itemView;
    }

    public void nofifyDataSetChanged() {
    }

    private class ViewHolder {
        TextView fruitNameTextView;
        public ViewHolder(View itemView) {
            fruitNameTextView = itemView.findViewById(R.id.main_fruit_name_textView);
        }
    }
}
