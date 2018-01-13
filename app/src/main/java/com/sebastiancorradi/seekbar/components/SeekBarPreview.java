package com.sebastiancorradi.seekbar.components;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.sebastiancorradi.seekbar.R;

import java.net.URI;
import java.util.HashMap;

/**
 * Created by Gregorio on 1/2/2018.
 */

public class SeekBarPreview  extends LinearLayout {
    private SeekBar mSeekBar;
    private ImageView mPreview;
    private View rootView;

    private int totalDuration;
    private int currentPosition;
    private MediaMetadataRetriever retriever;

    public SeekBarPreview(Context context) {
        super(context);
        init();
    }

    public SeekBarPreview(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public SeekBarPreview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.seekbarpreview_layout, this, true);

        mPreview = rootView.findViewById(R.id.imageView);
        mSeekBar = rootView.findViewById(R.id.seekBar);
        retriever = new MediaMetadataRetriever();

        mPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    public void setUrl(String url){
        retriever.setDataSource(url, new HashMap<String, String>());
        String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        if (duration != null) {
            totalDuration = Integer.valueOf(duration);//milisecs
        } else {
            totalDuration = 0;
            //or throw an exception
        }
    }
    public void setUri(Uri uri){
        retriever.setDataSource(getContext(),uri);
        String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        if (duration != null) {
            totalDuration = Integer.valueOf(duration);//milisecs
        } else {
            totalDuration = 0;
            //or throw an exception
        }
    }

    public void update(int i) {
        Long newPosition = totalDuration * i * 10L;
        Bitmap bitmap = retriever.getFrameAtTime(newPosition);
        mPreview.setImageBitmap(bitmap);


        int x = mSeekBar.getThumb().getBounds().centerX();
        int newPos = x - (i * mPreview.getWidth() / 100);
        mPreview.setX(newPos);


    }

    public void initialize(){

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                update(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekBar.setProgress(1);
    }
}
