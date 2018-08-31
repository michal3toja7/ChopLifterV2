package pl.michal.choplifterv2.sprite;

import pl.michal.choplifterv2.level.InterfaceLevel;

public class House extends AbstractAnimatedSprite {

    public final static int CLOSED = 1 ;

    public House(int x, int y, InterfaceLevel pLevel) {
        setX(x) ;
        setY(y) ;
        setDirection(CLOSED) ;
        setLevel(pLevel) ;
        loadAnimation() ;

    }

    public void loadAnimation() {
        switch (getDirection()) {
            case CLOSED:
                setAnimation(SpriteDirection.HOUSE, 0);
                break ;
            case CRASH:
                setAnimation(SpriteDirection.HOUSE, 1);
                break ;
        }
    }

    public int action() {
        if (getDirection() == CLOSED) return -1 ;

        return -1 ;
    }


    public boolean isNear(int x, int y) {
        return (Math.abs((double) (x - getX())) < 10)
                && Math.abs((double) (y - getY())) < 12;
    }

    public void remove() {
        // House will not be removed
        setDirection(CRASH) ;
        loadAnimation() ;
       for (int i=0; i < 10; ++i)
            getLevel().add(new Human(getLevel(), this)) ;
    }


    public String toString() {
        return "House" ;
    }
}
