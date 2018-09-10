package pl.michal.choplifterv2.gui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.VectorDrawableCompat;

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
    private Typeface arcadeClassic = Typeface.createFromAsset(getContext().getAssets(), "fonts/arcadeclassic.ttf");
    private int sideMargin = SCREEN_WIDTH / 104;
    private int topMargin = SCREEN_HEIGHT / 68;
    private int statusBarHeight = 15* SPRITE_SCALE;
    private int textAndLabelHeight = 13* SPRITE_SCALE - 2*topMargin;






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
        int width = SCREEN_WIDTH;
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


        Paint paintStroke= new Paint();
        paintStroke.setColor(C64Theme.YELLOW) ;
        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setStrokeWidth(topMargin/2);
        canvas.drawRoundRect( sideMargin, topMargin, SCREEN_WIDTH - sideMargin, topMargin + statusBarHeight, 3*sideMargin,3*sideMargin, paintStroke);
        paint.setColor(C64Theme.RED);
        canvas.drawRoundRect(sideMargin + sideMargin,topMargin+topMargin,(SCREEN_WIDTH/4) - 3*sideMargin, statusBarHeight, 12, 12,paint);
        canvas.drawRoundRect((SCREEN_WIDTH/4) + 3*sideMargin,topMargin+topMargin,(SCREEN_WIDTH/2) - 3*sideMargin, statusBarHeight, 12, 12,paint);
        canvas.drawRoundRect((SCREEN_WIDTH/2) + 3*sideMargin,topMargin+topMargin,(3*(SCREEN_WIDTH/4)) - 3*sideMargin, statusBarHeight, 12, 12,paint);
        canvas.drawRoundRect((3*(SCREEN_WIDTH/4)) + 3*sideMargin,topMargin+topMargin,SCREEN_WIDTH - sideMargin - sideMargin, statusBarHeight, 12, 12,paint);
        getLabel("pik").draw(canvas);
        getLabel("karo").draw(canvas);
        getLabel("heart").draw(canvas);




        //     g.drawRoundRect(1, 12, 317, 19, 23, 20) ;
   //     g.drawRoundRect(2, 13, 315, 17, 15, 20) ;

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

     private int getVectorWidth(VectorDrawableCompat mMyVectorDrawable){

         double scale =  mMyVectorDrawable.getMinimumHeight()/ (textAndLabelHeight) ;

         return (int)(mMyVectorDrawable.getMinimumWidth() / scale);
     }
      private VectorDrawableCompat getLabel(String label){
        int barTextTop = 2 * topMargin + SPRITE_SCALE;

           VectorDrawableCompat mMyVectorDrawable = null;
        switch (label) {
            case ("pik"):
                mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.pik, null);
                mMyVectorDrawable.setBounds((SCREEN_WIDTH / 4) - 3 * sideMargin, barTextTop, (SCREEN_WIDTH / 4) - 3 * sideMargin + getVectorWidth(mMyVectorDrawable), barTextTop+ textAndLabelHeight);
                break;
            case ("karo"):
                mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.karo, null);
                mMyVectorDrawable.setBounds((SCREEN_WIDTH / 2) - 3 * sideMargin, barTextTop, (SCREEN_WIDTH / 2) - 3 * sideMargin + getVectorWidth(mMyVectorDrawable),  barTextTop+ textAndLabelHeight);
                break;
            case ("heart"):
                mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.heart, null);
                mMyVectorDrawable.setBounds((3 * (SCREEN_WIDTH / 4)) - (3 * sideMargin), barTextTop, ((3 * (SCREEN_WIDTH / 4)) - 3 * sideMargin) + getVectorWidth(mMyVectorDrawable),  barTextTop+ textAndLabelHeight);
                break;
        }
        return mMyVectorDrawable;
        }

}