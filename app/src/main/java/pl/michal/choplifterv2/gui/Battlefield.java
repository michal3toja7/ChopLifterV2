package pl.michal.choplifterv2.gui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.graphics.drawable.VectorDrawableCompat;
import pl.michal.choplifterv2.c64.C64Theme;
import pl.michal.choplifterv2.level.DestroyedException;
import pl.michal.choplifterv2.level.InterfaceLevel;
import pl.michal.choplifterv2.level.Level1;
import pl.michal.choplifterv2.sprite.Helicopter;

import static pl.michal.choplifterv2.ChopLifterActivity.getContext;


/**
 * Created by micha on 14.03.2018.
 */

public class Battlefield implements GameObject {
    Stars stars;
    private InterfaceLevel level= new Level1();
    private static int scrollx = 0 ;
    int i=0;
    private Typeface arcadeClassic = Typeface.createFromAsset(getContext().getAssets(), "fonts/arcadeclassic.ttf");




    public Battlefield(){


    }

    public final void setLevel(InterfaceLevel pLevel) {
        level = pLevel ;
    }

    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        canvas.drawColor(C64Theme.BLACK);
        int width = C64Theme.SCREEN_WIDTH;
        int height= C64Theme.SCREEN_HEIGHT;

        paint.setColor(C64Theme.GRAY);
        paint.setTextSize(50);
        paint.setTypeface(arcadeClassic);
        canvas.drawRect(0,2*(height/3),width,height,paint);

        if (level != null) {
           // level.getHelicopter().refreshAnimation();
            level.refreshAnimation();
            level.draw(canvas) ;
            level.heartBeat();
        }

       // canvas.drawText("Testowy komunikat", 700, 300, paint);


        if (stars==null)
            stars = new Stars(20);
        stars.draw(canvas);
    }

    @Override
    public void update() {
    }





    public void move(double angle, double power) throws DestroyedException {
    level.getHelicopter().move(angle, power);
    }
    public void onTap(char button){

        switch (button) {
            case 'a':
                level.getHelicopter().shoot();
                break;
            case 'b':
                level.getHelicopter().toggleDirection();
                break;
        }
    }


}