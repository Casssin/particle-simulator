package model;

import java.awt.Color;

import java.time.Clock;

import ui.MainPanel;

public class Lava extends FallingLiquid {
    private static final int DISPERSION_RATE = 10 / MainPanel.PARTICLE_SIZE;
    public static final Color COLOR = new Color(255,37,0);
    private static final Color COLOR1 = new Color(255,102,0);
    private static final Color COLOR2 = new Color(212, 21, 21);
    public static final long COLOR_DURATION = 500;
    private int randDispersionRate;
    private int dispersionRate;
    private long lastChange;
    private Clock clock;

    public Lava(int x, int y) {
        super(COLOR, x, y, DISPERSION_RATE, 2, false);
        this.dispersionRate = DISPERSION_RATE;
        this.clock = Clock.systemDefaultZone();
        this.lastChange = clock.millis() - COLOR_DURATION;
        this.setColor();
    }
    
    @Override
    public void update() {
        this.setColor();
        this.searchForFlammable();
        this.searchForWater();
        if (screen.isGas(x, y + 1)) {
            fallDown(x, y);
        } else if (screen.isGas(x - 1, y + 1)) {
            if (screen.isGas(x + 1, y + 1)) {
                this.moveTo(x + (rand.nextInt(2) == 1 ? 1 : -1), y + 1);
            }
            else {
                this.moveTo(x - 1, y + 1);
            }
        } else if (screen.isGas(x + 1, y + 1)) {
            this.moveTo(x + 1, y + 1);
        }
        else if (screen.isGas(x + 1, y) || screen.isGas(x - 1, y)) {
            disperse();
        }
        hasUpdated = true;
    }

    private void setColor() {
        if (clock.millis() - this.lastChange >= COLOR_DURATION && rand.nextInt(5) == 0) {
            int num = rand.nextInt(100);
            if (num < 70) {
                this.color = COLOR;
            } else if (num < 85) {
                this.color = COLOR1;
            } else {
                this.color = COLOR2;
            }
            this.lastChange = clock.millis();
        }
    }

    private int calculateFlatDispersion(int startX, int direction) {
        for (int i = 1; i < randDispersionRate; i++) {
            int checkX = startX + direction * i;
            if (!screen.inBounds(checkX, y) || !screen.isGas(checkX, y)) {
                return i - 1;
            }
        }
        return randDispersionRate - 1;
    }
    
    @Override
    protected void disperse() {
        randDispersionRate = dispersionRate + rand.nextInt(dispersionRate) - dispersionRate / 2;
        int rightValue = calculateFlatDispersion(x, 1);
        int leftValue = calculateFlatDispersion(x, -1);

        if (rightValue > leftValue) {
            this.moveTo(x + rightValue, y);
        } else if (rightValue == leftValue) {
            if (rightValue == randDispersionRate - 1) {
                if (screen.isGas(x + rightValue, y + 1)) {
                    this.moveTo(x + rightValue, y + 1);
                } else if (screen.isGas(x - leftValue, y + 1)) {
                    this.moveTo(x - leftValue, y + 1);
                } else {
                    this.moveTo(x + (rand.nextInt(2) == 1 ? rightValue : -leftValue), y);
                }
            } else {
                this.moveTo(x + (rand.nextInt(2) == 1 ? rightValue : -leftValue), y);
            }
        } else {
            this.moveTo(x - leftValue, y);
        }
    }

    private void searchForFlammable() {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (screen.isFlammable(i, j)) {
                    if (rand.nextInt(screen.ignitionChance(i, j)) == 0) 
                        screen.changeValue(i, j, new Fire(i, j));
                    return;
                }
            }
        }
    }

    private void searchForWater() {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 2; j <= y + 2; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (screen.isWater(i, j)) {
                    screen.changeValue(i, j, new Stone(i, j));
                    screen.changeValue(i, j - 1, new Steam(i, j - 1));
                    return;
                }
            }
        }
    }
}
