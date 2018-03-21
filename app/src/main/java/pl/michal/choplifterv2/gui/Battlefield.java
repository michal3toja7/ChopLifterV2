package pl.michal.choplifterv2.gui;

import android.graphics.Canvas;
import android.graphics.Paint;

import pl.michal.choplifterv2.c64.C64Theme;


/**
 * Created by micha on 14.03.2018.
 */

public class Battlefield implements GameObject {
    Stars stars;


    public Battlefield(){

    }


    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        canvas.drawColor(C64Theme.BLACK);
        int width = C64Theme.SCREEN_WIDTH;
        int height= C64Theme.SCREEN_HEIGHT;

        paint.setColor(C64Theme.GRAY);
        canvas.drawRect(0,height/2,width,height,paint);

        if (stars==null)
            stars = new Stars(20);
        stars.draw(canvas);

    }

    @Override
    public void update() {
    }



}
