package ui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;

public class LabelPanel extends JPanel {
    private ArrayList<Label> labels; 
    private Label currLabel;
    private static final Dimension SIZE = new Dimension(MainPanel.SCREEN_WIDTH / 40, MainPanel.SCREEN_HEIGHT);

    public LabelPanel() {
        super();
        this.setMinimumSize(SIZE);
        this.setMinimumSize(SIZE);
        this.setPreferredSize(SIZE);
        this.setOpaque(false);
        labels = new ArrayList<Label>();
        initArray();
        currLabel = labels.get(0);
        this.add(currLabel);
    }

    private void initArray() { 
        Label Air = new Label(""); 
        labels.add(Air); 
        Label Sand = new Label("Sand"); 
        labels.add(Sand); 
        Label Water = new Label("Water"); 
        labels.add(Water); 
        Label Wood = new Label("Wood"); 
        labels.add(Wood); 
        Label Fire = new Label("Fire"); 
        labels.add(Fire); 
        Label Smoke = new Label("Smoke"); 
        labels.add(Smoke); 
        Label Steam = new Label("Steam"); 
        labels.add(Steam); 
        Label Gunpowder = new Label("Gunpowder"); 
        labels.add(Gunpowder); 
        Label Oil = new Label("Oil"); 
        labels.add(Oil); 
        Label Stone = new Label("Stone"); 
        labels.add(Stone); 
        Label Lava = new Label("Lava"); 
        labels.add(Lava); 
    }

    public void setLabel(int currParticle) {
        this.remove(currLabel);
        currLabel = labels.get(currParticle);
        this.add(currLabel);
        this.revalidate(); // Revalidate to refresh the layout
        this.repaint();    // Repaint to reflect changes
    }
}
