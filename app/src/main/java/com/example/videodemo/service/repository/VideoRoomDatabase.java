package com.example.videodemo.service.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.videodemo.service.dao.IVideoDao;
import com.example.videodemo.service.model.Video;

@Database(entities = {Video.class}, version = 1, exportSchema = false)
public abstract class VideoRoomDatabase extends RoomDatabase {

    public abstract IVideoDao videoDao();

    private static VideoRoomDatabase INSTANCE;

    static VideoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VideoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VideoRoomDatabase.class, "video_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
