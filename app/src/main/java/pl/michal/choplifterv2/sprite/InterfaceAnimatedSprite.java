package pl.michal.choplifterv2.sprite;

import pl.michal.choplifterv2.level.DestroyedException;
import pl.michal.choplifterv2.level.InterfaceLevel;

public interface InterfaceAnimatedSprite {

        /** Heartbeat called from game */
       int heartBeat() throws DestroyedException;
        /** Action to be implemented i.e. movement */
        int action() throws DestroyedException;

        /** Load animation to icon i.e. dependant on direction */
        void loadAnimation() ;

        /** For determination whether this is near to another object */
        boolean isNear(int x, int y) ;
        /** Start explosion */
        void explode() throws DestroyedException ;

        void remove();


        /* Getter / Setter */
        /** Getter */
        InterfaceLevel getLevel() ;
        /** Setter */
        void setLevel(InterfaceLevel level) ;

        void refreshAnimation();


}
