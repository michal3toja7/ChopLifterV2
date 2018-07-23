package pl.michal.choplifterv2.sprite;

/**
 * Created by micha on 20.03.2018.
 */

public class Helicopter  extends  AbstractAnimatedSprite{
    public enum HelicopterDirection {
        CENTER("helicopter_center",new String[] {"", "_land", "_left", "_right"}, 3),
        CRASH("helicopter_crash",new String[] {""}, 4),
        LEFT("helicopter_left", new String[] {"", "_back", "_down", "_halfback", "_halfdown", "_land"},3),
        RIGHT("helicopter_right", new String[] {"", "_back", "_down", "_halfback", "_halfdown", "_land"},3),
        ROTATE("helicopter_rotate", new String[] {"_left", "_right"},0);

        private String spriteSide;
        private String directionName[];
        private int numberFrames;
        HelicopterDirection(String spriteSide, String direction[], int numberFrames) {
            this.spriteSide = spriteSide;
            this.directionName = direction;
            this.numberFrames = numberFrames;
        }
        public String getSpriteSide(){
            return spriteSide;
        }
        public String[] getDirectionName(){
            return directionName;
        }
        public int getNumberFrames(){
            return numberFrames;
        }
    }



    protected final static int DIR_CENTER = 0 ;
    private final static int DIR_CENTER_LAND = 100 + DIR_CENTER ;
    private final static int DIR_CENTER_L = 4 ;
    private final static int DIR_CENTER_R = 5 ;
    protected final static int DIR_LEFT = 1 ;
    private final static int DIR_LEFT_LAND = 100 + DIR_LEFT ;
    protected final static int DIR_RIGHT = 2 ;
    private final static int DIR_RIGHT_LAND = 100 + DIR_RIGHT ;

    private final static int IMPULSE = 60 ;
    private final static int FULL_THROTTLE = 10 ;
    private final static float GRAVITY = 0.6f ;
    private final static int ANGLEPOINT = 5 ;
    private final static int MAXANGLEPOINT = ANGLEPOINT + 3 ;

    private int v = 0 ;
    private int stepsX = 0 ;
    private int stepsY = 0 ;

    private int impulseX = 0 ;
    private int impulseY = 0 ;


