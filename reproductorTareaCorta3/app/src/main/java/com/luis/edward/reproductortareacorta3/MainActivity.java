package com.luis.edward.reproductortareacorta3;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Integer canciones[]=new Integer[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        canciones[0]=R.raw.que_es_la_vida;
        canciones[1]=R.raw.club_cant_handle_me;


        mediaPlayer = MediaPlayer.create(getApplicationContext(),canciones[0]);

        ArrayAdapter<String>

    }

    public void clickPlay(View view)
    {


        mediaPlayer.start();


        //mediaPlayer.pa

    }

    public void clickPause(View view)
    {
        mediaPlayer.pause();

    }

    public void clickSig(View view)
    {
        mediaPlayer = MediaPlayer.create(getApplicationContext(),canciones[1]);
        mediaPlayer.start();

    }
}
