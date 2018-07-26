package pl.michal.choplifterv2.sprite;

/**
 * Created by micha on 16.04.2018.
 */

public abstract class AbstractAnimatedSprite extends AbstractSprite implements InterfaceSprite,InterfaceAnimatedSprite {
    private Helicopter.SpriteDirection actualSpriteDirection;
    private int actualIdDirection;
    public final static int CRASH = -1 ;
  //  private ILevel level ;
    private int ticker = 0 ;
    private int direction;
    private String vectorImageName = null ;
    private static int frame =1;


    public enum SpriteDirection {
        HEL_CENTER("helicopter_center",new String[] {"", "_land", "_left", "_right"}, 3),
        HEL_CRASH("helicopter_crash",new String[] {""}, 4),
        HEL_LEFT("helicopter_left", new String[] {"", "_back", "_down", "_halfback", "_halfdown", "_land"},3),
        HEL_RIGHT("helicopter_right", new String[] {"", "_back", "_down", "_halfback", "_halfdown", "_land"},3),
        HEL_ROTATE("helicopter_rotate", new String[] {"_left", "_right"},0),
        HUM_CENTER("human_center", new String[] {""},2),
        HUM_LEFT("human_left_", new String[] {""},3),
        HUM_RIGHT("human_right", new String[] {""},3),
        TAN_CENTER("tank_center", new String[] {""},2),
        TAN_LEFT("tank_left", new String[] {""},2),
        TAN_RIGHT("tank_right", new String[] {""},2),
        TAN_CRASH("tank_center", new String[] {""},2),;


        private String spriteSide;
        private String directionName[];
        private int numberFrames;
        SpriteDirection(String spriteSide, String direction[], int numberFrames) {
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

/*    public final int heartBeat() throws DestroyedException {
        if (vectorImageName != null) {
            ticker = ++ticker % 16 ;
      //      setIcon(vectorImageName[ticker % vectorImageName.length]) ;
        }
        return action() ;
    }
*/
    public final void setAnimation(SpriteDirection spriteDirection, int IdDirection) {
            actualSpriteDirection = spriteDirection;
            actualIdDirection = IdDirection;
            StringBuilder builderVectorImage = new StringBuilder();
            String [] Direction = spriteDirection.getDirectionName();
            builderVectorImage.append(spriteDirection.getSpriteSide());
            builderVectorImage.append(Direction[IdDirection]);
            if (spriteDirection.getNumberFrames() != 0){
                if (frame <= spriteDirection.getNumberFrames() && frame > 0){
                    builderVectorImage.append(frame);
                }
            }
            vectorImageName = builderVectorImage.toString();

            frame ++;
            if (frame > spriteDirection.getNumberFrames() || frame < 1){
                frame = 1;
            }
    }


    public String getVectorImageName() {
        return vectorImageName;
    }
    public void refreshAnimation(){
        setAnimation(actualSpriteDirection,actualIdDirection);
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
