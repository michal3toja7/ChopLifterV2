package pl.michal.choplifterv2.sprite;

import pl.michal.choplifterv2.level.DestroyedException;
import pl.michal.choplifterv2.level.InterfaceLevel;

public class Human extends AbstractAnimatedSprite{
    public final static int DIR_CENTER = 6 ;
    public final static int DIR_INVISIBLE = 5 ;
    public final static int DIR_LEFT = 1 ;
    public final static int DIR_RIGHT = 2 ;

    private final static int IMPULSE = 50 ;
    private final static int FULL_THROTTLE = 2 ;


    private int stepsX = 0 ;
    private int impulseX = 0 ;

    private boolean isInHelicopter = false ;
    private boolean isSaved = false ;

    public Human(InterfaceLevel pLevel) {
        setX((int) Math.round(Math.random() * (InterfaceLevel.MAXWIDTH-400)) + 100) ;
        setY(pLevel.getStartY() + 8) ;
        setDirection(DIR_RIGHT) ;
        setLevel(pLevel) ;
        loadAnimation() ;
    }

    public Human(InterfaceLevel pLevel, House house) {
        this(pLevel) ;
        setX(house.getX()) ;
    }

    public int action(){ //throws DestroyedException {
        if (isInHelicopter) {
            if (! getLevel().getHelicopter().canLeave()) {
                // Flying
                return -1 ;
            } else {
                // Enter ExitHelicopter mode
                setX(getLevel().getHelicopter().getX() + 15) ;
                stepsX = +FULL_THROTTLE ;
                impulseX = 0 ;
                isInHelicopter = false ;
                isSaved = true ;
                getLevel().decPassengers() ;
                return -1 ;
            }
        } else if (isSaved) {
            if (getLevel().getStation().canEnter(getX())) {
                // Entered station
                getLevel().incSaved() ;
                loadAnimation() ;
 //               throw new DestroyedException() ;
            } else {
                // ExitHelicopter and enter station
                if (impulseX-- <= 0) {
                    impulseX = (int) (5 + Math.random() * IMPULSE) ;
                    if (Math.random() > 0.5) {
                        stepsX = +FULL_THROTTLE ;
                    } else {
                        stepsX = 0 ;
                        setDirection(DIR_CENTER) ;
                    }
                }
                setX(getX() + stepsX) ;
                loadAnimation() ;
            }
            return -1 ;
        }




        if (--impulseX <= 0) {
            if (getLevel().isHelicopterNear(getX())) {
                // Run to helicopter
                impulseX = IMPULSE ;
                if (getX() <  getLevel().getHelicopter().getX()) {
                    stepsX = +FULL_THROTTLE ;
                } else {
                    stepsX = -FULL_THROTTLE ;
                }
            } else if (Math.random() > 0.5) {
                // Just run randomly
                impulseX = (int) (20 + Math.random() * IMPULSE) ;
                stepsX = Math.random() > 0.5 ? -FULL_THROTTLE : FULL_THROTTLE ;
            } else {
                // Salute
                impulseX = 20 ;
                stepsX = 0 ;
                setDirection(DIR_CENTER) ;
                loadAnimation() ;
                return -1 ;
            }
        }

        setX(getX() + stepsX) ;

        if (getX() < 50) {
            stepsX = -stepsX ;
        } else if (getX() > getLevel().getLandingCoordsX()-150) {
            stepsX = -stepsX ;
        }

 /*       if (getLevel().getHelicopter().canEnter(getX())) {
            if (getLevel().getHelicopter().isAlive()
                    && (getLevel().getPassengers() < 8)) {
                isInHelicopter = true ;
                loadAnimation() ;
                getLevel().incPassengers() ;
                return -1 ; // Entered
            }
        }
*/
        loadAnimation() ;
        return -1 ;
    }

    public void loadAnimation() {
        if (isInHelicopter) {
 //           setAnimation(humanInvisible) ;
        } else if (stepsX == 0) {
            setAnimation(SpriteDirection.HUMAN, 0) ;
        } else if (stepsX > 0) {
            setAnimation(SpriteDirection.HUMAN, 2) ;
        } else if (stepsX < 0){
            setAnimation(SpriteDirection.HUMAN, 1) ;
        } else if (getDirection() == CRASH) {
            System.out.println("Human crashed") ;
        }
    }

    public String toString() {
        return "Human" ;
    }


    public boolean isInHelicopter() {
        return isInHelicopter;
    }
}

