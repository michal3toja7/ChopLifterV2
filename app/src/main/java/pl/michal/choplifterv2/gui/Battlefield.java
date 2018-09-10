package pl.michal.choplifterv2.gui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.VectorDrawableCompat;

import pl.michal.choplifterv2.ChopLifterPanel;
import pl.michal.choplifterv2.R;
import pl.michal.choplifterv2.c64.C64Theme;
import pl.michal.choplifterv2.level.DestroyedException;
import pl.michal.choplifterv2.level.InterfaceLevel;
import pl.michal.choplifterv2.level.Level1;

import static pl.michal.choplifterv2.ChopLifterActivity.getContext;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_HEIGHT;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;
import static pl.michal.choplifterv2.c64.C64Theme.SPRITE_SCALE;


/**
 * Created by micha on 14.03.2018.
 */

public class Battlefield implements GameObject {
    Stars stars;
    private InterfaceLevel level= new Level1();
    private static int scrollx = 0 ;
    private int i=0;
    StatusBar statusBar;








    public Battlefield(){


    }

    public final void setLevel(InterfaceLevel pLevel) {
        level = pLevel ;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        canvas.drawColor(C64Theme.BLACK);

        if (stars==null)
            stars = new Stars(20);
        stars.draw(canvas);

        int width = SCREEN_WIDTH;
        int height= SCREEN_HEIGHT;

        paint.setColor(C64Theme.GRAY);
        canvas.drawRect(0,2*(height/3),width,height,paint);

        if (level != null) {
            level.draw(canvas);
            if(level.isStarted()) {
                level.refreshAnimation();

                level.heartBeat();
            }
        }

        //if (level.isStarted()){
            if (statusBar == null)
                statusBar = new StatusBar(level);
            statusBar.draw(canvas);

        //}







        //     g.drawRoundRect(1, 12, 317, 19, 23, 20) ;
   //     g.drawRoundRect(2, 13, 315, 17, 15, 20) ;

       // canvas.drawText("Testowy komunikat", 700, 300, paint);



    }

    @Override
    public void update() {
        if (level!=null) {
            if (!level.getHelicopter().isAlive()) {
                level.setStarted(false);
                level.setYouLose(true);
            }
        }
    }





    public void move(double angle, double power) throws DestroyedException {
    level.getHelicopter().move(angle, power);
    }
    public void onTap(char button){

        switch (button) {
            case 'a':
                level.getHelicopter().shoot();
                if(!level.isStarted())
                    level.setStarted(true);
                break;
            case 'b':
                level.getHelicopter().toggleDirection();
                if(!level.isStarted())
                    level.setStarted(true);
                break;
            case 'x':

                if(!level.isStarted() && level.isYouLose()){
                    ChopLifterPanel.startNewGame();
                }
                else if(!level.isStarted())
                    level.setStarted(true);



                break;
        }
    }


}