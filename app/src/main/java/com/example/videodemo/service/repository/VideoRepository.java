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

    public VideoRepository(Application application) {
        VideoRoomDatabase db = VideoRoomDatabase.getDatabase(application);
        mVideoDao = db.videoDao();
        mAllVideos = mVideoDao.getAllVideos();
    }

    public LiveData<List<Video>> getAllVideos() {
        return mAllVideos;
    }

    public void insert(Video video) {
        new InsertAsyncTask(mVideoDao).execute(video);
    }

    private static class InsertAsyncTask extends AsyncTask<Video, Void, Void> {

        private IVideoDao mAsyncTaskDao;

        InsertAsyncTask(IVideoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Video... params) {
            mAsyncTaskDao.insertVideo(params[0]);
            return null;
        }
    }
}
