package pl.michal.choplifterv2.level;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import pl.michal.choplifterv2.c64.C64Theme;
import pl.michal.choplifterv2.sprite.Arm;
import pl.michal.choplifterv2.sprite.Fence;
import pl.michal.choplifterv2.sprite.Helicopter;
import pl.michal.choplifterv2.sprite.Human;
import pl.michal.choplifterv2.sprite.InterfaceAnimatedSprite;
import pl.michal.choplifterv2.sprite.InterfaceSprite;
import pl.michal.choplifterv2.sprite.Station;
import pl.michal.choplifterv2.sprite.Tank;

import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_HEIGHT;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;

public abstract class AbstractLevel implements InterfaceLevel{

    /* Maps */
    /** Map with all icons */
    private List map = new ArrayList(32);
    /** Map with only the active icons */
    private List activeMap = new ArrayList(32) ;

    /** The station */
    private Station station ;
    /** The chopper */
    private Helicopter helicopter ;

    /* Status */
    /** How many people were killed? */
    private int killed = 0 ;
    /** How many people are passengers right now? */
    private int passengers = 0 ;
    /** How many people entered to station? */
    private int saved = 0 ;
    /** Level started? */
    private boolean started = false ;

    // ------------------------------------------------------------------------

    /**
     * Paint all elements
     */


    public void draw(Canvas canvas) {
        for (int i = 0; i < getMap().size(); ++i) {
            getMap(i).draw(canvas);
        }
    }

    // ------------------------------------------------------------------------

    /**
     * Pings all active elements and sets view (scrollx)
     */

    public final int heartBeat() {
        int result = -1 ;
        int newScrollX = -1 ;

        for (int i = 0; i < getActiveMap().size(); ++i) {

            try {
                result = getActiveMap(i).heartBeat() ;
            } catch (DestroyedException de) {
                remove((InterfaceSprite) getActiveMap(i)) ;
            }
            if (result > -1) {
                newScrollX = result ;
            }
        }

        return newScrollX ;
    }


    // ------------------------------------------------------------------------

    /**
     * This handles explosions of arm1. Looks for destroyables near to the
     * explosion.
     */
    public void manageCollision(int x, int y) throws DestroyedException{
        // Only collide in view
        int sx = getHelicopter().getX() ;
        if ((x < sx) || (x > sx + C64Theme.SCREEN_WIDTH)) return ;

        for (int i = 0; i < getActiveMap().size(); ++i) {
            if (! (getActiveMap(i) instanceof Arm)) {
                if (getActiveMap(i).isNear(x, y)) {

                    if (getActiveMap(i) instanceof Human) {
                        if (! ((Human) getActiveMap(i)).isInHelicopter()) {
                            incKilled() ;
                            getActiveMap(i).explode() ;
                            throw new DestroyedException() ;
                        } else {
                            continue ;
                        }
                    } else {
                        getActiveMap(i).explode() ;
                    }
                    return ; // only destroy one icon
                }
            }
        }
    }

    // ------------------------------------------------------------------------

    /**
     * Add element to game
     */
    public void add(InterfaceSprite sprite) {
        if (sprite instanceof Human) {
            if (! map.contains(getStation()))
                add(getStation()) ;
            if (! map.contains(getHelicopter()) )
                add(getHelicopter()) ;
       }
        map.add(sprite) ;
        if (sprite instanceof InterfaceAnimatedSprite) {
            activeMap.add(sprite) ;
        }
    }

    /**
     * Remove element from game
     */
    public void remove(InterfaceSprite sprite) {
        map.remove(sprite) ;
        activeMap.remove(sprite) ;
    }

    // ------------------------------------------------------------------------

    /**
     * Getter
     */
    public boolean isHelicopterNear(int x) {
        return  (getHelicopter().getY() > getLandingCoordsY() - 30)
                && Math.abs((double) (x - getHelicopter().getX())) < 100 ;
    }


    /**
     * Getter
     */
    public List getActiveMap() {
        return activeMap ;
    }

    /**
     * Getter
     */
    public InterfaceAnimatedSprite getActiveMap(int i) {
        return (InterfaceAnimatedSprite) getActiveMap().get(i) ;
    }

    /**
     * Getter
     */
    public List getMap() {
        return map ;
    }

    /**
     * Getter
     */
    public InterfaceSprite getMap(int i) {
        return (InterfaceSprite) map.get(i) ;
    }

    /**
     * Setter
     */
    public void setMap(List list) {
        this.map = list ;
    }

    /**
     * Getter
     */
    public int getKilled() {
        return killed ;
    }

    /**
     * Getter
     */
    public int getPassengers() {
        return passengers ;
    }

    /**
     * Getter
     */
    public int getSaved() {
        return saved ;
    }

    /**
     * Setter
     */
    public void incKilled() {
        killed++ ;
    }

    /**
     * Setter
     */
    public void incPassengers() {
        passengers++ ;
    }

    /**
     * Setter
     */
    public void decPassengers() {
        passengers-- ;
    }

    /**
     * Setter
     */
    public void incSaved() {
        saved++ ;
    }

    /**
     * Getter
     */
    public Helicopter getHelicopter() {
        if (helicopter == null)
            setHelicopter(new Helicopter(this));
        return helicopter ;
    }

    /**
     * Setter
     */
    public void setHelicopter(Helicopter helicopter) {
        this.helicopter = helicopter;
    }

    /**
     * Getter
     */
    public Station getStation() {
        if (station == null) {
            setStation(new Station(getStartX() + 125, getStartY() - 2));
            add(new Fence(SCREEN_WIDTH, getStartY())) ;
            add(new Fence(SCREEN_WIDTH , getStartY()+ ((SCREEN_HEIGHT/3)/4))) ; // These ain't no
            add(new Fence(SCREEN_WIDTH, getStartY()+((SCREEN_HEIGHT/3)/2))) ; // real coords
            add(new Fence(SCREEN_WIDTH, getStartY()+((SCREEN_HEIGHT/3)/4*3))) ;

        }
        return station ;
    }

    /**
     * Setter
     */
    public void setStation(Station station) {
        this.station = station ;
    }

    /**
     * Getter
     */
    public final int getLandingCoordsX() {
        return getStation().getX() + 10 ;
    }

    /**
     * Getter
     */
    public final int getLandingCoordsY() {
        return getStation().getY() + 2 ;
    }

    /**
     * Getter
     */
    public boolean isStarted() {
        return started ;
    }

    /**
     * Setter
     */
    public void setStarted(boolean started) {
        this.started = started ;
    }

    public void refreshAnimation(){
        for (int i = 0; i < getActiveMap().size(); ++i) {
            if (getActiveMap(i) instanceof Tank || getActiveMap(i) instanceof Human || getActiveMap(i) instanceof Helicopter) {
                getActiveMap(i).refreshAnimation();
            }
        }

    }











}
