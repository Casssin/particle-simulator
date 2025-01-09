package model;

import java.awt.Color;

import ui.MainPanel;

public class Gunpowder extends SolidFallingParticle implements Flammable {
    private static final Color COLOR = new Color(16,16,16);
    private static final int IGNITIONCHANCE = 2;
    
    public Gunpowder(int x, int y) {
        super(COLOR, x, y);
    }

    // if directly below is open, go down
    // if below is blocked, but below and left is open, go there
    // if below is blocked and below and left is blocked, but below and right is
    // open, go there
    // otherwise don't go anywhere
    @Override
    public void update() {
        if (y + 1 < MainPanel.ARR_HEIGHT) {
            if (screen.isGas(x, y + 1)) {
                fallDown(x, y);
            } else if (x - 1 >= 0 && screen.isGas(x - 1, y + 1)) {
                this.moveTo(x - 1, y + 1);
            } else if (x + 1 < MainPanel.ARR_WIDTH && screen.isGas(x + 1, y + 1)) {
                this.moveTo(x + 1, y + 1);
            } else if (screen.isWater(x, y + 1)) {
                this.swap(x, y + 1);
            } else if (x - 1 >= 0 && screen.isWater(x - 1, y + 1)) {
                this.swap(x - 1, y + 1);
            } else if (x + 1 < MainPanel.ARR_WIDTH && screen.isWater(x + 1, y + 1)) {
                this.swap(x + 1, y + 1);
            } else {
                velocityY = 0;
            }
        }

        hasUpdated = true;
    }

    @Override
    public int ignitionChance() {
        return IGNITIONCHANCE;
    }
}

