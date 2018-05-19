package com.example.maovideoplayer;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import java.net.URL;
import java.util.List;

/**
 * Created by 67698 on 2018/5/19.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> VideoUrl=null;
    private Context mcontext;
    private VideoView videoplayer;
    public RecyclerAdapter(List<String> videoUrl)
    {
        VideoUrl=videoUrl;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mcontext==null)mcontext=parent.getContext();
        View view= LayoutInflater.from(mcontext).inflate(R.layout.video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
    videoplayer=holder.videoView;
    videoplayer.setVideoURI(Uri.parse(VideoUrl.get(position)));
    holder.button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            videoplayer.start();
        }
    });
    holder.button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            videoplayer.pause();
        }
    });
    holder.button3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            videoplayer.resume();
        }
    });
    }

    @Override
    public int getItemCount() {
        return VideoUrl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        CardView cardView;
        Button button1;
        Button button2;
        Button button3;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            videoView=(VideoView)itemView.findViewById(R.id.video);
            button1=(Button)itemView.findViewById(R.id.buttonPane);
            button2=(Button)itemView.findViewById(R.id.buttonPane2);
            button3=(Button)itemView.findViewById(R.id.buttonPane3);
        }
    }
}
