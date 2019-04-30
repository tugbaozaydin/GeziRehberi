package com.tugbaozaydin.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tugbaozaydin.myapplication.Model.Aktivite;
import com.tugbaozaydin.myapplication.R;

import java.util.ArrayList;

public class ActivityAdapter extends BaseAdapter {
    ArrayList<Aktivite> aktiviteler;
    Context context;
    LayoutInflater layoutInflater;

    public ActivityAdapter( Context context,ArrayList<Aktivite> list) {
        this.aktiviteler = list;
        this.context = context;
        this.layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return aktiviteler.size();
    }

    @Override
    public Object getItem(int position) {
        return aktiviteler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return aktiviteler.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =  layoutInflater.inflate(R.layout.ozel_satir,null);
        //İçerisindeki nesneleri tanımlamamız gereliyor
        ImageView ımageView = v.findViewById(R.id.imageView);
        int id = context.getResources().getIdentifier(
                aktiviteler.get(position).getImage(), "drawable",context.getPackageName()
        );
        Glide.with(context).load(id).into(ımageView);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Haritaya bağlan
            }
        });
        return  v;
    }
}
