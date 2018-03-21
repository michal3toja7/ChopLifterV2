package pl.michal.choplifterv2.gui;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import pl.michal.choplifterv2.ChopLifterPanel;

/**
 * Created by micha on 14.03.2018.
 */

public class MainThread extends Thread {

    public static final int MAX_FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private ChopLifterPanel chopLifterPanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder holder, ChopLifterPanel panel)
    {
        super();
        this.surfaceHolder = holder;
        this.chopLifterPanel = panel;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    @Override
    public void run()
    {
        long startTime;
        long timeMs;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running)
        {
            startTime = System.currentTimeMillis();
            canvas = null;

            try
            {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.chopLifterPanel.update();
                    this.chopLifterPanel.draw(canvas);
                }
            } catch (Exception e) {e.printStackTrace();}
            finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {e.printStackTrace();}
                }
            }
            timeMs = System.currentTimeMillis() - startTime;
            waitTime = targetTime - timeMs;
            try {
                if(waitTime > 0)
                    this.sleep(waitTime);
            } catch (Exception e) {e.printStackTrace();}

            totalTime += System.currentTimeMillis() - startTime;
            frameCount++;
            if(frameCount == MAX_FPS) {
                averageFPS = 1000/(totalTime/frameCount);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }
}


