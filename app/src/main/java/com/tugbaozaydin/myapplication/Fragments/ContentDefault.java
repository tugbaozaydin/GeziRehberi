package com.tugbaozaydin.myapplication.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tugbaozaydin.myapplication.Activity.DetayActivity;
import com.tugbaozaydin.myapplication.Adapter.MyRecyclerViewAdapter;
import com.tugbaozaydin.myapplication.Model.CardViewObject;
import com.tugbaozaydin.myapplication.R;

import java.util.ArrayList;


public class ContentDefault extends Fragment {
    String key = null;
    String title = null;
    int layoutId = 0;
    ArrayList<CardViewObject> cardViewObjects;
    RecyclerView mRecyclerView;
    MyRecyclerViewAdapter mAdapter;

    public ContentDefault() {

    }

    public void fillContent(String key, String title, int layoutId,
                            ArrayList<CardViewObject> cardViewObjects,
                            RecyclerView mRecyclerView) {
        this.key = key;
        this.title = title;
        this.layoutId = layoutId;
        this.cardViewObjects = cardViewObjects;
        this.mRecyclerView = mRecyclerView;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);

        View view =  inflater.inflate(layoutId, container, false);

        if ("main".equals(this.key)) {
            this.mRecyclerView = (RecyclerView) view.findViewById(R.id.myRecyclerView);
            this.mRecyclerView.setHasFixedSize(true);
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            this.mAdapter = new MyRecyclerViewAdapter(cardViewObjects);
            this.mRecyclerView.setAdapter(mAdapter);


            ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onItemClick(int position, View v) {
                    Log.d("log",
                            cardViewObjects.get(position).getName());
                    Intent intent = new Intent(getContext(), DetayActivity.class);
                    intent.putExtra("id", cardViewObjects.get(position));
                    startActivity(intent);
                }
            });

        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(title);
    }
}
