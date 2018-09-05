package pl.michal.choplifterv2.sprite;

import pl.michal.choplifterv2.level.DestroyedException;
import pl.michal.choplifterv2.level.InterfaceLevel;

import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;
import static pl.michal.choplifterv2.level.InterfaceLevel.MAXWIDTH;

/**
 * Created by micha on 20.03.2018.
 */

public class Helicopter extends AbstractAnimatedSprite {

    protected final static int DIR_CENTER = 0;
    private final static int DIR_CENTER_LAND = 100 + DIR_CENTER;
    private final static int DIR_CENTER_L = 4;
    private final static int DIR_CENTER_R = 5;
    protected final static int DIR_LEFT = 1;
    private final static int DIR_LEFT_LAND = 100 + DIR_LEFT;
    protected final static int DIR_RIGHT = 2;
    private final static int DIR_RIGHT_LAND = 100 + DIR_RIGHT;

    private final static int IMPULSE = 60;
    private final static int FULL_THROTTLE = 10;
    private final static float GRAVITY = 0.4f;
    private final static int ANGLEPOINT = 5;
    private final static int MAXANGLEPOINT = ANGLEPOINT + 3;

    private int v = 0;
    private int stepsX = 0;
    private int stepsY = 0;

    private int impulseX = 0;
    private int impulseY = 0;
    private int oldX = 0;
    private int oldY = 0;
    private double angle;
    private double power;

    public Helicopter(InterfaceLevel pLevel) {
        setX(pLevel.getLandingCoordsX());
        setY(pLevel.getLandingCoordsY());
        this.v = pLevel.getStartX();
        setLevel(pLevel);
        setDirection(DIR_CENTER);
        loadAnimation();


    }

    public boolean isLanded() {
        return getY() == getLevel().getLandingCoordsY();
    }

    public boolean canEnter(int x) {
        return isLanded()
                && (x > (getX() + 12) - 4)
                && (x < (getX() + 12) + 4);
    }

    public boolean canLeave() {
        return isLanded()
                && (getX() > getLevel().getLandingCoordsX() - 10)
                && (getX() < getLevel().getLandingCoordsX() + 20);
    }


    public void move(double angle, double power) {
        this.angle = angle;
        this.power = power;
        oldX = getX();
        oldY = getY();
        setX((int) (oldX - ((Math.cos(angle) * power) * GRAVITY)));
        setY((int) (oldY + ((Math.sin(-angle) * power) * GRAVITY)));
        stepsX = getX() - oldX;
        stepsY = getY() - oldY;


        if (getY() >= getLevel().getLandingCoordsY()) {
            // Don't move when landed
            setY(getLevel().getLandingCoordsY());
            if (Math.sqrt(Math.sqrt(stepsY * stepsY) * Math.sqrt(stepsY * stepsY))
                    > FULL_THROTTLE) {
                stepsX = 0;
                stepsY = 0;
                impulseX = 0;
                impulseY = 0;
                try {
                    explode() ;
                } catch (DestroyedException e) {
                    e.printStackTrace();
                }
                return;
            } else if (Math.sqrt(Math.abs(stepsY) * Math.abs(stepsY))
                    > FULL_THROTTLE / 4) {
                setDirection(getDirection() + 100); // 100 + x is the landing ani
            }
            stepsY = 0;
            stepsX = 0;
        } else if (getY() < 50) {
            stepsY = 0;
            setY(50);
        }
//        System.out.println("Helicopter X= " + getX());
//        System.out.println("Helicopter Y= " + getY());

        if (getX() > (SCREEN_WIDTH - (SCREEN_WIDTH / 9)) - getScrollX()) {
            setScrollX ((SCREEN_WIDTH - (SCREEN_WIDTH / 9)) - getX());
            stepsX = getX() - oldX;
        }
        if (getX() < (SCREEN_WIDTH / 9) - getScrollX()) {
            setScrollX (((SCREEN_WIDTH / 9)) - getX());
            stepsX = getX() - oldX;
        }

        if (getX() < 50) {
            setX(50);
            stepsX = 0;
        } else if (getX() > MAXWIDTH - 50) {
            setX(MAXWIDTH - 50);
            stepsX = 0;
        }

        loadAnimation();
    }

