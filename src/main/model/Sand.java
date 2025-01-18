package model;

import java.awt.Color;


public class Sand extends SolidFallingParticle {
    public static final Color COLOR = new Color(255, 229, 33);
    
    public Sand(int x, int y) {
        super(COLOR, x, y);
    }

}

