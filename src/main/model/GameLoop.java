package model;

import java.time.Clock;

import ui.MainPanel;

public class GameLoop {
    private static final int MAX_FRAMESKIP = 10;
    private static final int TICKS_PER_SECOND = 60;
    private static final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private long currTick;
    private Clock clock;
    private MainPanel mp;

    public GameLoop(MainPanel mp) {
        clock = Clock.systemDefaultZone();
        currTick = clock.millis();
        this.mp = mp;
        this.gameLoop();
    }


    private void gameLoop() {
        Boolean gameIsRunning = true;
        int loops;

        while (gameIsRunning) {
            loops = 0;
            
            while (clock.millis() > currTick && loops < MAX_FRAMESKIP) {
                mp.update();
                currTick += SKIP_TICKS;
                loops++;
            }
            mp.repaint();
        }
    }
}
