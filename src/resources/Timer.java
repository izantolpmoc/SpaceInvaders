package resources;

import game.SpaceInvaders;

public class Timer implements Runnable{
    private final int BREAK = 10;
    public static int turnsCount = 0;

    @Override
    public void run() {
        while (true) {
            SpaceInvaders.background.repaint();
            try {Thread.sleep(BREAK);} // 10ms break
            catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
