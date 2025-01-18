package model;

import java.awt.Color;

public class Wood extends Particle implements Flammable {
    public static final Color COLOR = new Color(111, 78, 55);
    private static final int IGNITIONCHANCE = 100;
    public Wood(int x, int y) {
        super(COLOR, x, y, true);
    }

    public void update() {}

    @Override
    public int ignitionChance() {
        return IGNITIONCHANCE;
    }
}
