package Entities;

import resources.Constants;

import javax.swing.*;
import java.awt.*;

public class Shoot extends Entity {
    private boolean isShooting = false;

    public Shoot(){
        super.xPos = 0;
        super.yPos = Constants.Y_POS_SPACESHIP - Constants.SHOOT_HEIGHT;
        super.width = Constants.SHOOT_WIDTH;
        super.height = Constants.SHOOT_HEIGHT;
        super.dx = 0;
        super.dy = Constants.DY_SPACESHIP_SHOOT;

        super.imagePath = "/images/shoot.png";

        super.icon = new ImageIcon(getClass().getResource(super.imagePath));
        super.image = this.icon.getImage();
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public int shootMovement() {
        if(this.isShooting == true) {
            if(this.yPos > 0) {this.yPos = this.yPos - Constants.DY_SPACESHIP_SHOOT;}
            else {this.isShooting = false;}
        }
        return yPos;
    }

    public void drawShoot(Graphics g) {
        if(this.isShooting == true) {
            g.drawImage(this.image, this.xPos, this.shootMovement(), null);}
    }

    public boolean destroyCube(MovingCube cube) {
        if(this.yPos < cube.yPos + cube.height
                && this.yPos + this.height > cube.yPos
                && this.xPos + this.width > cube.xPos
                && this.xPos < cube.xPos + cube.width
                && cube.isAlive == true){
            return true;
        }
        else{return false;}
    }
}
