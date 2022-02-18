package com.example.myproyect_2022;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterHolder> implements View.OnClickListener {

    private Context context;
    private List<Character> characterList;
    private View.OnClickListener listener;

    public CharacterAdapter(Context context, List<Character> characterList) {
        this.context = context;
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.character, parent, false);
        view.setOnClickListener(this);
        return new CharacterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
        Character character = characterList.get(position);
        holder.textViewName.setText(character.getName());
        Glide.with(context).load(character.getImgURL()).into(holder.imageView);

//        Picasso.get().load("http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg").into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }

//  Holder class -------------------------------------------------------------------
    public class CharacterHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageView;
        CardView cardView;

        public CharacterHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.name);
            imageView = (ImageView) itemView.findViewById(R.id.cardImg);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
