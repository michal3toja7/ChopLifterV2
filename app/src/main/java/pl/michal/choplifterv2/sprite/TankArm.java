package pl.michal.choplifterv2.sprite;

import pl.michal.choplifterv2.c64.C64Theme;
import pl.michal.choplifterv2.level.DestroyedException;
import pl.michal.choplifterv2.level.InterfaceLevel;

import static pl.michal.choplifterv2.c64.C64Theme.SPRITE_SCALE;

public class TankArm extends AbstractAnimatedSprite {
    private int ox = 0 ;
    private int sd = -1 ;

    private int ground = 1024 ;

    public TankArm(int x, int y, int d, InterfaceLevel pLevel) {
        this.ox = x ;
        this.sd = d ;
        setLevel(pLevel) ;
        this.ground = pLevel.getLandingCoordsY() + 8;
        setX(x + 8) ;
        setY(10 + y) ;
        setAnimation(SpriteDirection.ARM, 0) ;
    }

    public void loadAnimation() {
        if (getDirection() == CRASH)
            setAnimation(SpriteDirection.ARM, 1) ;
    }

    public int action() throws DestroyedException {
 //       System.out.println("Tank Arm X: "+ getX());
 //       System.out.println("Tank Arm Y: "+ getY());
        if (explodeCount > 0 && getDirection() == CRASH) {
                explode();
            loadAnimation();
            return -1 ;
        }

        switch (sd) {
            case Tank.DIR_RIGHT:
                setY(getLevel().getStartY()- 10 + (int)(((Math.sin(0.15-(getX() - ox)/(25f*SPRITE_SCALE))) *18f*SPRITE_SCALE))) ;
                setX(getX()+20);
                break ;
            case Tank.DIR_LEFT:
                setX(getX()-20) ;
                setY(getLevel().getStartY()- 10 +(int)(((Math.sin(0.15-(ox-getX())/(25f*SPRITE_SCALE))) *18f*SPRITE_SCALE))) ;
                break ;
        }
        if (hasCollision()) {
                explode() ;
            if (this.isNear(getLevel().getHelicopter().getX(), getLevel().getHelicopter().getY())) {
                getLevel().getHelicopter().explode();
            }
            loadAnimation();
        }
        return -1 ;
    }

    public boolean hasCollision() {
        return (getX() > ox + C64Theme.SCREEN_WIDTH)
                || (getX() < ox - C64Theme.SCREEN_WIDTH)
                || (getY() < 0)
                || (getY() >= ground)
                || (this.isNear(getLevel().getHelicopter().getX(), getLevel().getHelicopter().getY()));
    }
}
