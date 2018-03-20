package pl.michal.choplifterv2;


import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ChopLifterActivity extends AppCompatActivity {
    private static Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Tryb pałnoekranowy
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Usuwanie nagłówka
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new ChopLifterPanel(this));


        //usuwanie przycisków nawigacyjnych
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

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


}
