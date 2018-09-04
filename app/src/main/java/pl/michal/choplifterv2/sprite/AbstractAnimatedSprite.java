package pl.michal.choplifterv2.sprite;

import android.graphics.Canvas;
import android.support.graphics.drawable.VectorDrawableCompat;

import pl.michal.choplifterv2.level.DestroyedException;
import pl.michal.choplifterv2.level.InterfaceLevel;

import static pl.michal.choplifterv2.ChopLifterActivity.getContext;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;
import static pl.michal.choplifterv2.c64.C64Theme.SPRITE_SCALE;

/**
 * Created by micha on 16.04.2018.
 */

public abstract class AbstractAnimatedSprite extends AbstractSprite implements InterfaceAnimatedSprite, InterfaceDestroyable {
    private static int scrollX =0;
    private static int minVisibleX =0;
    private static int maxVisibleX = SCREEN_WIDTH;
    private VectorDrawableCompat mMyVectorDrawable;
    private SpriteDirection actualSpriteDirection;
    private int actualIdDirection;
    public final static int CRASH = -1;
    private InterfaceLevel level;
    private int ticker = 0;
    private int direction;
    private String vectorImageName = null;
    private int frame = 1;
    int resID = 0;
    public int explodeCount =0; //zmienna pomagająca wyświetlić eksplozję
    private boolean invisible = false;


    public enum SpriteDirection {
        HEL_CENTER("helicopter_center", new String[]{"", "_land", "_left", "_right"}, 3),
        HEL_CRASH("helicopter_crash", new String[]{""}, 4),
        HEL_LEFT("helicopter_left", new String[]{"", "_back", "_down", "_halfback", "_halfdown", "_land"}, 3),
        HEL_RIGHT("helicopter_right", new String[]{"", "_back", "_down", "_halfback", "_halfdown", "_land"}, 3),
        HEL_ROTATE("helicopter_rotate", new String[]{"_left", "_right"}, 0),
        HUMAN("human", new String[]{"_center", "_left_", "_right"}, 3),
        TANK("tank", new String[]{"_center", "_left", "_right", "_crash"}, 2),
        STATION("station", new String[]{""}, 1),
        HOUSE("house", new String[]{"", "_crash"}, 1),
        ARM("arm", new String[]{"", "_crash"}, 2),
        FENCE("fence", new String[]{""}, 1);



        private String spriteSide;
        private String directionName[];
        private int numberFrames;

        SpriteDirection(String spriteSide, String direction[], int numberFrames) {
            this.spriteSide = spriteSide;
            this.directionName = direction;
            this.numberFrames = numberFrames;
        }

        public String getSpriteSide() {
            return spriteSide;
        }

        public String[] getDirectionName() {
            return directionName;
        }

        public int getNumberFrames() {
            return numberFrames;
        }
    }

    public final int heartBeat() throws DestroyedException {
        if (vectorImageName != null) {
            //  ticker = ++ticker % 16 ;
            //  refreshAnimation();
            //      setIcon(vectorImageName[ticker % vectorImageName.length]) ;
        }
       // refreshAnimation();
        return action();
    }

    public final void setAnimation(SpriteDirection spriteDirection, int IdDirection) {
        actualSpriteDirection = spriteDirection;
        actualIdDirection = IdDirection;

        StringBuilder builderVectorImage = new StringBuilder();
        String[] Direction = spriteDirection.getDirectionName();

        builderVectorImage.append(spriteDirection.getSpriteSide());
        builderVectorImage.append(Direction[IdDirection]);
        if (spriteDirection.getNumberFrames() != 0) {
            if (frame <= 0) {
                frame = 1;
            }
            if (frame <= spriteDirection.getNumberFrames()) {
                builderVectorImage.append(frame);
            } else if (frame > spriteDirection.getNumberFrames()) {
                frame = 1;
                builderVectorImage.append(frame);
            }

        }

        vectorImageName = builderVectorImage.toString();

        frame++;
        if (frame > spriteDirection.getNumberFrames() || frame < 1) {
            frame = 1;
        }
    }


    public String getVectorImageName() {
        return vectorImageName;
    }

    public void refreshAnimation() {
            setAnimation(actualSpriteDirection, actualIdDirection);

    }

    public boolean isNear(int x, int y) {
            return (Math.abs((double) (x - getX())) < (10 * SPRITE_SCALE))
                    && Math.abs((double) (y - getY())) < (10 * SPRITE_SCALE);
    }

    public final void explode() throws DestroyedException {
        if(explodeCount >=30){
            explodeCount = 0;
            remove();
        }
        else if(explodeCount <= 0 ){
                setDirection(CRASH);
            if (this instanceof Arm || this instanceof TankArm)
                getLevel().manageCollision(getX(), getY());
            explodeCount ++;
        }
        else if (explodeCount>0 && explodeCount <30){
            explodeCount ++;
        }
    }

    public synchronized void remove() {
        level.remove(this);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


    public void draw(Canvas canvas) {
        //     System.out.println(getVectorImageName());

        if (this.getX() >= minVisibleX && this.getX() <= maxVisibleX) {
            if(this instanceof Human && invisible)
                return;
            resID = getContext().getResources().getIdentifier(this.getVectorImageName(), "drawable", getContext().getPackageName());
            mMyVectorDrawable = VectorDrawableCompat.create(getContext().getResources(), resID, null);


            mMyVectorDrawable.setBounds(this.getX() + scrollX, this.getY(), (this.getX() + mMyVectorDrawable.getMinimumWidth() * SPRITE_SCALE) + scrollX, this.getY() + mMyVectorDrawable.getMinimumHeight() * SPRITE_SCALE);
            mMyVectorDrawable.draw(canvas);

            mMyVectorDrawable = null;
        }

    }


    public InterfaceLevel getLevel() {
        return level;
    }

    public void setLevel(InterfaceLevel level) {
        this.level = level;
    }
    public void setInvisible(boolean invisible){
        this.invisible = invisible;
    }

    public void setScrollX(int scrollX){
        this.scrollX = scrollX;
        if (this instanceof Helicopter){
            minVisibleX = -scrollX - (SCREEN_WIDTH / 3);
            maxVisibleX = SCREEN_WIDTH - scrollX;

        }
    }
    public int getScrollX(){
        return  scrollX;
    }

}
