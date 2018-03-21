package com.luis.edward.reproductortareacorta3;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Integer canciones[]=new Integer[9];
    String nombreCanciones[]=new String[9];
    Integer LetraCanciones[]=new Integer[9];

    ListView listViewLuis;

    AdapterView.OnItemClickListener mMessageClickedHandler; //para ejecutar media player con respecto listView

    Integer numeroActualCancion=0;

    Boolean siguiente=false;
    Boolean anterior=false;

    SeekBar seekBarTiempo;

    private Handler mHandler;//Esto tendrá el hillo que se encargar de cambiar barra con avance de cancion

    Boolean barraEsPresionada=false;

    Integer tiempoTotalCancion=0;

    TextView textViewLetra;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Lista de canciones en la carpeta raw
        canciones[0]=R.raw.que_es_la_vida;
        canciones[1]=R.raw.club_cant_handle_me;
        canciones[2]=R.raw.break_your_heart;
        canciones[3]=R.raw.just_the_way_you_are;
        canciones[4]=R.raw.lose_yourself;
        canciones[5]=R.raw.numb;
        canciones[6]=R.raw.twenty_one_guns;
        canciones[7]=R.raw.when_september_ends;
        canciones[8]=R.raw.you_make_me;



        //Lista de canciones String
        nombreCanciones[0]="Qué es la vida";
        nombreCanciones[1]="Club can't handle me";
        nombreCanciones[2]="Break you heart";
        nombreCanciones[3]="Just_the_way_you_are";
        nombreCanciones[4]="Lose_yourself";
        nombreCanciones[5]="Numb";
        nombreCanciones[6]="Twenty_one_guns";
        nombreCanciones[7]="When_september_ends";
        nombreCanciones[8]="You_make_me";


        textViewLetra=findViewById(R.id.textViewLetra);
        LetraCanciones[0]=R.raw.que_es_la_vida1;
        LetraCanciones[1]=R.raw.club_cant_handle_me1;
        LetraCanciones[2]=R.raw.break_your_heart1;
        LetraCanciones[3]=R.raw.just_the_way_you_are1;
        LetraCanciones[4]=R.raw.lose_yourself1;
        LetraCanciones[5]=R.raw.numb1;
        LetraCanciones[6]=R.raw.twenty_one_guns1;
        LetraCanciones[7]=R.raw.when_september_ends1;
        LetraCanciones[8]=R.raw.you_make_me1;
        textViewLetra.setText("Presione cualquier canción para ver la letra");

        //Se crear objeto media player
        mediaPlayer = MediaPlayer.create(getApplicationContext(),canciones[0]);

        //
        seekBarTiempo= findViewById(R.id.seekBarTiempo);
        seekBarTiempo.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        //seekBarTiempo.getThumb().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);




        //El tamaño del array que le va a entrar por parametros tiene que tener datos segun el tamaño de arreglo declarado
        ArrayAdapter<String> lista=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,nombreCanciones);

        listViewLuis = (ListView) findViewById(R.id.listViewLuis);
        listViewLuis.setAdapter(lista);


        mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click

                reproducir(position);

            }
        };
        listViewLuis.setOnItemClickListener(mMessageClickedHandler);


        //Para
      /*  mHandler = new Handler();
        //Make sure you update Seekbar on UI thread
        MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(mediaPlayer != null && seekBarTiempo.onfsd){
                    int posicionActualCancion = mediaPlayer.getCurrentPosition() / 1000;
                    seekBarTiempo.setProgress(posicionActualCancion);
                }
                mHandler.postDelayed(this, 0);
            }
        });
        */

    }



    public void reproducir(int position)
    {
        //Log.d("A--NUMERO-Cancion",String.valueOf(numeroActualCancion));
        Log.d("---NUMER-LENGTH",String.valueOf(canciones.length));
        if (mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();

        }

        if (numeroActualCancion==canciones.length-1 && siguiente==true )
        {
            position=0;
            siguiente=false;

        }

        if (position<0 && anterior==true)
        {
            position=canciones.length-1;
            anterior=false;
            Log.d("ATENCION!","ENTRO AL ANTERIOR CON -1");
        }

        Log.d("CORRIENDO--NUMERO",String.valueOf(position));
        mediaPlayer = MediaPlayer.create(getApplicationContext(),canciones[position]);
        mediaPlayer.start();


        //Mostrar letra cancion
        try {
            Resources res = getResources();
            InputStream letraCancion = res.openRawResource(LetraCanciones[position]);

            byte[] b = new byte[letraCancion.available()];
            letraCancion.read(b);
            textViewLetra.setText(new String(b));
        } catch (Exception e) {
            // e.printStackTrace();
            textViewLetra.setText("Error: can't show help.");
        }



        numeroActualCancion=position;




        //Seekbar
        tiempoTotalCancion=mediaPlayer.getDuration()/1000; //Paso a segundos
        // Configuración de color


        seekBarTiempo.setMax(tiempoTotalCancion);
        seekBarTiempo.setProgress(0);

        seekBarTiempo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {

                Log.d("HEY-Progreso actual es:",String.valueOf(mediaPlayer.getCurrentPosition()/1000));
                Log.d("tiempoTotal cancion es:",String.valueOf(tiempoTotalCancion));
                Log.d("Barra Progreso es:",String.valueOf(seekBar.getProgress()));
                Log.d("Barra TOTAL es:",String.valueOf(seekBar.getMax()));
                //seekBar.ise
                //if (mediaPlayer.getDuration()/1000==tiempoTotalCancion-1)  Agregar pasar a siguiente cancion cuando termine actual
                //{

                        //reproducir(numeroActualCancion+1);
                  //  }
                //else
                   // {
                        cambiarTiempoCancion(i);
                    //}

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
/*
        seekBarTiempo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
*/



        Log.d("Des--NUMERO-Cancion",String.valueOf(numeroActualCancion));



    }
    public void cambiarTiempoCancion(int valorBarra)
    {
        mediaPlayer.seekTo(valorBarra*1000);
        barraEsPresionada=false;

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

        siguiente=true;
        reproducir(numeroActualCancion+1);

    }

    public void clickAnt(View view)
    {
        anterior=true;
        reproducir(numeroActualCancion-1);

    }




}
