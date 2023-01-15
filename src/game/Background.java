package game;

import Entities.CubeGroup;
import Entities.Shoot;
import Entities.Spaceship;
import com.jogamp.opengl.awt.GLCanvas;
import resources.Constants;
import resources.Keyboard;
import resources.Timer;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    public Spaceship spaceship = new Spaceship();
    public CubeGroup cubeGroup = new CubeGroup();
    public Shoot shoot = new Shoot();
    public int nbCubesDestroyed = 0;

    public Background(){
        super();

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new Keyboard());

        Thread screenTimer = new Thread(new Timer());
        screenTimer.start();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        //background color
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        //bottom green line
        graphics2D.setColor(Color.GREEN);
        graphics2D.fillRect(30, 530, 535, 5);

        //spaceship
        graphics2D.drawImage(this.spaceship.getImage(), this.spaceship.spaceshipMovement(), this.spaceship.getyPos(), null);

        //spaceship projectile
        this.shoot.drawShoot(graphics2D);

        //checks position of cube vs projectile
        this.cubeGroup.cubeDestroyedByShot(this.shoot);

        //score
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 20));
        graphics2D.drawString("Remaining cubes : " + (Constants.NUMBER_CUBES - nbCubesDestroyed) + " / " + Constants.NUMBER_CUBES, 350, 25);

        //End game message
        if(this.nbCubesDestroyed == Constants.NUMBER_CUBES) {
            graphics2D.setFont(new Font("Arial", Font.PLAIN, 80));
            graphics2D.drawString("VICTORY !!", 50, 100);
        }

        //updates position of cubes
        for(int column=0; column<10; column++) {
            for (int line = 0; line < 5; line++) {
                if (cubeGroup.tabCubes[line][column] != null) {
                    GLCanvas canvas = cubeGroup.tabCubes[line][column];
                    canvas.setLocation(cubeGroup.tabCubes[line][column].xPos, cubeGroup.tabCubes[line][column].yPos);
                }
            }
        }

        //removes cubes from frame when destroyed
        for(int column=0; column<10; column++) {
            for (int line = 0; line < 5; line++) {
                if (cubeGroup.tabCubes[line][column] != null && cubeGroup.tabCubes[line][column].isAlive == false) {
                    GLCanvas canvas = cubeGroup.tabCubes[line][column];
                    this.remove(canvas);
                }
            }
        }
    }
}