    public Helicopter(){
        //   setX(pLevel.getLandingCoordsX()) ;
        //   setY(pLevel.getLandingCoordsY()) ;
        //   this.v = pLevel.getStartX() ;
        // setLevel(pLevel) ;
        setX(400);
        setY(400);
        setDirection(DIR_CENTER) ;
        loadAnimation() ;

    }
/*    public boolean isLanded() {
        return getY() == getLevel().getLandingCoordsY() ;
    }

    public boolean canEnter(int x) {
        return isLanded()
                && (x > (getX()+12)-4)
                &&  (x < (getX()+12)+4) ;
    }

    public boolean canLeave() {
        return isLanded()
                && (getX() > getLevel().getLandingCoordsX()-10)
                &&  (getX() < getLevel().getLandingCoordsX()+20) ;
    }

*/
/*public void move()  {
    if (impulseX-- != IMPULSE -1)
        if (impulseX > 0) {
            if (impulseX % 10 == 0) {
                stepsX = (int) (stepsX * GRAVITY) ;
                loadAnimation() ;
            }
        } else {
            impulseX = 0 ;
        }
    if (--impulseY > 0) {
        if (impulseY % 10 == 0) {
            stepsY = (int) (stepsY * GRAVITY) ;
            loadAnimation() ;
        }
    } else {
        impulseY = 0 ;
    }

    setX(getX() + stepsX) ;
    setY(getY() + stepsY) ;

    if (getY() >= 400 ){//getLevel().getLandingCoordsY()) {
        // Don't move when landed
        setY(400);//getLevel().getLandingCoordsY()) ;
        if (Math.sqrt(Math.sqrt(stepsY * stepsY) * Math.sqrt(stepsY * stepsY))
                > FULL_THROTTLE) {
            stepsX = 0 ;
            stepsY = 0 ;
            impulseX = 0 ;
            impulseY = 0 ;
        //    explode() ;
        } else
        if (Math.sqrt(Math.abs(stepsY) * Math.abs(stepsY))
                > FULL_THROTTLE / 4) {
            setDirection(getDirection() + 100) ; // 100 + x is the landing ani
            loadAnimation() ;
        }
        stepsY = 0 ;
        stepsX = 0 ;
    } else
    if (getY() < 50) {
        stepsY = 0 ;
        setY(50) ;
        loadAnimation() ;
    }

    if (getX() < 50) {
        setX(50) ;
        stepsX = 0 ;
        loadAnimation() ;
    } else if (getX() >  1030) { //ILevel.MAXWIDTH -50
        setX(1030) ; //ILevel.MAXWIDTH - 50
        stepsX = 0 ;
        loadAnimation() ;
        return ;
    }
}


*/
public void move(double angle, double power){
    int getX = getX();
    int getY = getY();
    setX((int)(getX -( Math.cos(angle) * power)));
    setY((int)(getY + (Math.sin(-angle) * power)));
  //  if (posX > width - radius) posX = width - radius;
 //   if (posX < radius) posX = radius;
 //   if (posY > height - radius) posY = height - radius;
 //   if (posY < radius) posY = radius;
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
                    setAnimation(HelicopterDirection.RIGHT, 0);

                else if (stepsX > 0) {
                    if (Math.abs(stepsX) < MAXANGLEPOINT)
                        setAnimation(HelicopterDirection.RIGHT, 4);
                    else
                        setAnimation(HelicopterDirection.RIGHT, 2);
                } else if (Math.abs(stepsX) < MAXANGLEPOINT)
                    setAnimation(HelicopterDirection.RIGHT, 3);
                else
                    setAnimation(HelicopterDirection.RIGHT, 1);
                break ;
            case DIR_LEFT:
                if (Math.abs(stepsX) <= ANGLEPOINT)
                    setAnimation(HelicopterDirection.LEFT, 0);
                else if (stepsX < 0) {
                    if (Math.abs(stepsX) < MAXANGLEPOINT)
                        setAnimation(HelicopterDirection.LEFT, 4);
                    else
                        setAnimation(HelicopterDirection.LEFT, 2);
                } else if (Math.abs(stepsX) < MAXANGLEPOINT)
                    setAnimation(HelicopterDirection.LEFT, 3);
                else
                    setAnimation(HelicopterDirection.RIGHT, 1);
                break ;
/*           IdDirection Center
            0 - ""
            1 - _land
            2 - _left
            3 - _right

*/
            case DIR_CENTER:
                if (Math.sqrt(stepsX*stepsX) <= ANGLEPOINT) {
                    setAnimation(HelicopterDirection.CENTER, 0);
                } else if (stepsX > 0) {
                    setAnimation(HelicopterDirection.CENTER, 3);
                } else {
                    setAnimation(HelicopterDirection.CENTER, 2);
                } break ;
            case DIR_CENTER_L:
                setAnimation(HelicopterDirection.ROTATE, 0);
                break ;
            case DIR_CENTER_R:
                setAnimation(HelicopterDirection.ROTATE, 1);
                break ;
            case CRASH:
                setAnimation(HelicopterDirection.CRASH, 0);
                break ;
            case DIR_RIGHT_LAND:
                setAnimation(HelicopterDirection.RIGHT, 5);
                setDirection(DIR_RIGHT) ;
                break ;
            case DIR_LEFT_LAND:
                setAnimation(HelicopterDirection.LEFT, 5);
                setDirection(DIR_LEFT) ;
                break ;
            case DIR_CENTER_LAND:
                setAnimation(HelicopterDirection.CENTER, 1);
                setDirection(DIR_CENTER) ;
                break ;
        }
    }

    public void moveLeft() {
 //       if (getY() >= getLevel().getLandingCoordsY()) { setY(getLevel().getLandingCoordsY()); stepsX = 0 ; return ; } // Don't move when landed
 //      if (getX() <= 10){ setX(10) ; stepsX = 0 ; return ; }
        impulseX = IMPULSE ;
        if (stepsX > - FULL_THROTTLE) {
            stepsX-- ;
            loadAnimation() ;
        }
    }

    public void moveRight() {
  //      if (getY() >= getLevel().getLandingCoordsY()) { setY(getLevel().getLandingCoordsY()); stepsX = 0 ; return ; } // Don't move when landed
  //      if (getX() > ILevel.MAXWIDTH - 50) { setX(ILevel.MAXWIDTH - 50) ; stepsX = 0 ; return ; }
        impulseX = IMPULSE ;
        if (stepsX < FULL_THROTTLE) {
            stepsX++ ;
            loadAnimation() ;
        }
    }

    public void moveUp() {
  //      if (! isAlive()) return ;
   //     if (getY() <= 50) { setY(50) ; stepsY = 0; return ; } // Don't rise higher
        if (stepsY > - FULL_THROTTLE) {
            stepsY-- ;
            impulseY = IMPULSE ;
            loadAnimation() ;
        }
    }

    public void moveDown() {
   //     if (getY() >= getLevel().getLandingCoordsY()) { setY(getLevel().getLandingCoordsY()) ; stepsY = 0; return ; } // Don't do anything when landed
        if (stepsX < FULL_THROTTLE) {
            stepsY++ ;
            impulseY = IMPULSE ;
            loadAnimation() ;
        }
    }



}