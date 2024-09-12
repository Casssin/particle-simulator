package model;

import ui.MainPanel;

// Screen will hold what particles are in the screen
// 0 = air
// 1 = sand
public class Screen {
    private Particle[][] screen;
    private static Screen instance = null;

    private Screen() {
        screen = new Particle[MainPanel.ARR_WIDTH][MainPanel.ARR_HEIGHT];
        initArray();
    }

    public static Screen getInstance() {
        if (instance == null) {
            instance = new Screen();
        }

        return instance;
    }

    private void initArray() {
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[i].length; j++) {
                screen[i][j] = new Air(i, j);
            }
        }
    }

    public Particle getValue(int x, int y) {
        return screen[x][y];
    }

    public String getClass(int x, int y) {
        String temp = screen[x][y].getClass().toString();
        return temp.substring(temp.indexOf(".") + 1);
    }

    public Boolean isAir(int x, int y) {
        return this.getClass(x, y).equals("Air");
    }

    public Boolean isWater(int x, int y) {
        return this.getClass(x, y).equals("Water");
    }

    public void update() {
        for (int i = MainPanel.ARR_WIDTH - 1; i >= 0; i--) {
            for (int j = MainPanel.ARR_HEIGHT - 1; j >= 0; j--) {
                screen[i][j].update();
            }
        }
    }

    public void changeValue(int x, int y, Particle val) {
        screen[x][y] = val;
        val.setCoords(x, y);
    }
}
