package com.example.maovideoplayer;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URL;
import java.util.List;

/**
 * Created by 67698 on 2018/5/19.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> VideoUrl=null;
    private List<String> itemtext=null;
    private List<String> zhan=null;
    private List<String> name=null;
    private Context mcontext;
    private mVideoView videoplayer;
    private int ss=0;
    public RecyclerAdapter(List<String> videoUrl,List<String>itemtext,List<String>zhan,List<String>name)
    {
        VideoUrl=videoUrl;
        this.itemtext=itemtext;
        this.zhan=zhan;
        this.name=name;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mcontext==null)mcontext=parent.getContext();
        View view= LayoutInflater.from(mcontext).inflate(R.layout.video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder holder, final int position) {
        Log.d("此时的Text为", " "+itemtext.get(position));
        videoplayer=holder.videoView;
        videoplayer.setVideoURI(Uri.parse(VideoUrl.get(position)));
        videoplayer.start();
        holder.textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        holder.textView.setText(itemtext.get(position));
        holder.zhan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        holder.zhan.setText(zhan.get(position));
        holder.name.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        holder.name.setText(name.get(position));
        holder.button1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            videoplayer=holder.videoView;
            videoplayer.setVideoURI(Uri.parse(VideoUrl.get(position)));
            videoplayer.start();
        }
    });
    holder.button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ss++;
            videoplayer=holder.videoView;
            videoplayer.setVideoURI(Uri.parse(VideoUrl.get(position)));
            if(ss==1)videoplayer.pause();
            else if(ss==2){
                videoplayer.start();
                ss=0;
            }
        }
    });
    holder.button3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            videoplayer=holder.videoView;
            videoplayer.setVideoURI(Uri.parse(VideoUrl.get(position)));
            videoplayer.resume();
        }
    });
    }

    @Override
    public int getItemCount() {
        //Log.d("ItemCount", "数组的大小 "+VideoUrl.size());
        return VideoUrl.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        mVideoView videoView;
        CardView cardView;
        ImageView button1;
        ImageView button2;
        ImageView button3;
        TextView textView;
        TextView zhan;
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            videoView=(mVideoView) itemView.findViewById(R.id.video);
            button1=(ImageView) itemView.findViewById(R.id.buttonPane);
            button2=(ImageView) itemView.findViewById(R.id.buttonPane2);
            button3=(ImageView) itemView.findViewById(R.id.buttonPane3);
            textView=(TextView)itemView.findViewById(R.id.itemtext);
            zhan=(TextView)itemView.findViewById(R.id.zhan);
            name=(TextView)itemView.findViewById(R.id.name);
        }
    }
}
