package com.example.videodemo.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.videodemo.R;
import com.example.videodemo.service.model.Video;
import com.example.videodemo.view.adapter.VideoListAdapter;
import com.example.videodemo.viewmodel.VideoViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VideoViewModel mVideoViewModel;
    private RecyclerView recyclerView;
    private VideoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attributeWidgets();
        setRecyclerViewAndAdapter();
        setVideoViewModel();
    }

    private void setRecyclerViewAndAdapter() {
        adapter = new VideoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setVideoViewModel() {
        mVideoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        mVideoViewModel.getAllVideos().observe(this, new Observer<List<Video>>() {
            @Override
            public void onChanged(@Nullable final List<Video> videos) {
                adapter.setVideos(videos);
            }
        });
    }

    private void attributeWidgets() {
        recyclerView = findViewById(R.id.recyclerView_videos);
    }
}