package model;

import java.awt.Color;

public class Wood extends Particle {
    private static final Color COLOR = new Color(111, 78, 55);
    public Wood(int x, int y) {
        super(COLOR, x, y, true);
    }

    public void update() {}
}
