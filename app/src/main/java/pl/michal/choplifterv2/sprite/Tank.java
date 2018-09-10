package pl.michal.choplifterv2.sprite;

import pl.michal.choplifterv2.level.DestroyedException;
import pl.michal.choplifterv2.level.InterfaceLevel;

import static pl.michal.choplifterv2.c64.C64Theme.FENCE_LINE;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;
import static pl.michal.choplifterv2.c64.C64Theme.SPRITE_SCALE;
import static pl.michal.choplifterv2.level.InterfaceLevel.MAXWIDTH;

public class Tank extends AbstractAnimatedSprite {
    public final static int DIR_CENTER = 6 ;
    public final static int DIR_CENTER_L = 4 ;
    public final static int DIR_CENTER_R = 5 ;
    public final static int DIR_LEFT = 1 ;
    public final static int DIR_RIGHT = 2 ;

    private final static int IMPULSE = 100 ;
    private final static int FULL_THROTTLE = 10 ;

    private int stepsX = 0 ;
    private int impulseX = 0 ;

    public Tank(InterfaceLevel pLevel) {
        this(pLevel, (int) Math.round(Math.random() * (MAXWIDTH-400)) + SCREEN_WIDTH) ;
    }

    public Tank(InterfaceLevel pLevel, int x) {
        setX(x) ;
        setY(pLevel.getStartY() + 19* SPRITE_SCALE) ;
        setDirection(DIR_RIGHT) ;
        setLevel(pLevel) ;
        loadAnimation() ;
    }


    public int action() throws DestroyedException {

        if (explodeCount > 0 && getDirection() == CRASH) {
                explode();
            loadAnimation();
            return -1 ;
        }

        if (--impulseX <= 0) {
            if (getLevel().isHelicopterNear(getX())) {
                // Move to helicopter
                impulseX = IMPULSE ;
                if (getX() <  getLevel().getHelicopter().getX()) {
                    stepsX = +FULL_THROTTLE ;
                } else {
                    stepsX = -FULL_THROTTLE ;
                }
            } else {
                // Random movement
                impulseX = (int) (50 + Math.random() * IMPULSE) ;
                stepsX = Math.random() > 0.5 ? -FULL_THROTTLE : FULL_THROTTLE ;
                int newDirection = stepsX > 0 ? DIR_RIGHT : DIR_LEFT ;
                if (newDirection != getDirection()) {
                    setDirection(newDirection);
                    return -1 ;
                }
            }
        }

        if (Math.random() > 0.98) {
            shoot() ;
        }

        setX(getX() + stepsX) ;

        if (getX() > MAXWIDTH) {
            stepsX = -stepsX ;
        } else if (getX() < FENCE_LINE +50) {
            stepsX = -stepsX ;
        }
        loadAnimation() ;
        return -1 ;
    }


    public void loadAnimation() {
        switch (getDirection()) {
            case DIR_RIGHT:
                setAnimation(SpriteDirection.TANK,2) ; //tankRight
                break ;
            case DIR_LEFT:
                setAnimation(SpriteDirection.TANK,1) ; //tankLeft
                break ;
            case DIR_CENTER_L:
                setAnimation(SpriteDirection.TANK,0) ; //tankCenterL
                break ;
            case DIR_CENTER:
                setAnimation(SpriteDirection.TANK,0) ; //tankCenter
                break ;
            case DIR_CENTER_R:
                setAnimation(SpriteDirection.TANK,0) ; //tankCenterR
                break ;
            case CRASH:
                setAnimation(SpriteDirection.TANK,3) ; //tankCrash
                break ;
        }
    }


    private void shoot() {
        getLevel().add(new TankArm(getX(), getY(), getDirection(), getLevel())) ;
    }

    public void remove() {
        Tank reinkarnation = new Tank(getLevel()) ;
        getLevel().add(reinkarnation) ;
        getLevel().remove(this) ;
    }

    public String toString() {
        return "Tank" ;
    }

}