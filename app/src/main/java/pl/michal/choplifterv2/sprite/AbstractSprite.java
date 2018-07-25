package pl.michal.choplifterv2.sprite;

import android.graphics.Canvas;

import pl.michal.choplifterv2.c64.C64Theme;

/**
 * Created by micha on 02.04.2018.
 */

public class AbstractSprite implements InterfaceSprite{
    private int x ;
    private int y ;

    public final int getX() {
        return x ;
    }

    public final int getY() {
        return y ;
    }

    public final void setX(int x) {
        this.x = x ;
    }

    public final void setY(int y) {
        this.y = y ;
    }


    public void draw (int v, Canvas canvas) {
        int vx = -v + getX();
     //   BufferedImage lIcon = getIcon() ;

        // Only paint if icon and if in view
        if (vx + 40 < 0) return ;
        if (vx > C64Theme.SCREEN_WIDTH) return ;
       // if (lIcon == null) return ;

        //canvas.drawImage(lIcon, vx, getY(), null) ;
    }

}
