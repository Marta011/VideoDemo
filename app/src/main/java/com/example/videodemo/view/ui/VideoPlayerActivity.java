package com.example.videodemo.view.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.videodemo.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    private String TAG = "ExoPlayer";
    private String url;
    private SimpleExoPlayer player;
    private StyledPlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        getValueFromIntent();
        attributeWidgets();
        setExoPlayer();
        setExoPlayersListeners();
    }

    private void getValueFromIntent() {
        url = getIntent().getStringExtra("url");
    }

    private void attributeWidgets() {
        playerView = findViewById(R.id.player_view);
    }

    private void setExoPlayer() {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        player.setMediaItem(MediaItem.fromUri(url));
        player.prepare();
        player.play();
    }

    private void setExoPlayersListeners() {
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerError(ExoPlaybackException error) {
                switch (error.type) {
                    case ExoPlaybackException.TYPE_SOURCE:
                        Log.e(TAG, "TYPE_SOURCE: " + error.getSourceException().getMessage());
                        displayErrorDialog();
                        break;

                    case ExoPlaybackException.TYPE_RENDERER:
                        Log.e(TAG, "TYPE_RENDERER: " + error.getRendererException().getMessage());
                        displayErrorDialog();
                        break;

                    case ExoPlaybackException.TYPE_UNEXPECTED:
                        Log.e(TAG, "TYPE_UNEXPECTED: " + error.getUnexpectedException().getMessage());
                        displayErrorDialog();
                        break;
                }
            }
        });
    }

    private void displayErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.error_msg)
                .setTitle(R.string.error_title)
                .setPositiveButton(R.string.confirm_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getApplication().startActivity(new Intent(getApplication(), MainActivity.class));
                    }
                });
        builder.show();
    }
}