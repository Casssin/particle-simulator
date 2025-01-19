package model;

import java.awt.Color;

import ui.MainPanel;

public class Oil extends FallingLiquid implements Flammable {
    private static final int DISPERSION_RATE = 20 / MainPanel.PARTICLE_SIZE;
    public static final Color COLOR = new Color(235, 180, 3);
    private static final int IGNITIONCHANCE = 5;

    public Oil(int x, int y) {
        super(COLOR, x, y, DISPERSION_RATE, 1, false);
    }

    @Override
    public int ignitionChance() {
        return IGNITIONCHANCE;
    }
}
