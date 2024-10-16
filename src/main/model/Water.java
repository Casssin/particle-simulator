package model;

import java.awt.Color;

import ui.MainPanel;

public class Water extends FallingParticle {
    private static int dispersionRate = 5;

    public Water(int x, int y) {
        super(Color.blue, x, y);
    }

    // if directly below is open, go down
    // if below is blocked, but below and left is open, go there
    // if below is blocked and below and left is blocked, but below and right is
    // open, go there
    // then trying going left, then try going right, then go nowhere if all else
    // fails
    @Override
    public void update() {
        if (y + 1 < MainPanel.ARR_HEIGHT) {
            if (screen.isAir(x, y + 1)) {
                this.moveTo(x, y + 1);
            } else if (x - 1 >= 0 && screen.isAir(x - 1, y + 1)) {
                this.moveTo(x - 1, y + 1);
            } else if (x + 1 < MainPanel.ARR_WIDTH && screen.isAir(x + 1, y + 1)) {
                this.moveTo(x + 1, y + 1);
            } else {
                disperse();
            }
        }
        hasUpdated = true;
    }

    // To disperse water better, checks every element between current pos and pos
    // moves as far without phasing through walls
    private void disperse() {
        boolean breakEarly = false;
        int rightValue = 0, leftValue = 0;
        for (int i = x + 1; i < Math.min(MainPanel.ARR_WIDTH, x + dispersionRate); i++) {
            if (!screen.isAir(i, y)) {
                rightValue = i - x;
                breakEarly = true;
                break;
            }
        }
        if (!breakEarly && screen.inBounds(x + dispersionRate - 1, y)) {
            this.moveTo(x + dispersionRate - 1, y);
        }

        breakEarly = false;
        for (int i = x - 1; i > x - dispersionRate; i++) {
            if (!screen.isAir(i, y) || !screen.inBounds(i, y)) {
                this.moveTo(i + 1, y);
                breakEarly = true;
                break;
            }
        }
        if (!breakEarly) {
            this.moveTo(x - dispersionRate + 1, y);
        }
    }

}
