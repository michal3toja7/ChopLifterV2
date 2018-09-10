package pl.michal.choplifterv2;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.erz.joysticklibrary.JoyStick;

import pl.michal.choplifterv2.c64.C64Color;
import pl.michal.choplifterv2.c64.C64Theme;
import pl.michal.choplifterv2.level.DestroyedException;

public class ChopLifterActivity extends AppCompatActivity implements JoyStick.JoyStickListener{
    private static Context mContext;
    private ChopLifterPanel chopLifterPanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();


        //Tryb pałnoekranowy
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Usuwanie nagłówka
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chop_lifter);


        chopLifterPanel = (ChopLifterPanel) findViewById(R.id.game);

        JoyStick joy1 = (JoyStick) findViewById(R.id.joy1);
        joy1.setListener(this);
        joy1.setPadColor(Color.parseColor("#55ffffff"));
        joy1.setButtonColor(Color.parseColor("#55ff0000"));

        JoyStick buttonA = (JoyStick) findViewById(R.id.buttonA);
        buttonA.setListener(this);
  //      buttonA.enableStayPut(true);
        buttonA.setPadColor(Color.parseColor("#99ff0000"));
        buttonA.setButtonDrawable(R.drawable.buttona);

        JoyStick buttonB = (JoyStick) findViewById(R.id.buttonB);
        buttonB.setListener(this);
        //      buttonA.enableStayPut(true);
        buttonB.setPadColor(Color.parseColor("#993399ff"));
        buttonB.setButtonDrawable(R.drawable.buttonb);

        /*  Na razie brak pomysłu - trochę nie pasuje do interfejsu.
        JoyStick pauseButton = (JoyStick) findViewById(R.id.pauseButton);
        pauseButton.setListener(this);
        //      buttonA.enableStayPut(true);
        pauseButton.setPadBackground(R.drawable.pausebutton);
        pauseButton.setButtonColor(Color.parseColor("#00ffffff"));
        */



        //usuwanie przycisków nawigacyjnych
           this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);




    }
    public static Context getContext() {
        return mContext;
    }
    public static int getWidth(Context mContext){
        int width=0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if(Build.VERSION.SDK_INT>17){
            Point size = new Point();
            display.getRealSize(size);
            width = size.x;
        }
        else if(Build.VERSION.SDK_INT>12 && Build.VERSION.SDK_INT<17){
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        }
        else{
            width = display.getWidth();  // Deprecated
        }
        return width;
    }
    public static int getHeight(Context mContext){
        int height=0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if(Build.VERSION.SDK_INT>17){
            Point size = new Point();
            display.getRealSize(size);
            height = size.y;
        }
        else if(Build.VERSION.SDK_INT>12 && Build.VERSION.SDK_INT<17){
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        }
        else{
            height = display.getHeight();  // Deprecated
        }
        return height;
    }


    @Override
    public void onMove(JoyStick joyStick, double angle, double power, int direction) {
        switch (joyStick.getId()) {
            case R.id.joy1:
                try {
                    chopLifterPanel.battlefield.move(angle, power);
                    chopLifterPanel.battlefield.onTap('x');
                } catch (DestroyedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.buttonA:
           //     gameView.rotate(angle);
                break;
        }
    }

    @Override
    public void onTap(JoyStick joyStick) {
        switch (joyStick.getId()) {
            case R.id.joy1:
                chopLifterPanel.battlefield.onTap('x');
                break;
            case R.id.buttonA:
                chopLifterPanel.battlefield.onTap('a');
                break;
            case R.id.buttonB:
                chopLifterPanel.battlefield.onTap('b');
                break;

        }

    }

    @Override
    public void onDoubleTap() {

    }

}