package pl.michal.choplifterv2.level;

import android.graphics.Canvas;

import java.util.List;

import pl.michal.choplifterv2.sprite.Helicopter;
import pl.michal.choplifterv2.sprite.InterfaceAnimatedSprite;
import pl.michal.choplifterv2.sprite.InterfaceSprite;
import pl.michal.choplifterv2.sprite.Station;

import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;

public interface InterfaceLevel {
    /*Constants */
    /** Max widths of a level */
    public final static int MAXWIDTH = SCREEN_WIDTH*4 ;


    /* Maps */
    /** Whole level map i*/
    List getMap() ;
    /** Set level map */
    void setMap(List map) ;
    /** Sprite at level map i */
    InterfaceSprite getMap(int i) ;
    /** Whole active level map */
    List getActiveMap() ;
    /** Sprite at active level map i */
    InterfaceAnimatedSprite getActiveMap(int i) ;
    /** Add an element to game */
    void add(InterfaceSprite sprite) ;
    /** Remove an element from game */
    void remove(InterfaceSprite sprite) ;

    /* Paint */
    /** Paint method, dependant from view */
//    void paint(int x, Graphics g) ;

    /* Main action */
    /** Level action / mainloop */
    int heartBeat() ;

    /* Explosion management */
    /** Explosions Fire fire fire */
    void manageCollision(int x, int y) throws DestroyedException ;

    /* Coordinates */
    /** Level start */
    int getStartX() ;
    /** Level start */
    int getStartY() ;
    /** Level landing */
    int getLandingCoordsX() ;
    /** Level landing */
    int getLandingCoordsY() ;

    /* Status fields */
    /** Game status */
    int getKilled() ;
    /** Game status */
    int getPassengers() ;
    /** Game status */
    int getSaved() ;
    /** Game status */
    void incKilled() ;
    /** Game status */
    void incPassengers() ;
    /** Game status */
    void decPassengers() ;
    /** Game status */
    void incSaved() ;
    /** Game status */
    boolean isStarted() ;
    /** Game status */
    void setStarted(boolean started) ;

    /* Main actresses */
    /** Helicopter */
    Helicopter getHelicopter() ;
    boolean isHelicopterNear(int x) ;
    /** Station */
    Station getStation() ;

    void draw(Canvas canvas);

    void refreshAnimation();
}
