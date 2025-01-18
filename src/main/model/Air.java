package model;

import java.awt.Color;

public class Air extends GasParticle {
    public static final Color COLOR = new Color(34,34,34);
    public Air(int x, int y) {
        super(COLOR, x, y, false);
    }

    public void update() {}
}