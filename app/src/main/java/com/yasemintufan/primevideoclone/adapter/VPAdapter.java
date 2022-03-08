package com.yasemintufan.primevideoclone.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yasemintufan.primevideoclone.MovieDetails;
import com.yasemintufan.primevideoclone.R;
import com.yasemintufan.primevideoclone.model.BannerMovies;

import java.util.ArrayList;
import java.util.List;

public class VPAdapter extends RecyclerView.Adapter<VPAdapter.ViewHolder> {
    ArrayList<BannerMovies>bannerMoviesList;
    Context context;


    public VPAdapter(List<BannerMovies> bannerMoviesArrayList, Context context) {
        this.bannerMoviesList = (ArrayList<BannerMovies>) bannerMoviesArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_pager,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        BannerMovies bannerMovies = bannerMoviesList.get(position);

        Glide.with(context).load(bannerMoviesList.get(position).getImageUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MovieDetails.class);
                i.putExtra("movieId",bannerMoviesList.get(position).getId());
                i.putExtra("movieName",bannerMoviesList.get(position).getMovieName());
                i.putExtra("movieImageUrl",bannerMoviesList.get(position).getImageUrl());
                i.putExtra("movieFile",bannerMoviesList.get(position).getFileUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bannerMoviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivImage);
        }

    }
}
