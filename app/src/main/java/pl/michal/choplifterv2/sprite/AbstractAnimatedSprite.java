package pl.michal.choplifterv2.sprite;

/**
 * Created by micha on 16.04.2018.
 */

public abstract class AbstractAnimatedSprite extends AbstractSprite {


    private ILevel level ;
    private int ticker = 0 ;
    private Helicopter.HelicopterDirection direction;
    private BufferedImage[] animation = null ;


    public final int heartBeat() throws DestroyedException {
        if (animation != null) {
            ticker = ++ticker % 16 ;
            setIcon(animation[ticker % animation.length]) ;
        }
        return action() ;
    }

    public final void setAnimation(Helicopter.HelicopterDirection HelicopterDirection, int IdDirection) {

        String [] Direction = HelicopterDirection.getDirection();


        HelicopterDirection.getNumberFrames();

    }

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

    public Helicopter.HelicopterDirection getDirection() {
        return direction;
    }

    public void setDirection(Helicopter.HelicopterDirection direction) {
        this.direction = direction;
    }

    public ILevel getLevel() {
        return level;
    }

    public void setLevel(ILevel level) {
        this.level = level;
    }
}
