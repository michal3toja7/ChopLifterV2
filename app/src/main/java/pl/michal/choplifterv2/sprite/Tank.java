package pl.michal.choplifterv2.sprite;

import pl.michal.choplifterv2.level.InterfaceLevel;

public class Tank extends AbstractAnimatedSprite {
    public final static int DIR_CENTER = 6 ;
    public final static int DIR_CENTER_L = 4 ;
    public final static int DIR_CENTER_R = 5 ;
    public final static int DIR_LEFT = 1 ;
    public final static int DIR_RIGHT = 2 ;

    private final static int IMPULSE = 50 ;
    private final static int FULL_THROTTLE = 10 ;

    private int stepsX = 0 ;
    private int impulseX = 0 ;

    public Tank(InterfaceLevel pLevel) {
        this(pLevel, (int) Math.round(Math.random() * (InterfaceLevel.MAXWIDTH-400)) + 100) ;
    }

    public Tank(InterfaceLevel pLevel, int x) {
        setX(x) ;
        setY(pLevel.getStartY() + 19) ;
        setDirection(DIR_RIGHT) ;
        setLevel(pLevel) ;
        loadAnimation() ;
    }


    public int action() {
        if (getDirection() == CRASH) return -1 ;

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
                  //  toggleDirection() ;
                    return -1 ;
                }
            }
        }

        if (Math.random() > 0.97) {
        //    shoot() ;
        }

        setX(getX() + stepsX) ;

        if (getX() < 50) {
            stepsX = -stepsX ;
        } else if (getX() > getLevel().getLandingCoordsX() - 200) {
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


 /*   private void shoot() {
        getLevel().add(new TankArm(getX(), getY(), getDirection(), getLevel())) ;
    }

    public void remove() {
        Tank reinkarnation = new Tank(getLevel(), 0) ;

        if (BattleField.getScrollx() < ILevel.MAXWIDTH/4) {
            reinkarnation.setX(BattleField.getScrollx() + 350) ;
        } else {
            reinkarnation.setX(BattleField.getScrollx() - 200) ;
        }

        getLevel().add(reinkarnation) ;
        getLevel().remove(this) ;
    }
*/


    public String toString() {
        return "Tank" ;
    }
}
