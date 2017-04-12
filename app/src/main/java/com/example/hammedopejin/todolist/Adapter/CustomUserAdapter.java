package com.example.hammedopejin.todolist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hammedopejin.todolist.Items;
import com.example.hammedopejin.todolist.R;


/**
 * Created by hammedopejin on 3/1/17.
 */

public class CustomUserAdapter extends ArrayAdapter<Items> {
    //View lookup cache
    private static class ItemHolder {
        TextView item;
        TextView priority;
    }

    Context context;
    int layoutResourceId;
    Items data[] = null;

    public CustomUserAdapter(Context context, int layoutResourceId, Items[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        //ViewHolder viewHolder;//view lookup cache stored in tag
        View row = convertView;
        ItemHolder holder;


        // Check if an existing view is being reused, otherwise inflate the view
        if (row == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.item_user, parent, false);

            holder = new ItemHolder();

            // Lookup view for data population
            holder.item = (TextView) row.findViewById(R.id.tvName);
            holder.priority = (TextView) row.findViewById(R.id.tvPri);


            //Cache the viewHolder object inside the fresh view
            row.setTag(holder);
        } else {
            //View is being recycled, retrieve the viewHolder object from tag
            holder = (ItemHolder) row.getTag();
        }
        Items item = data[position];
        // Populate the data from the data object using the viewHolder object

        holder.item.setText(item.todo);
        holder.priority.setText(item.pri);

        return row;
    }

}