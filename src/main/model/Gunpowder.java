package model;

import java.awt.Color;

import ui.MainPanel;

public class Gunpowder extends SolidFallingParticle implements Flammable {
    public static final Color COLOR = new Color(16,16,16);
    private static final int IGNITIONCHANCE = 2;
    
    public Gunpowder(int x, int y) {
        super(COLOR, x, y);
    }


    @Override
    public int ignitionChance() {
        return IGNITIONCHANCE;
    }
}

