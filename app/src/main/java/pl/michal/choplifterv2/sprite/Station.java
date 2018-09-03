package pl.michal.choplifterv2.sprite;



public class Station extends  AbstractAnimatedSprite{

    public Station(int x, int y) {
        setX(x) ;
        setY(y) ;
        setAnimation(SpriteDirection.STATION, 0);

    }

    public boolean canEnter(int x) {
        return (x > (getX()+85)-4)
                &&  (x < (getX()+85)+4) ;
    }

    @Override
    public int action() {
        return 0;
    }

    @Override
    public void loadAnimation() {

    }
}

