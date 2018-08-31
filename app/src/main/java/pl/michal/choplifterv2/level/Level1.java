package pl.michal.choplifterv2.level;

import pl.michal.choplifterv2.sprite.Helicopter;
import pl.michal.choplifterv2.sprite.House;
import pl.michal.choplifterv2.sprite.Human;
import pl.michal.choplifterv2.sprite.Mountain;

import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_HEIGHT;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;

public class Level1 extends AbstractLevel {
    public Level1() {

 //       add(new Mountain(845, 172)) ;
 //       add(new Mountain(590, 172)) ;
 //       add(new Mountain(400, 172)) ;
 //       add(new Mountain(200, 172)) ;
 //       add(new Mountain(0, 172)) ;

        add(new House(250, 2*(SCREEN_HEIGHT/3), this)) ;
       add(new House(1250, 2*(SCREEN_HEIGHT/3), this)) ;
        House openHouse = new House(450, 2*(SCREEN_WIDTH/3), this) ;
        add(openHouse) ;
        openHouse.remove() ; /// Open (not remove) house1

  //      for (int i=0; i < 2; ++i)
   //         add(new Tank(this)) ;
    }

    // ------------------------------------------------------------------------

    @Override
    public int heartBeat() {
        return 0;
    }

    /**
     * @implements {@link InterfaceLevel#getStartX()}
     */
    public final int getStartX() {
        return 400 ;
    }

    /**
     * @implements {@link InterfaceLevel#getStartX()}
     */
    public final int getStartY() {
        return 900 ;
    }
}

