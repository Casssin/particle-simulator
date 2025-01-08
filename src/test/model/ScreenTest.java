package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScreenTest {
    private Screen screen;
    
    @BeforeEach
    void runBefore() {
        screen = Screen.getInstance();
    }

    @Test
    void testIsAir() {
        screen.changeValue(0, 0, new Sand(0, 0));
        assertFalse(screen.isGas(0, 0));
        assertTrue(screen.isGas(1, 1));
    }

    @Test
    void testGetClass() {
        screen.changeValue(0, 0, new Sand(0, 0));
        assertEquals("Air", screen.getClass(1, 1));
        assertEquals("Sand", screen.getClass(0, 0));
    }
}
