package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.MainPanel;

public class SandTest {
    private Screen screen;
    private Sand sand;
    
    @BeforeEach
    void runBefore() {
        screen = Screen.getInstance();
        sand = new Sand(1, 1);
        screen.changeValue(1, 1, sand);
    }

    @Test
    void testMoveTo() {
        sand.moveTo(10, 10);
        assertEquals("Sand", screen.getClass(10, 10));
        assertTrue(screen.isAir(1, 1));
        assertEquals(10, sand.getX());
        assertEquals(10, sand.getY());
    }

    @Test
    void testUpdate() {
        sand.update();
        assertTrue(screen.isAir(1, 1));
        assertEquals(1, sand.getX());
        assertEquals(2, sand.getY());
        assertEquals("Sand", screen.getClass(1, 2));

        sand.moveTo(1, MainPanel.ARR_HEIGHT - 2);
        screen.changeValue(1, MainPanel.ARR_HEIGHT - 1, new Sand(1, MainPanel.ARR_HEIGHT - 1));
        sand.update();
        assertTrue(screen.isAir(1, MainPanel.ARR_HEIGHT - 2));
        assertEquals(0, sand.getX());
        assertEquals(MainPanel.ARR_HEIGHT - 1, sand.getY());
        assertEquals("Sand", screen.getClass(0, MainPanel.ARR_HEIGHT - 1));

        sand.moveTo(1, MainPanel.ARR_HEIGHT - 2);
        screen.changeValue(1, MainPanel.ARR_HEIGHT - 1, new Sand(1, MainPanel.ARR_HEIGHT - 1));
        screen.changeValue(0, MainPanel.ARR_HEIGHT - 1, new Sand(0, MainPanel.ARR_HEIGHT - 1));
        sand.update();
        assertTrue(screen.isAir(1, MainPanel.ARR_HEIGHT - 2));
        assertEquals(2, sand.getX());
        assertEquals(MainPanel.ARR_HEIGHT - 1, sand.getY());
        assertEquals("Sand", screen.getClass(2, MainPanel.ARR_HEIGHT - 1));
    }
}
