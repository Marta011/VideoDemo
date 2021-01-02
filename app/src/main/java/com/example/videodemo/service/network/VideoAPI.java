package com.example.videodemo.service.network;

import com.example.videodemo.service.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VideoAPI {

    @GET("index.php")
    Call<List<Video>> getVideoFromUrl();
}
