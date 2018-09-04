package pl.michal.choplifterv2.sprite;


import static pl.michal.choplifterv2.c64.C64Theme.SPRITE_SCALE;

public class Station extends  AbstractAnimatedSprite{

    public Station(int x, int y) {
        setX(x) ;
        setY(y) ;
        setAnimation(SpriteDirection.STATION, 0);

    }

    public boolean canEnter(int x) {
        return (x > (getX()+85*SPRITE_SCALE)-4)
                &&  (x < (getX()+85*SPRITE_SCALE)+4) ;
    }

    @Override
    public int action() {
        return 0;
    }

    @Override
    public void loadAnimation() {

    }
}

