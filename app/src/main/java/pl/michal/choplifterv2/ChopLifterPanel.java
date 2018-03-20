package pl.michal.choplifterv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by micha on 14.03.2018.
 */

public class ChopLifterPanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Paint paint;
    private Battlefield battlefield;


    public ChopLifterPanel(Context context){
        super(context);

        getHolder().addCallback(this);

        battlefield = new Battlefield();


        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry)
        {
            try{thread.setRunning(false);
                thread.join();

            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }

    public void update()
    {
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        battlefield.draw(canvas);

        }

}

