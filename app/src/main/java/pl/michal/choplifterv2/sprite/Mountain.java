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

    public void draw(Canvas canvas) {



        String vectorImageName = this.getVectorImageName();
        int resID = getContext().getResources().getIdentifier(vectorImageName, "drawable", getContext().getPackageName());
        mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), resID, null);


        mMyVectorDrawable.setBounds(this.getX(), this.getY(), this.getX() + mMyVectorDrawable.getMinimumWidth() * 6, this.getY() + mMyVectorDrawable.getMinimumHeight() * 6);

        mMyVectorDrawable.draw(canvas);
    }
    }

