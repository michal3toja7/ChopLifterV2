package pl.michal.choplifterv2.level;

import pl.michal.choplifterv2.sprite.Helicopter;
import pl.michal.choplifterv2.sprite.House;
import pl.michal.choplifterv2.sprite.Human;
import pl.michal.choplifterv2.sprite.Mountain;
import pl.michal.choplifterv2.sprite.Tank;

import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_HEIGHT;
import static pl.michal.choplifterv2.c64.C64Theme.SCREEN_WIDTH;
import static pl.michal.choplifterv2.c64.C64Theme.SPRITE_SCALE;

public class Level1 extends AbstractLevel {
    public Level1() {



        for (int i = SCREEN_WIDTH; i<= this.MAXWIDTH; i=i+800 )
            add(new Mountain(i, getStartY() + (11 * SPRITE_SCALE)));
        

        add(new House(5000,  getStartY() + (9*SPRITE_SCALE), this)) ;
       add(new House(4000, getStartY() + (9*SPRITE_SCALE), this)) ;
        House openHouse = new House(3000, getStartY() + (9*SPRITE_SCALE), this) ;
        add(openHouse) ;
        openHouse.remove() ; /// Open (not remove) house1

       for (int i=0; i < 2; ++i)
            add(new Tank(this)) ;

       setMaxSaved(30);
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
        return 2*(SCREEN_HEIGHT/3) - (19 * SPRITE_SCALE) ;
    }
}

