package game;

import Entities.MovingCube;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import resources.Constants;

import javax.swing.*;

public class SpaceInvaders {

    public static Background background;

    public static void main(String[] args) {

        FPSAnimator fpsAnimator = new FPSAnimator(60, true);

        //Window configuration
        JFrame window = new JFrame("Space Invaders");
        window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        window.setResizable(false);
        //opens window at the center of the screen
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //prevents window from disappearing behind another one
        window.setAlwaysOnTop(true);

        //adding background to window
        background = new Background();

        //adds cubes to background
        MovingCube[][] cubes = background.cubeGroup.tabCubes;
        for(int column=0; column<10; column++) {
            for(int line=0; line<5; line++) {
                if(cubes[line][column] != null) {
                    GLCanvas canvas = cubes[line][column];
                    fpsAnimator.add(canvas);
                    background.add(canvas);
                    canvas.setBounds(cubes[line][column].xPos, cubes[line][column].yPos, cubes[line][column].width, cubes[line][column].height);
                }
            }
        }

        window.setContentPane(background);

        background.setLayout(null);

        window.setVisible(true);
        fpsAnimator.start();

    }
}
