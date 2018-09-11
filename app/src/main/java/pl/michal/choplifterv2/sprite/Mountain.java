package pl.michal.choplifterv2.sprite;

import android.graphics.Canvas;
import android.support.graphics.drawable.VectorDrawableCompat;

import pl.michal.choplifterv2.level.DestroyedException;

import static pl.michal.choplifterv2.ChopLifterActivity.getContext;

/**
 * Created by micha on 20.03.2018.
 */

    public final class Mountain extends AbstractAnimatedSprite {

        public Mountain(int x, int y) {
            setX(x) ;
            setY(y) ;
            setAnimation(SpriteDirection.MOUNTAIN, 0);
        }


    @Override
    public int action() throws DestroyedException {
       return -1;
    }

    @Override
    public void loadAnimation() {

    }
}

