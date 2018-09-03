package pl.michal.choplifterv2.sprite;

import android.graphics.Canvas;

public interface InterfaceSprite {

        /* Paint */
        /** Paint method */
        void draw(Canvas canvas);
        /* Getter / Setter */
        /** Getter */
        int getX() ;
        /** Setter */
        void setX(int x) ;
        /** Getter */
        int getY() ;
        /** Setter */
        void setY(int y) ;
        /** Getter */
     //   BufferedImage getIcon() ;
        /** Setter */
     //   void setIcon(BufferedImage icon) ;
    }

