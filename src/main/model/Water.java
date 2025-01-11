package model;

import java.awt.Color;

import ui.MainPanel;

public class Water extends FallingLiquid {
    private static final int DISPERSION_RATE = 20 / MainPanel.PARTICLE_SIZE;
    private static final Color COLOR = new Color(22, 106, 250);

    public Water(int x, int y) {
        super(COLOR, x, y, DISPERSION_RATE, 2);
    }


}
