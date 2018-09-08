package pl.michal.choplifterv2;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.erz.joysticklibrary.JoyStick;

import java.util.Locale;

import pl.michal.choplifterv2.c64.ArcadeFont;
import pl.michal.choplifterv2.gui.Controller;
import pl.michal.choplifterv2.level.DestroyedException;

public class ChopLifterActivity extends AppCompatActivity implements JoyStick.JoyStickListener{
    private static Context mContext;
    private ChopLifterPanel chopLifterPanel;
    private Controller controller;
    private TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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

        JoyStick joy2 = (JoyStick) findViewById(R.id.joy2);
        joy2.setListener(this);
        joy2.enableStayPut(true);
        //joy2.setPadBackground(R.drawable.pad);
        //joy2.setButtonDrawable(R.drawable.button);


        //usuwanie przycisków nawigacyjnych
           this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        //mAppNameTextView = (TextView) findViewById(R.id.);
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/arcadeclassic.ttf");
        //mAppNameTextView.setTypeface(ostrichFont);

        mContext = getApplicationContext();


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
                } catch (DestroyedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.joy2:
           //     gameView.rotate(angle);
                break;
        }
    }

    @Override
    public void onTap(JoyStick joyStick) {
        switch (joyStick.getId()) {
            case R.id.joy1:
                break;
            case R.id.joy2:
                chopLifterPanel.battlefield.onTap();
                break;
        }
    }

    @Override
    public void onDoubleTap() {

    }

}