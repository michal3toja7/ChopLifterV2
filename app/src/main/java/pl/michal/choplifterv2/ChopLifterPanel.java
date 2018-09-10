package pl.michal.choplifterv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import pl.michal.choplifterv2.gui.Battlefield;
import pl.michal.choplifterv2.gui.MainThread;
import pl.michal.choplifterv2.level.InterfaceLevel;
import pl.michal.choplifterv2.level.Level1;


/**
 * Created by micha on 14.03.2018.
 */

public class ChopLifterPanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Paint paint;
    static public Battlefield battlefield;
    static private InterfaceLevel level ;


    public ChopLifterPanel(Context context){
        super(context);

        getHolder().addCallback(this);

      //  battlefield = new Battlefield();



        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    public ChopLifterPanel(Context context, AttributeSet attrs){
        super(context, attrs);

        getHolder().addCallback(this);

     //   battlefield = new Battlefield();



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
        battlefield.onTap('x');
        return super.onTouchEvent(event);
    }

    public void update()
    {
        if(battlefield==null){
            startNewGame();
        }
        battlefield.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
       if(battlefield!=null){
           battlefield.draw(canvas);
       }

    }

    static public void startNewGame(){
        battlefield = null;
        battlefield = new Battlefield();
        battlefield.setNewGame(true);
        level = null ;
        level = new Level1() ;
        battlefield.setLevel(level) ;

    }

}