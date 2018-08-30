package pl.michal.choplifterv2.sprite;

import android.graphics.Canvas;

import pl.michal.choplifterv2.c64.C64Theme;

/**
 * Created by micha on 02.04.2018.
 */

public abstract class AbstractSprite implements InterfaceSprite{
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


}
