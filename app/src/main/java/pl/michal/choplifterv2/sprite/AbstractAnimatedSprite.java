package pl.michal.choplifterv2.sprite;

/**
 * Created by micha on 16.04.2018.
 */

public abstract class AbstractAnimatedSprite extends AbstractSprite implements InterfaceSprite,InterfaceAnimatedSprite {
    private Helicopter.HelicopterDirection actualHelicopterDirection;
    private int actualIdDirection;
    public final static int CRASH = -1 ;
  //  private ILevel level ;
    private int ticker = 0 ;
    private int direction;
    private String vectorImageName = null ;
    private static int frame =1;

/*    public final int heartBeat() throws DestroyedException {
        if (vectorImageName != null) {
            ticker = ++ticker % 16 ;
      //      setIcon(vectorImageName[ticker % vectorImageName.length]) ;
        }
        return action() ;
    }
*/
    public final void setAnimation(Helicopter.HelicopterDirection HelicopterDirection, int IdDirection) {
            actualHelicopterDirection=HelicopterDirection;
            actualIdDirection = IdDirection;
            StringBuilder builderVectorImage = new StringBuilder();
            String [] Direction = HelicopterDirection.getDirectionName();
            builderVectorImage.append(HelicopterDirection.getSpriteSide());
            builderVectorImage.append(Direction[IdDirection]);
            if (HelicopterDirection.getNumberFrames() != 0){
                if (frame <= HelicopterDirection.getNumberFrames() && frame > 0){
                    builderVectorImage.append(frame);
                }
            }
            vectorImageName = builderVectorImage.toString();

            frame ++;
            if (frame > HelicopterDirection.getNumberFrames() || frame < 1){
                frame = 1;
            }
    }


    public String getVectorImageName() {
        return vectorImageName;
    }
    public void refreshAnimation(){
        setAnimation(actualHelicopterDirection,actualIdDirection);
    }
/*
    public boolean isNear(int x, int y) {
        return (Math.abs((double) (x - getX())) < 10)
                && Math.abs((double) (y - getY())) < 10;
    }

    public final void explode() throws DestroyedException {
        if (getDirection() != CRASH) {
            (new ExplodeThread()).start() ;
            if (this instanceof IArm) {
                getLevel().manageCollision(getX(), getY()) ;
            }
        }
    }

    public synchronized void remove() {
        level.remove(this) ;
    }

    private final class ExplodeThread extends Thread {
        private final static int DELAY = 400 ;

        public void run () {
            try {
                setDirection(CRASH) ;
                loadAnimation() ;

                Thread.sleep(DELAY) ;
                remove() ;
            } catch (InterruptedException e) {}
        }
    }


    */

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


/*
    public ILevel getLevel() {
        return level;
    }

    public void setLevel(ILevel level) {
        this.level = level;
    }

*/
}
