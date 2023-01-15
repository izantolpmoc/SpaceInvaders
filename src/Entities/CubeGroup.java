package Entities;


import game.SpaceInvaders;
import resources.Constants;
import resources.Timer;

public class CubeGroup {
    public MovingCube tabCubes[][] = new MovingCube[5][10];
    public int speed = Constants.CUBE_SPEED;
    private boolean isMovingRight;

    public CubeGroup() {

        this.initCubesTable();
        this.isMovingRight = false;
    }

    private void initCubesTable() {
        for(int column=0; column<10; column++) {
            this.tabCubes[0][column] = new MovingCube(Constants.X_POS_INIT_CUBE + (Constants.CUBE_WIDTH + Constants.CUBE_COLUMN_SPACE) * column,
                    Constants.ALT_INIT_CUBE);
            for(int line = 1; line <5; line++) {
                this.tabCubes[line][column] = new MovingCube(Constants.X_POS_INIT_CUBE + (Constants.CUBE_WIDTH + Constants.CUBE_COLUMN_SPACE) *
                        column, Constants.ALT_INIT_CUBE + Constants.CUBE_ROW_SPACE * line);
            }
        }
    }

    public void cubeDestroyedByShot(Shoot shoot) {
        //updates cubes movement
        if(Timer.turnsCount % (100 - 10 * this.speed) == 0) {this.moveCubes();}

        for(int column=0; column<10; column++) {
            for(int lign=0; lign<5; lign++) {
                if(this.tabCubes[lign][column] != null) {
                    if(shoot.destroyCube(this.tabCubes[lign][column]) == true) {
                        this.tabCubes[lign][column].isAlive = false;
                        //stops projectile
                        shoot.yPos = -1;
                        SpaceInvaders.background.nbCubesDestroyed++;
                    }
                }
            }
        }
    }
    private boolean reachedLeftBorder() {
        boolean response = false;
        for(int column=0; column<10; column++) {
            for(int lign=0; lign<5; lign++) {
                if(this.tabCubes[lign][column] != null) {
                    if(this.tabCubes[lign][column].xPos < Constants.WINDOW_MARGIN){
                        response = true;
                        break;
                    }
                }
            }
        }
        return response;
    }

    private boolean reachedRightBorder() {
        boolean response = false;
        for(int column=0; column<10; column++) {
            for(int lign=0; lign<5; lign++) {
                if(this.tabCubes[lign][column] != null) {
                    if(this.tabCubes[lign][column].xPos > Constants.WINDOW_WIDTH - Constants.CUBE_WIDTH - Constants.DX_CUBE - Constants.WINDOW_MARGIN) {
                        response = true;
                        break;
                    }
                }
            }
        }
        return response;
    }

    public void changeCubeDirection() {
        if(this.reachedRightBorder() == true) {
            this.isMovingRight = false;
            if(this.speed < 9) {this.speed++;}
        } else {
            if(this.reachedLeftBorder() == true) {
                this.isMovingRight = true;
                if(this.speed < 9) {this.speed++;}
            }
        }
    }

    public void moveCubes() {
        if(this.isMovingRight == true) { // If direction is set to right, moves them to the right
            for(int column=0; column<10; column++) {
                for(int line=0; line<5; line++) {
                    if(this.tabCubes[line][column] != null) {
                        this.tabCubes[line][column].xPos = this.tabCubes[line][column].xPos + Constants.DX_CUBE;
                    }
                }
            }
        }else{ // Moves cubes to the left
            for(int column=0; column<10; column++) {
                for(int line=0; line<5; line++) {
                    if(this.tabCubes[line][column] != null) {
                        this.tabCubes[line][column].xPos = this.tabCubes[line][column].xPos - Constants.DX_CUBE;
                    }
                }
            }
        }

        this.changeCubeDirection();
    }
}
