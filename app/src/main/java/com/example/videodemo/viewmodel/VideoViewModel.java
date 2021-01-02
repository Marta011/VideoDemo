package com.example.videodemo.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.videodemo.service.model.Video;
import com.example.videodemo.service.repository.VideoRepository;
import com.example.videodemo.view.ui.MainActivity;

import java.util.List;

public class VideoViewModel extends AndroidViewModel {

    private VideoRepository mRepository;

    private LiveData<List<Video>> mAllVideos;

    public VideoViewModel (Application application) {
        super(application);
        mRepository = new VideoRepository(application);
        mAllVideos = mRepository.getAllVideos();
    }

    public LiveData<List<Video>> getAllVideos() { return mAllVideos; }

    public void insert(List<Video> videos) { mRepository.insert(videos); }

    public void update(Video video) { mRepository.update(video); }
}
