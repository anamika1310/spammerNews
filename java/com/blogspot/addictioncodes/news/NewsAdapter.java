package com.blogspot.addictioncodes.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 7/18/2017.
 */

public class NewsAdapter extends ArrayAdapter<news> {

    public NewsAdapter(@NonNull Context context, ArrayList<news> data) {
        super(context, 0,data);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View v=convertView;
        if(v==null)
            v= LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent,false);
        news n=getItem(position);
        TextView tt=(TextView) v.findViewById(R.id.text);
        tt.setText(n.getSt());
        TextView textView=(TextView) v.findViewById(R.id.des);
        textView.setText(n.getSt1());
        TextView t1=(TextView) v.findViewById(R.id.date);
        t1.setText(n.getDa());
        TextView t2=(TextView) v.findViewById(R.id.time);
        t2.setText(n.getT());
        return v;
    }
}