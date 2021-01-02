package com.example.videodemo.view.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.videodemo.R;
import com.example.videodemo.service.dao.IVideoDao;
import com.example.videodemo.service.model.Video;
import com.example.videodemo.service.network.MyRetrofit;
import com.example.videodemo.view.adapter.VideoListAdapter;
import com.example.videodemo.viewmodel.VideoViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static VideoViewModel mVideoViewModel;
    private RecyclerView recyclerView;
    private VideoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        attributeWidgets();
        getVideosFromJson();
        setVideoViewModel();
        setRecyclerViewAndAdapter();
    }

    private void setRecyclerViewAndAdapter() {
        adapter = new VideoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setVideoViewModel() {
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

    public void getVideosFromJson() {
        Call<List<Video>> call = MyRetrofit.getInstance().getVideoApi().getVideoFromUrl();
        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                List<Video> videos = response.body();
                for (Video video : videos) {
                    video.setQuantity(0);
                }
                mVideoViewModel.insert(videos);
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}