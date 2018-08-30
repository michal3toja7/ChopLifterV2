package pl.michal.choplifterv2.sprite;

public class Fence extends AbstractAnimatedSprite {

    public Fence(int x, int y) {
        setX(x) ;
        setY(y) ;
        setAnimation(SpriteDirection.FENCE, 0);
    }
    
}
