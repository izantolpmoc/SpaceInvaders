package resources;

public abstract class Constants {

    //Window size
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_MARGIN = 50;


    //Spaceship
    public static final int SPACESHIP_WIDTH = 39;
    public static final int SPACESHIP_HEIGHT = 24;
    public static final int X_POS_INIT_SPACESHIP = (WINDOW_WIDTH - SPACESHIP_WIDTH) /2;
    public static final int Y_POS_SPACESHIP = 490;
    public static final int DX_SPACESHIP = 5;
    public final static int LEFT_LIMIT_SPACESHIP = 60;
    public final static int RIGHT_LIMIT_SPACESHIP = 500;

    //Evil cube
    public static final int CUBE_WIDTH = 33;
    public static final int CUBE_HEIGHT = 25;
    public final static int ALT_INIT_CUBE = 120;
    public final static int X_POS_INIT_CUBE = 29 + WINDOW_MARGIN;
    public final static int CUBE_ROW_SPACE = 40;
    public final static int CUBE_COLUMN_SPACE = 10;
    public final static int DX_CUBE = 2;
    public final static int CUBE_SPEED = 1;
    public static final  int NUMBER_CUBES = 50;

    //spaceship - shoot
    public final static int SHOOT_WIDTH = 3;
    public final static int SHOOT_HEIGHT = 13;
    public final static int DY_SPACESHIP_SHOOT = 7;
}
