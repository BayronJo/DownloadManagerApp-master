package com.example.gabriela.downloadmanagerapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.gabriela.downloadmanagerapp.R;
import com.example.gabriela.downloadmanagerapp.activities.DownloadActivity;
import com.example.gabriela.downloadmanagerapp.utils.ImageAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentGallery extends DialogFragment{

    View view;
    @BindView(R.id.myImageList) RecyclerView myTodoList;
    @BindView(R.id.bigImage) ImageView bigImage;
    @BindView(R.id.fabAdd) FloatingActionButton fabAdd;
    private ImageAdapter adapter;
    private  List<File> files;
    private  File Path;
    private ImageAdapter.OnClickRecycle listener;

    public FragmentGallery() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_gallery, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.Path = new File(Environment.getExternalStorageDirectory()+"/AppFolder");
        this.files = this.getListFiles(this.Path);
        this.adapter = new ImageAdapter(getContext(), files, R.layout.adapter, new ImageAdapter.OnClickRecycle() {
            @Override
            public void OnClickItemRecycle(File file) {
                Picasso.get().load(file).into(bigImage);
            }
        });
        this.adapter.setmDataSet(files);
        this.myTodoList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        this.myTodoList.setAdapter(this.adapter);
        this.myTodoList.setItemAnimator(new DefaultItemAnimator());
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                startActivity(intent);
            }
        });



        this.adapter.notifyDataSetChanged();
    }


    private List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<>();
        File[] files = parentDir.listFiles();
        if(files != null && files.length > 0){
            for (File file : files) {
                if (file.isDirectory()) {
                    inFiles.addAll(getListFiles(file));
                } else {
                    inFiles.add(file);
                }
            }
        }

        return inFiles;
    }
}
