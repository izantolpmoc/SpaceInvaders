package Entities;

import resources.Constants;

import javax.swing.*;

public class Spaceship extends Entity {
    public Spaceship() {
        super.xPos = Constants.X_POS_INIT_SPACESHIP;
        super.yPos = Constants.Y_POS_SPACESHIP;
        super.width = Constants.SPACESHIP_WIDTH;
        super.height = Constants.SPACESHIP_HEIGHT;
        super.dx = 0;
        super.dy = 0;

        super.imagePath = "/images/space-ship.png";

        super.icon = new ImageIcon(getClass().getResource(super.imagePath));
        super.image = this.icon.getImage();
        this.isAlive = true;
    }

    public int spaceshipMovement() {
        if(this.dx < 0){if(this.xPos > Constants.LEFT_LIMIT_SPACESHIP) {this.xPos = this.xPos + this.dx;}
        }else if(dx > 0) {if(this.xPos + this.dx < Constants.RIGHT_LIMIT_SPACESHIP) {this.xPos = this.xPos + this.dx;}}
        return this.xPos;
    }
}
