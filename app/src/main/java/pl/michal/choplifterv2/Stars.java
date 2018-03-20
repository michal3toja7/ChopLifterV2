package pl.michal.choplifterv2;

import android.graphics.Canvas;
import android.graphics.Paint;

import pl.michal.choplifterv2.c64.C64Theme;

/**
 * Created by micha on 18.03.2018.
 */



public final class Stars {
    /** All stars */
    private static int[][] stars ;

    /**
     * Constructor
     * @param count stars
     */
    public Stars(int count) {
        stars = new int[count][2] ;
        for (int i=0; i < stars.length;++i) {
            stars[i][0] = (int) (Math.random() * C64Theme.SCREEN_WIDTH) ;
            stars[i][1] =  (int) (Math.random() * (C64Theme.SCREEN_HEIGHT/2)) ;
        }
    }

    // ------------------------------------------------------------------------

    /**
     * Paint
     */
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        for (int y = 0; y < stars.length; ++y) {
            paint.setColor((Math.random()) > 0.9 ? C64Theme.WHITE : C64Theme.RED) ;
            canvas.drawRect(stars[y][0], stars[y][1], stars[y][0]+6, stars[y][1]+6,paint) ;

        }
    }
}
