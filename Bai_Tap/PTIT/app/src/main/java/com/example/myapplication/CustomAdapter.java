package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context context;
    int layoutId;
    ArrayList<model> dv = new ArrayList<>();

    public CustomAdapter(@NonNull Context context, int layoutId, ArrayList<model> dv) {
        super(context, layoutId);
        this.context = context;
        this.layoutId = layoutId;
        this.dv = dv;
    }

    @Override
    public int getCount() {
        return dv.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater.from(context));
        convertView = inflater.inflate(layoutId, null);
        TextView tvLoaiDV = convertView.findViewById(R.id.tvLoaiDV);
        TextView tvDay = convertView.findViewById(R.id.tvDay);
        TextView tvUser = convertView.findViewById(R.id.tvUser);
        tvLoaiDV.setText(dv.get(position).getLoaiDV());
        tvDay.setText(dv.get(position).getDay());
        tvUser.setText(dv.get(position).getUser());
        return  convertView;
    }

}
