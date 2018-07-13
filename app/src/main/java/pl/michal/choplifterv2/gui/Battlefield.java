package pl.michal.choplifterv2.gui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.VectorDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.util.logging.XMLFormatter;

import pl.michal.choplifterv2.R;
import pl.michal.choplifterv2.c64.C64Theme;
import pl.michal.choplifterv2.sprite.Helicopter;

import static pl.michal.choplifterv2.ChopLifterActivity.getContext;


/**
 * Created by micha on 14.03.2018.
 */

public class Battlefield implements GameObject {
    Stars stars;
    VectorDrawableCompat mMyVectorDrawable;
    int i=0;


    public Battlefield(){

    }


    @Override
    public void draw(Canvas canvas){



        if (i==0) {
            mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.helicopter_center_right1, null);
        i++;
        }
        else if (i==1) {
            mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.helicopter_center_right2, null);
            i++;
        }
        else if (i==2) {
            mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.helicopter_center_right3, null);
            i=0;
        }
    //    mMyVectorDrawable.getI
     //   System.out.println("minimum width: " + mMyVectorDrawable.getMinimumWidth());
     //   System.out.println("minimum height: " + mMyVectorDrawable.getMinimumHeight());

        mMyVectorDrawable.setBounds(400, 400, 400+mMyVectorDrawable.getMinimumWidth()*12, 400+ mMyVectorDrawable.getMinimumHeight()*12);
        Paint paint = new Paint();
        canvas.drawColor(C64Theme.BLACK);
        int width = C64Theme.SCREEN_WIDTH;
        int height= C64Theme.SCREEN_HEIGHT;

        paint.setColor(C64Theme.GRAY);
        canvas.drawRect(0,height/2,width,height,paint);
  //      draw(getMirroredImage(mMyVectorDrawable));
      //  getMirroredImage(mMyVectorDrawable);
        canvas.drawBitmap(getMirroredImage(mMyVectorDrawable),mMyVectorDrawable.getMinimumWidth()*12,
                mMyVectorDrawable.getMinimumHeight()*12,null);


        if (stars==null)
            stars = new Stars(20);
        stars.draw(canvas);
    }

    @Override
    public void update() {
    }



    public Bitmap getMirroredImage( VectorDrawableCompat mMyVectorDrawable){
        Bitmap split = Bitmap.createBitmap(mMyVectorDrawable.getMinimumWidth()*12,
                mMyVectorDrawable.getMinimumHeight()*12,
                Bitmap.Config.ARGB_8888);
        Matrix matrix = new Matrix();
        Canvas canvas2 = new Canvas(split);
        mMyVectorDrawable.setBounds(400, 400, 400+mMyVectorDrawable.getMinimumWidth()*12, 400+ mMyVectorDrawable.getMinimumHeight()*12);
        mMyVectorDrawable.draw(canvas2);

      //  matrix.preScale(-1.0f, 1.0f, canvas2.getWidth() / 2, canvas2.getHeight() / 2);
      //  canvas2.setMatrix(matrix);
        return split;
    }



}
