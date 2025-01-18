package model;

import java.awt.Color;
import java.time.Clock;
import java.util.Random;


public class Fire extends NonFallingParticle {
    public static final Color COLOR = new Color(255,206,0);
    private static final Color COLOR1 = new Color(255,154,0);
    private static final Color COLOR2 = new Color(255,90,0);
    public static final Color COLOR3 = new Color(255,0,0);
    private static final int COLORCHANCE = 5;
    private static final int DIECHANCE = 10;
    private long lifeTime = 500;
    private long lifeStart;
    private Random rand;
    private Clock clock;

    public Fire(int x, int y) {
        super(COLOR, x, y, true);
        clock = Clock.systemDefaultZone();
        lifeStart = clock.millis();
        rand = new Random();
    }

    @Override
    public void update() {
        this.searchForWater();
        this.searchForFlammable();
        long currLife = clock.millis() - lifeStart;
        // changes color depending on how long it has lived
        float duration = (float) currLife/lifeTime;
        if (duration < 0.50 && duration > 0.25 && this.color != COLOR1) {
            if (rand.nextInt(COLORCHANCE) == 0) {
                this.color = COLOR1;
            }
        } else if (duration < 0.75 && this.color != COLOR2) {
            if (rand.nextInt(COLORCHANCE) == 0) {
                this.color = COLOR2;
            }
        } else if (duration < 1 && this.color != COLOR3) {
            if (rand.nextInt(COLORCHANCE) == 0) {
                this.color = COLOR3;
            }
        } else if (rand.nextInt(DIECHANCE) == 0) {
            extinguish(new Smoke(this.x, this.y));
            return;
        }
        hasUpdated = true;
    }

    public void searchForWater() {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (screen.isWater(i, j)) {
                    extinguish(new Steam(this.x, this.y));
                    return;
                }
            }
        }
    }

    public void searchForFlammable() {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (screen.isFlammable(i, j)) {
                    if (rand.nextInt(screen.ignitionChance(i, j)) == 0) 
                        screen.changeValue(i, j, new Fire(i, j));
                    lifeTime += 50;
                    return;
                }
            }
        }
    }

    public void extinguish(GasParticle gas) {
        screen.changeValue(this.x, this.y, gas);
    }
}
