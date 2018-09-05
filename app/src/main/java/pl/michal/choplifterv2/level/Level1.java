package pl.michal.choplifterv2.level;

import pl.michal.choplifterv2.sprite.Helicopter;
import pl.michal.choplifterv2.sprite.House;
import pl.michal.choplifterv2.sprite.Human;
import pl.michal.choplifterv2.sprite.Mountain;
import pl.michal.choplifterv2.sprite.Tank;

import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_HEIGHT;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;

public class Level1 extends AbstractLevel {
    public Level1() {

 //       add(new Mountain(845, 172)) ;
 //       add(new Mountain(590, 172)) ;
 //       add(new Mountain(400, 172)) ;
 //       add(new Mountain(200, 172)) ;
 //       add(new Mountain(0, 172)) ;

        add(new House(4000, 2*(SCREEN_HEIGHT/3), this)) ;
       add(new House(3000, 2*(SCREEN_HEIGHT/3), this)) ;
        House openHouse = new House(1600, 2*(SCREEN_HEIGHT/3), this) ;
        add(openHouse) ;
        openHouse.remove() ; /// Open (not remove) house1

     //  for (int i=0; i < 1; ++i)
            add(new Tank(this)) ;
    }

    // ------------------------------------------------------------------------



    /**
     * @implements {@link InterfaceLevel#getStartX()}
     */
    public final int getStartX() { return  SCREEN_WIDTH/6 ; }

    /**
     * @implements {@link InterfaceLevel#getStartX()}
     */
    public final int getStartY() {
        return 2*(SCREEN_HEIGHT/3) ;
    }
}

