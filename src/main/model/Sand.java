package model;

import java.awt.Color;

import ui.MainPanel;

public class Sand extends Solid {
    public Sand(int x, int y) {
        super(Color.yellow, x, y);
    }

    @Override
    public void update() {
        if (y + 1 < MainPanel.ARR_HEIGHT) {
            if (screen.isAir(x, y + 1)) {
                this.moveTo(x, y + 1);
            } else if (x - 1 >= 0 && screen.isAir(x - 1, y + 1)) {
                this.moveTo(x - 1, y + 1);
            } else if (x + 1 < MainPanel.ARR_WIDTH && screen.isAir(x + 1, y + 1)) {
                this.moveTo(x + 1, y + 1);
            }
        }
    }
}
