package pl.michal.choplifterv2.gui;

import android.graphics.Canvas;


import java.io.IOException;

/**
 * Created by micha on 14.03.2018.
 */

public interface GameObject {
    public void draw(Canvas canvas) throws IOException;
        public void update();


}
