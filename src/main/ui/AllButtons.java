package ui;

import javax.swing.*;

import model.Fire;
import model.Gunpowder;
import model.Oil;
import model.Sand;
import model.Smoke;
import model.Steam;
import model.Water;
import model.Wood;

import java.awt.Dimension;

public class AllButtons extends JPanel {
    private static final Dimension SIZE = new Dimension(MainPanel.SCREEN_HEIGHT / 20, MainPanel.SCREEN_HEIGHT);
    private static final int PADDING = 5;
    private MainPanel mp;
    public AllButtons(MainPanel mp) {
        super();
        this.mp = mp;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setMinimumSize(SIZE);
        this.setMinimumSize(SIZE);
        this.setPreferredSize(SIZE);
        this.setOpaque(false);
        addButtons();
    }

    private void addButtons() {
        this.add(Box.createRigidArea(new Dimension(0,PADDING)));
        this.add(new ParticleButton(1, Sand.COLOR, mp));
        this.add(Box.createRigidArea(new Dimension(0,PADDING)));
        this.add(new ParticleButton(2, Water.COLOR, mp));
        this.add(Box.createRigidArea(new Dimension(0,PADDING)));
        this.add(new ParticleButton(3, Wood.COLOR, mp));
        this.add(Box.createRigidArea(new Dimension(0,PADDING)));
        this.add(new ParticleButton(4, Fire.COLOR3, mp));
        this.add(Box.createRigidArea(new Dimension(0,PADDING)));
        this.add(new ParticleButton(5, Smoke.COLOR, mp));
        this.add(Box.createRigidArea(new Dimension(0,PADDING)));
        this.add(new ParticleButton(6, Steam.COLOR, mp));
        this.add(Box.createRigidArea(new Dimension(0,PADDING)));
        this.add(new ParticleButton(7, Gunpowder.COLOR, mp));
        this.add(Box.createRigidArea(new Dimension(0,PADDING)));
        this.add(new ParticleButton(8, Oil.COLOR, mp));
    }
}
