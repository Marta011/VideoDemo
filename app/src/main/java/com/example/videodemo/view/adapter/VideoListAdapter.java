package com.example.videodemo.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.videodemo.R;
import com.example.videodemo.service.model.Video;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

    private final LayoutInflater mInflater;
    private List<Video> mVideos;

    public VideoListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        if (mVideos != null) {
            Video current = mVideos.get(position);
            holder.videoItemView.setText(current.getTitle());
        } else {
            holder.videoItemView.setText("No Video");
        }
    }

    public void setVideos(List<Video> videos){
        mVideos = videos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mVideos != null)
            return mVideos.size();
        else return 0;
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        private final TextView videoItemView;

        private VideoViewHolder(View itemView) {
            super(itemView);
            videoItemView = itemView.findViewById(R.id.textView_title);
        }
    }
}
