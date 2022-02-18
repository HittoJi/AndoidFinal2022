package com.example.myproyect_2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicHolder>{

    private Context context;
    private List<Comic> comicList;

    public ComicAdapter(Context context, List<Comic> comicList) {
        this.context = context;
        this.comicList = comicList;
    }

    @NonNull
    @Override
    public ComicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comic, parent, false);
        return new ComicAdapter.ComicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicHolder holder, int position) {
        Comic comic = comicList.get(position);
        Glide.with(context).load(comic.getImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }


    public class ComicHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ComicHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.comicImg);
        }
    }
}
