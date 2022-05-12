package com.example.ibrahimr.movieum.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibrahimr.movieum.R;

import java.util.List;

/**
 * Created by IbrahimR on 8/22/2020.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {

    private List<String> castImage, castName;
    private Context context;

    public CastAdapter(Context context, List<String> castImage, List<String> castName) {
        this.castImage = castImage;
        this.castName = castName;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cast_item,parent,false);

        return new CastAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context)
                .load(castImage.get(position))
                .into(holder.castImageView);
        holder.castNameTextView.setText(castName.get(position));

    }

    @Override
    public int getItemCount() {
        return castName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView castImageView;
        TextView castNameTextView;


        public ViewHolder(View itemView) {
            super(itemView);

            castImageView = itemView.findViewById(R.id.castImage);
            castNameTextView = itemView.findViewById(R.id.castName);
        }
    }
}
