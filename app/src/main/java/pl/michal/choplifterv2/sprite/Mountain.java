package pl.michal.choplifterv2.sprite;

import android.graphics.Canvas;
import android.support.graphics.drawable.VectorDrawableCompat;

import static pl.michal.choplifterv2.ChopLifterActivity.getContext;

/**
 * Created by micha on 20.03.2018.
 */

    public final class Mountain extends AbstractSprite {
    VectorDrawableCompat mMyVectorDrawable;

        public Mountain(int x, int y) {
            setX(x) ;
            setY(y) ;
            //setIcon(convertAsIs(MOUNTAIN)) ;
        }

    @Override
    public void draw(Canvas canvas) {

    }
}

