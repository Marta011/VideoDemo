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

    public void update(Video video) {
        new UpdateAsyncTask(mVideoDao).execute(video);
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

    private static class UpdateAsyncTask extends AsyncTask<Video, Void, Void> {

        private IVideoDao mAsyncTaskDao;

        UpdateAsyncTask(IVideoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Video... videos) {
            videos[0].setQuantity(videos[0].getQuantity() + 1);
            mAsyncTaskDao.updateVideo(videos[0]);
            return null;
        }
    }
}
