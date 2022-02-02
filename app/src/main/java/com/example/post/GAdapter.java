package com.example.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import java.util.ArrayList;
import java.util.Collections;

public class GAdapter  extends RecyclerView.Adapter<GAdapter.GViewHolder> {
    private ArrayList<String> mDataset;
    private Activity activity;

    public static class GViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public GViewHolder(CardView v) {
            super(v);
            cardView = v;
        }


    }

    public GAdapter(Activity activity, ArrayList<String> myDataset) {
        mDataset = myDataset;
        Collections.reverse(mDataset);
        this.activity = activity;
    }

    @Override
    public GAdapter.GViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_g, parent, false);

        return new GViewHolder(cardView);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull final GViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, mDataset.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                Bitmap bmp = BitmapFactory.decodeFile(mDataset.get(holder.getAdapterPosition()));
                ((MainActivity)MainActivity.mContext).iv.setImageBitmap(bmp);
            }
        });
        ImageView imageView = cardView.findViewById(R.id.imageView);
        Glide.with(activity).load(mDataset.get(position)).centerCrop().override(500).into(imageView);
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}