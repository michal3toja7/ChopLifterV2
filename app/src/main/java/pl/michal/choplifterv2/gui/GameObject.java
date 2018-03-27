package pl.michal.choplifterv2.gui;

import android.graphics.Canvas;

import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

/**
 * Created by micha on 14.03.2018.
 */

public interface GameObject {
    public void draw(Canvas canvas) throws SVGParseException, IOException;
        public void update();


}
