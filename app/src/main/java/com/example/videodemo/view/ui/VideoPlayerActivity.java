package com.example.videodemo.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.videodemo.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    String url;
    SimpleExoPlayer player;
    StyledPlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        getValueFromIntent();
        attributeWidgets();

        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri(url);
// Set the media item to be played.
        player.setMediaItem(mediaItem);
// Prepare the player.
        player.prepare();
// Start the playback.
        player.play();
    }

    private void getValueFromIntent() {
        Intent myIntent = getIntent();
        url = myIntent.getStringExtra("url");
    }

    private void attributeWidgets() {
        playerView = findViewById(R.id.player_view);
    }
}