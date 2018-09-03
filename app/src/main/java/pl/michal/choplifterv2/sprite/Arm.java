package pl.michal.choplifterv2.sprite;

import pl.michal.choplifterv2.c64.C64Theme;
import pl.michal.choplifterv2.level.DestroyedException;
import pl.michal.choplifterv2.level.InterfaceLevel;

public class Arm extends AbstractAnimatedSprite {
    private int sx = 0 ;
    private int ox = 0 ;
    private int sd = -1 ;

    private int ground = 1024 ;

    public Arm(int x, int y, int sx, int sy, int d, InterfaceLevel pLevel) {
        this.sx = sx ;
        this.ox = x ;
        this.sd = d ;
        setLevel(pLevel) ;
        setX(x + 8) ;
        setY(18 + ((int)(y/15))*15) ;
        this.ground = pLevel.getLandingCoordsY() + 12 ;
        setAnimation(SpriteDirection.ARM, 0) ;
    }


    public void loadAnimation() {
        if (getDirection() == CRASH) {

            setAnimation(SpriteDirection.ARM, 1) ;
        }
    }


    public final int action(){// throws DestroyedException {
        if (getDirection() == CRASH) return -1 ;

        switch (sd) {
            case Helicopter.DIR_RIGHT:
                setX(getX()+15) ;
                setY(getY() + (sx/2)) ;
                break ;
            case Helicopter.DIR_LEFT:
                setX(getX()-15) ;
                setY(getY() - (sx/2)) ;
                break ;
            case Helicopter.DIR_CENTER:
                setY(getY() + 15) ;
                break ;
        }

        if (hasCollision()) {
            try {
                explode() ;
            } catch (DestroyedException e) {
                e.printStackTrace();
            }
        }
        return -1 ;
    }

    public final boolean hasCollision() {
        return (getX() > ox + C64Theme.SCREEN_WIDTH)
                || (getX() < ox - C64Theme.SCREEN_WIDTH)
                || (getY() < 0)
                || (getY() >= ground) ;
    }
}


