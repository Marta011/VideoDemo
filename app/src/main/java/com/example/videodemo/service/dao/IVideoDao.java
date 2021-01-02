package com.example.videodemo.service.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.videodemo.service.model.Video;

import java.util.List;

@Dao
public interface IVideoDao {

    @Insert
    void insertVideos(List<Video> video);

    @Query("SELECT * FROM Videos ORDER BY Quantity DESC")
    LiveData<List<Video>> getAllVideos();
}
