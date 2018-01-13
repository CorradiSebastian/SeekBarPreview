package com.sebastiancorradi.seekbar;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.sebastiancorradi.seekbar.components.SeekBarPreview;

public class MainActivity extends AppCompatActivity {

    private SeekBarPreview seekBarPreviewSDCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager am = this.getAssets();

        seekBarPreviewSDCard = findViewById(R.id.seekBarPreviewSDCard);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.bunny;
        seekBarPreviewSDCard.setUri(Uri.parse(path));
        //seekBarPreviewSDCard.setUrl("http://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");

        seekBarPreviewSDCard.initialize();
    }

}
