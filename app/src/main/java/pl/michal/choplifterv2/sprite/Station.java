package pl.michal.choplifterv2.sprite;

public class Station extends AbstractSprite{

    public Station(int x, int y) {
        setX(x) ;
        setY(y) ;
     //   setIcon(convertAsIs(STATION1)) ;
    }

    public boolean canEnter(int x) {
        return (x > (getX()+85)-4)
                &&  (x < (getX()+85)+4) ;
    }
}

