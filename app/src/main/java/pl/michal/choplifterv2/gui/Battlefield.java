package pl.michal.choplifterv2.gui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.VectorDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.util.logging.XMLFormatter;

import pl.michal.choplifterv2.R;
import pl.michal.choplifterv2.c64.C64Theme;
import pl.michal.choplifterv2.sprite.Helicopter;

import static pl.michal.choplifterv2.ChopLifterActivity.getContext;


/**
 * Created by micha on 14.03.2018.
 */

public class Battlefield implements GameObject {
    Stars stars;
    VectorDrawableCompat mMyVectorDrawable;
     Helicopter helicopter;
    int i=0;


    public Battlefield(){

    }


    @Override
    public void draw(Canvas canvas){

        if(helicopter==null){
        helicopter = new Helicopter();}
        else{
            helicopter.refreshAnimation();
        }


        String vectorImageName =helicopter.getVectorImageName();
        int resID = getContext().getResources().getIdentifier(vectorImageName, "drawable", getContext().getPackageName());
        mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(),resID, null);



        mMyVectorDrawable.setBounds(helicopter.getX(), helicopter.getY(), helicopter.getX()+mMyVectorDrawable.getMinimumWidth()*6, helicopter.getY()+ mMyVectorDrawable.getMinimumHeight()*6);
        Paint paint = new Paint();
        canvas.drawColor(C64Theme.BLACK);
        int width = C64Theme.SCREEN_WIDTH;
        int height= C64Theme.SCREEN_HEIGHT;

        paint.setColor(C64Theme.GRAY);
        canvas.drawRect(0,height/2,width,height,paint);

        //  getMirroredImage(mMyVectorDrawable);
        mMyVectorDrawable.draw(canvas);

        if (stars==null)
            stars = new Stars(20);
        stars.draw(canvas);
    }

    @Override
    public void update() {
    }





    public Helicopter getHelicopter() {
        return helicopter;
    }

    public void move(double angle, double power) {
    helicopter.move(angle, power);
    }
    public void onTap(){
        helicopter.toggleDirection();
    }


}