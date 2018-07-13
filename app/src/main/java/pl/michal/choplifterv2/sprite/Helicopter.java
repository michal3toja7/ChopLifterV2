package pl.michal.choplifterv2.sprite;

import java.util.Arrays;

/**
 * Created by micha on 20.03.2018.
 */

public class Helicopter  extends  AbstractAnimatedSprite{
    public enum HelicopterDirection {
        CENTER("helicopter_center",new String[] {"", "_land", "_left", "_right"}, 3),
        CRASH("helicopter_crash",new String[] {""}, 4),
        LEFT("helicopter_left", new String[] {"", "_back", "_down", "_halfback", "_halfdown", "_land"},3),
        RIGHT("helicopter_right", new String[] {"", "_back", "_down", "_halfback", "_halfdown", "_land"},3)

        private String spriteSide;
        private String direction[];
        private int numberFrames;
        HelicopterDirection(String spriteSide, String direction[], int numberFrames) {
            this.spriteSide = spriteSide;
            this.direction = direction;
            this.numberFrames = numberFrames;
        }
        public String getSpriteSide(){
            return spriteSide;
        }
        public String[] getDirection(){
            return direction;
        }
        public int getNumberFrames(){
            return numberFrames;
        }
    }


    public Helicopter(){


    }



    public void loadAnimation() {

        switch (getDirection()) {
            case DIR_RIGHT:
                if (Math.abs(stepsX) <= ANGLEPOINT)
                    setAnimation(helRight) ;
                else if (stepsX > 0) {
                    if (Math.abs(stepsX) < MAXANGLEPOINT)
                        setAnimation(helRightHalfDown) ;
                    else
                        setAnimation(helRightDown) ;
                } else if (Math.abs(stepsX) < MAXANGLEPOINT)
                    setAnimation(helRightHalfBack) ;
                else
                    setAnimation(helRightBack) ;
                break ;
            case DIR_LEFT:
                if (Math.abs(stepsX) <= ANGLEPOINT)
                    setAnimation(helLeft) ;
                else if (stepsX < 0) {
                    if (Math.abs(stepsX) < MAXANGLEPOINT)
                        setAnimation(helLeftHalfDown) ;
                    else
                        setAnimation(helLeftDown) ;
                } else if (Math.abs(stepsX) < MAXANGLEPOINT)
                    setAnimation(helLeftHalfBack) ;
                else
                    setAnimation(helLeftBack) ;
                break ;
            case DIR_CENTER:
                if (Math.sqrt(stepsX*stepsX) <= ANGLEPOINT) {
                    setAnimation(helCenter) ;
                } else if (stepsX > 0) {
                    setAnimation(helCenterRight) ;
                } else {
                    setAnimation(helCenterLeft) ;
                } break ;
            case DIR_CENTER_L:
                setAnimation(helTurnLeft) ;
                break ;
            case DIR_CENTER_R:
                setAnimation(helTurnRight) ;
                break ;
            case CRASH:
                setAnimation(helCrash) ;
                break ;
            case DIR_RIGHT_LAND:
                setAnimation(helRightLand) ;
                setDirection(DIR_RIGHT) ;
                break ;
            case DIR_LEFT_LAND:
                setAnimation(helLeftLand) ;
                setDirection(DIR_LEFT) ;
                break ;
            case DIR_CENTER_LAND:
                setAnimation(helCenterLand) ;
                setDirection(DIR_CENTER) ;
                break ;
        }
    }
}