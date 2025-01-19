package model;

import java.awt.Color;
import java.util.Random;

public abstract class FallingLiquid extends FallingParticle {
    protected float velocityX;
    private int dispersionRate;
    private int randDispersionRate;
    private int density;
    protected Random rand;

    public FallingLiquid(Color color, int x, int y, int dispersionRate, int density, boolean variableColor) {
        super(color, x, y, variableColor);
        velocityX = 0f;
        this.dispersionRate = dispersionRate;
        rand = new Random();
        this.density = density;
    }

    public int getDensity() {
        return density;
    }

    private int calculateFlatDispersion(int startX, int direction) {
        for (int i = 1; i < randDispersionRate; i++) {
            int checkX = startX + direction * i;
            if (!screen.inBounds(checkX, y) || (!screen.isGas(checkX, y) && !(screen.isLiquid(checkX, y) && screen.getLiquidDensity(checkX, y) < density))) {
                return i - 1;
            }
        }
        return randDispersionRate - 1;
    }
    // To disperse water better, checks every element between current pos and pos
    // moves as far without phasing through walls
    protected void disperse() {
        randDispersionRate = dispersionRate + rand.nextInt(10) - 5;
        int rightValue = calculateFlatDispersion(x, 1);
        int leftValue = calculateFlatDispersion(x, -1);

        if (rightValue > leftValue) {
            this.moveTo(x + rightValue, y);
        } else if (rightValue == leftValue) {
            if (rightValue == randDispersionRate - 1) {
                if (screen.isGas(x + rightValue, y + 1)) {
                    this.moveTo(x + rightValue, y + 1);
                } else if (screen.isGas(x - leftValue, y + 1)) {
                    this.moveTo(x - leftValue, y + 1);
                } else {
                    this.moveTo(x + (rand.nextInt(2) == 1 ? rightValue : -leftValue), y);
                }
            } else {
                this.moveTo(x + (rand.nextInt(2) == 1 ? rightValue : -leftValue), y);
            }
        } else {
            this.moveTo(x - leftValue, y);
        }
    }

    // if directly below is open, go down
    // if below is blocked, but below and left is open, go there
    // if below is blocked and below and left is blocked, but below and right is
    // open, go there
    // then trying going left, then try going right, then go nowhere if all else
    // fails
    @Override
    public void update() {
        if (screen.isGas(x, y + 1)) {
            fallDown(x, y);
        } else if (screen.isGas(x - 1, y + 1)) {
            if (screen.isGas(x + 1, y + 1)) {
                this.moveTo(x + (rand.nextInt(2) == 1 ? 1 : -1), y + 1);
            }
            else {
                this.moveTo(x - 1, y + 1);
            }
        } else if (screen.isGas(x + 1, y + 1)) {
            this.moveTo(x + 1, y + 1);
        } else if (screen.isLiquid(x, y + 1) && screen.getLiquidDensity(x, y + 1) < density) {
            this.swap(x, y + 1);
        } else if (screen.isLiquid(x - 1, y + 1) && screen.getLiquidDensity(x - 1, y + 1) < density) {
            if (screen.isLiquid(x + 1, y + 1) && screen.getLiquidDensity(x + 1, y + 1) < density) {
                this.swap(x + (rand.nextInt(2) == 1 ? 1 : -1), y + 1);
            }
            else {
                this.swap(x - 1, y + 1);
            }
        } else if (screen.isLiquid(x + 1, y + 1) && screen.getLiquidDensity(x + 1, y + 1) < density) {
            this.swap(x + 1, y + 1);
        } 
        else if (screen.isGas(x + 1, y) || screen.isGas(x - 1, y) || (screen.isLiquid(x - 1, y) && screen.getLiquidDensity(x - 1, y) < density) || (screen.isLiquid(x + 1, y) && screen.getLiquidDensity(x + 1, y) < density)) {
            disperse();
        }
        hasUpdated = true;
    }
}
