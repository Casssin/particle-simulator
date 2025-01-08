package model;

import java.awt.Color;
import java.time.Clock;
import java.util.Random;

import ui.MainPanel;

public class Smoke extends GasParticle {
    private static final int DIECHANCE = 100;
    private long lifeTime = 1000;
    private long lifeStart;
    private Clock clock;
    private Random rand;
    
    public Smoke(int x, int y) {
        super(Color.black, x, y, false);
        clock = Clock.systemDefaultZone();
        lifeStart = clock.millis();  
        rand = new Random();
    }

    @Override
    public void update() {
        long currLife = clock.millis() - lifeStart;
        if (currLife >= lifeTime && rand.nextInt(DIECHANCE) == 0) {
            screen.changeValue(this.x, this.y, new Air(this.x, this.y));
        }
        if (screen.isAir(x, y - 1)) {
            fallUp(x, y);
        } else if (x - 1 >= 0 && screen.isAir(x - 1, y - 1)) {
            this.moveTo(x - 1, y - 1);
        } else if (x + 1 < MainPanel.ARR_WIDTH && screen.isAir(x + 1, y - 1)) {
            this.moveTo(x + 1, y - 1);
        } 
        else {
            velocityY = 0;
        }
        hasUpdated = true;
    }
}
