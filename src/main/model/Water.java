package model;

import java.awt.Color;

import ui.MainPanel;

public class Water extends FallingParticle {
    private static final int DISPERSION_RATE = 5;

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

    private int calculateDisperseValue(int startX, int direction) {
        for (int i = 1; i < DISPERSION_RATE; i++) {
            int checkX = startX + direction * i;
            if (!screen.inBounds(checkX, y) || !screen.isAir(checkX, y)) {
                return i - 1;
            }
        }
        return DISPERSION_RATE - 1;
    }
    
    // To disperse water better, checks every element between current pos and pos
    // moves as far without phasing through walls
    private void disperse() {
        int rightValue = calculateDisperseValue(x, 1);
        int leftValue = calculateDisperseValue(x, -1);

        if (rightValue >= leftValue) {
            this.moveTo(x + rightValue, y);
        } else {
            this.moveTo(x - leftValue, y);
        }
    }

}
