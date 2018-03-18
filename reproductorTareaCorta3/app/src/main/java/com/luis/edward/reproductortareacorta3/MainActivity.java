package com.luis.edward.reproductortareacorta3;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickPlay(View view)
    {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.clubcant);
        mediaPlayer.start();

    }

    public void clickPause(View view)
    {
        mediaPlayer.pause();

    }
}
