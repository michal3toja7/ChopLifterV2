package pl.michal.choplifterv2.gui;

import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.io.IOException;

import pl.michal.choplifterv2.ChopLifterActivity;
import pl.michal.choplifterv2.R;

public class Sounds implements GameObject {
   static MediaPlayer helicopter= MediaPlayer.create(ChopLifterActivity.getContext(),R.raw.helisoptersound);


    @Override
    public void draw(Canvas canvas) throws IOException {

    }

    @Override
    public void update() {

    }

   static public void playExplosion(){
       MediaPlayer explosion= MediaPlayer.create(ChopLifterActivity.getContext(),R.raw.armsound);
       explosion.start();

    }
    static public void playHelicopter(){
        //helicopter.setVolume(1,1);
        helicopter.start();
        helicopter.setLooping(true);
    }
    static public void stopHeliopterMusic(){
        helicopter.stop();
    }




}