    public void toggleDirection() {
        if (getX() - oldX > 0) {
            setDirection(DIR_CENTER_R);
            loadAnimation();
            setDirection(DIR_RIGHT);
            loadAnimation();
        } else if (getX() - oldX < 0) {
            setDirection(DIR_CENTER_L);
            loadAnimation();
            setDirection(DIR_LEFT);
            loadAnimation();
        } else if (getX() - oldX == 0) {
            setDirection(DIR_CENTER);
            loadAnimation();
        }

    }

    @Override
    public int action() {
        move(angle, power);

        if (explodeCount > 0 && getDirection() == CRASH) {
            try {
                explode();
            } catch (DestroyedException e) {
                e.printStackTrace();
            }
            loadAnimation();
        }


        return -1;
    }

    public void loadAnimation() {

        switch (getDirection()) {

/*            IdDirection Left and Right:
            0 - ""
            1 - _back
            2 - _down
            3 - _halfback
            4 - _halfdown
            5 - _land

*/
            case DIR_RIGHT:
                if (Math.abs(stepsX) <= ANGLEPOINT)
                    setAnimation(SpriteDirection.HEL_RIGHT, 0);

                else if (stepsX > 0) {
                    if (Math.abs(stepsX) < MAXANGLEPOINT)
                        setAnimation(SpriteDirection.HEL_RIGHT, 4);
                    else
                        setAnimation(SpriteDirection.HEL_RIGHT, 2);
                } else if (Math.abs(stepsX) < MAXANGLEPOINT)
                    setAnimation(SpriteDirection.HEL_RIGHT, 3);
                else
                    setAnimation(SpriteDirection.HEL_RIGHT, 1);
                break;
            case DIR_LEFT:
                if (Math.abs(stepsX) <= ANGLEPOINT)
                    setAnimation(SpriteDirection.HEL_LEFT, 0);
                else if (stepsX < 0) {
                    if (Math.abs(stepsX) < MAXANGLEPOINT)
                        setAnimation(SpriteDirection.HEL_LEFT, 4);
                    else
                        setAnimation(SpriteDirection.HEL_LEFT, 2);
                } else if (Math.abs(stepsX) < MAXANGLEPOINT)
                    setAnimation(SpriteDirection.HEL_LEFT, 3);
                else
                    setAnimation(SpriteDirection.HEL_RIGHT, 1);
                break;
/*           IdDirection Center
            0 - ""
            1 - _land
            2 - _left
            3 - _right

*/
            case DIR_CENTER:
                if (Math.sqrt(stepsX * stepsX) <= ANGLEPOINT) {
                    setAnimation(SpriteDirection.HEL_CENTER, 0);
                } else if (stepsX > 0) {
                    setAnimation(SpriteDirection.HEL_CENTER, 3);
                } else {
                    setAnimation(SpriteDirection.HEL_CENTER, 2);
                }
                break;
            case DIR_CENTER_L:
                setAnimation(SpriteDirection.HEL_ROTATE, 0);
                break;
            case DIR_CENTER_R:
                setAnimation(SpriteDirection.HEL_ROTATE, 1);
                break;
            case CRASH:
                setAnimation(SpriteDirection.HEL_CRASH, 0);
                break;
            case DIR_RIGHT_LAND:
                setAnimation(SpriteDirection.HEL_RIGHT, 5);
                setDirection(DIR_RIGHT);
                break;
            case DIR_LEFT_LAND:
                setAnimation(SpriteDirection.HEL_LEFT, 5);
                setDirection(DIR_LEFT);
                break;
            case DIR_CENTER_LAND:
                setAnimation(SpriteDirection.HEL_CENTER, 1);
                setDirection(DIR_CENTER);
                break;
        }
    }

    public boolean isAlive() {
        return getDirection() != CRASH;
    }

    public boolean isNear(int x, int y) {
        return (Math.abs((double) (x - getX())) < 20)
                && Math.abs((double) (y - getY())) < 22;
    }

    public void remove() {
        setDirection(CRASH);
        loadAnimation();
        while (getLevel().getPassengers() > 0) {
            getLevel().incKilled();
            getLevel().decPassengers();
        }
    }

    public void shoot() {
        getLevel().add(new Arm(getX(), getY(), stepsX, stepsY, getDirection(), getLevel()));
    }


}