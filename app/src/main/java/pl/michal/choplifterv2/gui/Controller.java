package pl.michal.choplifterv2.gui;

import pl.michal.choplifterv2.ChopLifterPanel;
import pl.michal.choplifterv2.sprite.Helicopter;

public class Controller {
    ChopLifterPanel chopLifterPanel;

Controller(){


}







   public void move(int direction){
        if(direction == 0){
            chopLifterPanel.battlefield.helicopter.moveLeft();
        }
        if(direction == 2){
            chopLifterPanel.battlefield.helicopter.moveUp();
        }
        if(direction == 3){
            chopLifterPanel.battlefield.helicopter.moveRight();
        }
        if(direction == 6){
            chopLifterPanel.battlefield.helicopter.moveDown();
        }

    }









}
