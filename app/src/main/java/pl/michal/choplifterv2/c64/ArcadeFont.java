package pl.michal.choplifterv2.c64;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.AttributedCharacterIterator;

public class ArcadeFont extends AppCompatTextView {
    public ArcadeFont(Context context, AttributeSet attrs){
        super(context, attrs);
        String fontPath = "fonts/arcadeclassic.ttf";
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), fontPath));

    }
}
