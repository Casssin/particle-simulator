package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Label extends JLabel {
    public Label (String text) {
        super(text);
        this.setFont(new Font("OCRA",1,20));
        this.setForeground(Color.WHITE);
    }
}
