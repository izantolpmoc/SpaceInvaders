package resources;

import game.SpaceInvaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){SpaceInvaders.background.spaceship.setDx(Constants.DX_SPACESHIP);}
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){SpaceInvaders.background.spaceship.setDx(-Constants.DX_SPACESHIP);}
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            //shoots only after previous one disappeared
            if(SpaceInvaders.background.shoot.isShooting() == false) {
                SpaceInvaders.background.shoot.setyPos(Constants.Y_POS_SPACESHIP - Constants.SHOOT_HEIGHT);
                SpaceInvaders.background.shoot.setxPos(SpaceInvaders.background.spaceship.getxPos() + Constants.SPACESHIP_WIDTH/2 - 1);
                SpaceInvaders.background.shoot.setShooting(true);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        SpaceInvaders.background.spaceship.setDx(0);
    }
}
