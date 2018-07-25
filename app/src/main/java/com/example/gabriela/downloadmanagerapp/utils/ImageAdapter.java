package com.example.gabriela.downloadmanagerapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.gabriela.downloadmanagerapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder>{
    private  List<File> files;
    private OnClickRecycle listener;
    Context context;
    private int idLayout;

    // Constructor
    public ImageAdapter(Context context,List<File> files,int idLayout,OnClickRecycle listener) {
        this.files = files;
        this.context = context;
        this.idLayout = idLayout;
        this.listener = listener;
    }

    public void setmDataSet(List<File> file ) {
        this.files = file;
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflater Recycler
        View view = LayoutInflater.from(parent.getContext()).inflate(idLayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(this.files.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    // Interface to click Recycle
    public interface OnClickRecycle{
        void OnClickItemRecycle(File file);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imvGallery) ImageView imvGallery;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final File file , final OnClickRecycle listener){
            Picasso.get().load(file).into(imvGallery);

            imvGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClickItemRecycle(file);
                }
            });
        }

    }
}
