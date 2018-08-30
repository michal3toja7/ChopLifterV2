package pl.michal.choplifterv2.level;

import pl.michal.choplifterv2.sprite.Helicopter;
import pl.michal.choplifterv2.sprite.House;
import pl.michal.choplifterv2.sprite.Mountain;

public class Level1 extends AbstractLevel {
    public Level1() {

 //       add(new Mountain(845, 172)) ;
 //       add(new Mountain(590, 172)) ;
 //       add(new Mountain(400, 172)) ;
 //       add(new Mountain(200, 172)) ;
 //       add(new Mountain(0, 172)) ;

        add(new House(250, 720, this)) ;
       add(new House(1250, 720, this)) ;
      //  House openHouse = new House(450, 165, this) ;
     //   add(openHouse) ;
     //   openHouse.remove() ; /// Open (not remove) house1

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
        return 400 ;
    }
}

