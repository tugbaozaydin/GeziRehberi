package com.tugbaozaydin.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tugbaozaydin.myapplication.Model.CardViewObject;
import com.tugbaozaydin.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TugbaOzaydin on 11.03.2019.
 */

public class MyRecyclerViewAdapter extends
        RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<CardViewObject> mDataset;
    private static MyClickListener myClickListener;
    private  static Context context;
    LayoutInflater layoutInflater;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivMekanResim;
        TextView txtMekanAdi,txtMekanAciklama;

        public DataObjectHolder(View itemView) {
            super(itemView);

            ivMekanResim = (ImageView) itemView.findViewById(R.id.ivMekanResim);
            txtMekanAdi = (TextView) itemView.findViewById(R.id.txtMekanAdi);
            //txtMekanAciklama = (TextView) itemView.findViewById(R.id.txtMekanAciklama);

            //nesneler burada tanımlanacak
            Log.d(LOG_TAG, "Listener ekleniyor");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);

        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public  MyRecyclerViewAdapter(ArrayList<CardViewObject> mDataset){
        this.mDataset = mDataset;

    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_gorunumu,parent,false);
        this.context = parent.getContext();
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return  dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, final int position) {
       int id = context.getResources().getIdentifier(
               mDataset.get(position).getImage(),
               "drawable",context.getPackageName());

        //Glide.with(context).load(id).into(holder.ivMekanResim);
        holder.ivMekanResim.setImageResource(id);

        holder.txtMekanAdi.setText(mDataset.get(position).getName());
        //holder.txtMekanAciklama.setText(mDataset.get(position).getText());
        //tıklanma olayı
        /*
        holder.ivMekanResim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(this,MapsActivity.class);
                i.putExtra("id",mDataset.get(position).getId());
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItem(CardViewObject cardViewObject,int index){
        mDataset.add(index,cardViewObject);

    }
    public void  deleteItem(int index)
    {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
    public interface  MyClickListener {
        public  void  onItemClick(int position,View v);
    }
}
