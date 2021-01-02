package com.example.videodemo.service.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.videodemo.service.dao.IVideoDao;
import com.example.videodemo.service.model.Video;

import java.util.List;

public class VideoRepository {

    private IVideoDao mVideoDao;
    private LiveData<List<Video>> mAllVideos;
    private VideoRoomDatabase db;

    public VideoRepository(Application application) {
        db = VideoRoomDatabase.getDatabase(application);
        mVideoDao = db.videoDao();
        mAllVideos = mVideoDao.getAllVideos();
    }

    public LiveData<List<Video>> getAllVideos() {
        return mAllVideos;
    }

    public void insert(List<Video> videos) {
        new InsertAsyncTask(mVideoDao).execute(videos);
    }

    private static class InsertAsyncTask extends AsyncTask<List<Video>, Void, Void> {

        private IVideoDao mAsyncTaskDao;

        InsertAsyncTask(IVideoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<Video>... lists) {
            mAsyncTaskDao.insertVideos(lists[0]);
            return null;
        }
    }
}
