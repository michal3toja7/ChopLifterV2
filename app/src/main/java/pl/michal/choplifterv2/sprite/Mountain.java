package pl.michal.choplifterv2.sprite;

import android.graphics.Canvas;

/**
 * Created by micha on 20.03.2018.
 */

    public final class Mountain{ // extends ASprite {

        public final static String[] MOUNTAIN = new String[] {
                "            2211        11                ",
                "      11  22116666    116666662211        ",
                "  226666666666666666666666666666666666    ",
                "6666666666666666666666666666666666666666  ",
        } ;

        public Mountain(int x, int y) {
         //   setX(x) ;
          //  setY(y) ;
            //setIcon(convertAsIs(MOUNTAIN)) ;
        }

        public void draw (int v, Canvas canvas) {
           // super.draw((int) (v-v*0.2), canvas) ;
        }

    }

