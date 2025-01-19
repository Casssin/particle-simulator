package model;

import java.awt.Color;

public class Stone extends Particle {
    public static final Color COLOR = Color.GRAY;
    public Stone(int x, int y) {
        super(COLOR, x, y, true);
    }

    @Override
    public void update() {}
}
