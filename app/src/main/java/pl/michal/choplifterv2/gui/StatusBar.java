package pl.michal.choplifterv2.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.VectorDrawableCompat;

import java.io.IOException;

import pl.michal.choplifterv2.R;
import pl.michal.choplifterv2.c64.C64Theme;
import pl.michal.choplifterv2.level.InterfaceLevel;

import static pl.michal.choplifterv2.ChopLifterActivity.getContext;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_HEIGHT;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;
import static pl.michal.choplifterv2.c64.C64Theme.SPRITE_SCALE;
import static pl.michal.choplifterv2.c64.C64Theme.WHITE;

public class StatusBar implements GameObject {
    private Typeface arcadeClassic = Typeface.createFromAsset(getContext().getAssets(), "fonts/arcadeclassic.ttf");
    private int sideMargin = SCREEN_WIDTH / 104;
    private int topMargin = SCREEN_HEIGHT / 68;
    private int statusBarHeight = 15* SPRITE_SCALE;
    private int textAndLabelHeight = 13* SPRITE_SCALE - 2*topMargin;
    int barTextTop = 2 * topMargin + SPRITE_SCALE;
    private InterfaceLevel level;
    int pauseScreenTextSize = SCREEN_HEIGHT/17;


    public StatusBar(InterfaceLevel level){
        this.level = level;
    }






    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void draw(Canvas canvas){//} throws IOException {
        Paint paint = new Paint();
        Paint textPaint = new Paint();
        textPaint.setTextSize(13*SPRITE_SCALE);
        textPaint.setTypeface(arcadeClassic);
        textPaint.setColor(WHITE);
        Paint pauseScreenPaint = new Paint();


        Paint paintStroke= new Paint();
        paintStroke.setColor(C64Theme.YELLOW) ;
        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setStrokeWidth(topMargin/2);
        canvas.drawRoundRect( sideMargin, topMargin, SCREEN_WIDTH - sideMargin, topMargin + statusBarHeight, 3*sideMargin,3*sideMargin, paintStroke);
        paint.setColor(C64Theme.RED);
        canvas.drawRoundRect(sideMargin + sideMargin,topMargin+topMargin,(SCREEN_WIDTH/4) - 4*sideMargin, statusBarHeight, 12, 12,paint);
        canvas.drawRoundRect((SCREEN_WIDTH/4) + 4*sideMargin,topMargin+topMargin,(SCREEN_WIDTH/2) - 4*sideMargin, statusBarHeight, 12, 12,paint);
        canvas.drawRoundRect((SCREEN_WIDTH/2) + 4*sideMargin,topMargin+topMargin,(3*(SCREEN_WIDTH/4)) - 4*sideMargin, statusBarHeight, 12, 12,paint);
        canvas.drawRoundRect((3*(SCREEN_WIDTH/4)) + 4*sideMargin,topMargin+topMargin,SCREEN_WIDTH - sideMargin - sideMargin, statusBarHeight, 12, 12,paint);
        getLabel("pik").draw(canvas);
        paint.setColor(WHITE);
        canvas.drawText((""+level.getKilled()),(SCREEN_WIDTH/4),barTextTop + textAndLabelHeight,textPaint);
        getLabel("karo").draw(canvas);
        canvas.drawText((""+level.getPassengers()),(SCREEN_WIDTH/2),barTextTop + textAndLabelHeight,textPaint);
        getLabel("heart").draw(canvas);
        canvas.drawText((""+level.getSaved()),(3*(SCREEN_WIDTH/4)),barTextTop + textAndLabelHeight,textPaint);
        pauseScreenPaint.setColor(Color.parseColor("#99000000"));
        textPaint.setTextSize(pauseScreenTextSize);
        if (level.isYouWin()) {
            canvas.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, pauseScreenPaint);
            canvas.drawText("Wygrales,   uratowales   wszystkich   ! ", SCREEN_WIDTH / 4, SCREEN_HEIGHT / 2, textPaint);
        }
        if (!level.isStarted() && level.isYouLose()) {
            canvas.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, pauseScreenPaint);
            canvas.drawText("game   over", SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2, textPaint);
        }
        else if (!level.isStarted() && Battlefield.isNewGame()) {
            canvas.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, pauseScreenPaint);
            canvas.drawText("Witaj   w   ChopLifterV2!", SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4, textPaint);
            canvas.drawText("Projekt   autorstwa:", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 4 + pauseScreenTextSize, textPaint);
            canvas.drawText("Michal   Bruzdowski", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 4 + 2 * pauseScreenTextSize, textPaint);
            canvas.drawText("Patryk   Dziarnecki", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 4 + 3 * pauseScreenTextSize, textPaint);
            canvas.drawText("(C)   2018", SCREEN_WIDTH / 2, SCREEN_HEIGHT / 4 + 4 * pauseScreenTextSize, textPaint);
        }

    }

    @Override
    public void update() {

    }


    private int getVectorWidth(VectorDrawableCompat mMyVectorDrawable){

        double scale =  mMyVectorDrawable.getMinimumHeight()/ (textAndLabelHeight) ;

        return (int)(mMyVectorDrawable.getMinimumWidth() / scale);
    }
    private VectorDrawableCompat getLabel(String label){


        VectorDrawableCompat mMyVectorDrawable = null;
        switch (label) {
            case ("pik"):
                mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.pik, null);
                mMyVectorDrawable.setBounds((SCREEN_WIDTH / 4) - 4*sideMargin, barTextTop, (SCREEN_WIDTH / 4) - 4*sideMargin + getVectorWidth(mMyVectorDrawable), barTextTop+ textAndLabelHeight);
                break;
            case ("karo"):
                mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.karo, null);
                mMyVectorDrawable.setBounds((SCREEN_WIDTH / 2) - 4*sideMargin, barTextTop, (SCREEN_WIDTH / 2) - 4*sideMargin + getVectorWidth(mMyVectorDrawable),  barTextTop+ textAndLabelHeight);
                break;
            case ("heart"):
                mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), R.drawable.heart, null);
                mMyVectorDrawable.setBounds((3 * (SCREEN_WIDTH / 4)) - (4*sideMargin), barTextTop, ((3 * (SCREEN_WIDTH / 4)) - 4*sideMargin) + getVectorWidth(mMyVectorDrawable),  barTextTop+ textAndLabelHeight);
                break;
        }
        return mMyVectorDrawable;
    }
}
