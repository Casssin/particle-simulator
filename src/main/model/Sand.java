package model;

import java.awt.Color;

import ui.MainPanel;

public class Sand extends SolidFallingParticle {
    public Sand(int x, int y) {
        super(Color.yellow, x, y);
    }

    // if directly below is open, go down
    // if below is blocked, but below and left is open, go there
    // if below is blocked and below and left is blocked, but below and right is
    // open, go there
    // otherwise don't go anywhere
    @Override
    public void update() {
        if (y + 1 < MainPanel.ARR_HEIGHT) {
            if (screen.isAir(x, y + 1)) {
                this.moveTo(x, y + 1);
            } else if (x - 1 >= 0 && screen.isAir(x - 1, y + 1)) {
                this.moveTo(x - 1, y + 1);
            } else if (x + 1 < MainPanel.ARR_WIDTH && screen.isAir(x + 1, y + 1)) {
                this.moveTo(x + 1, y + 1);
            } else if (screen.isWater(x, y + 1)) {
                this.swap(x, y + 1);
            } else if (x - 1 >= 0 && screen.isWater(x - 1, y + 1)) {
                this.swap(x - 1, y + 1);
            } else if (x + 1 < MainPanel.ARR_WIDTH && screen.isWater(x + 1, y + 1)) {
                this.swap(x + 1, y + 1);
            }
        }

        hasUpdated = true;
    }
}
