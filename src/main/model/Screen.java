package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import ui.MainPanel;

// Screen will hold what particles are in the screen
// 0 = air
// 1 = sand
public class Screen {
    public Particle[][] screen;
    private static Screen instance = null;
    public final static int THREAD_COUNT = 12;

    private Screen() {
        screen = new Particle[MainPanel.ARR_WIDTH][MainPanel.ARR_HEIGHT];

        // for (int i = 0; i < THREAD_COUNT; i++) {
        //     mutex[i] = new ReentrantLock();
        //     cond[i] = mutex[i].newCondition();
        // }
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

    public Particle getParticle(int x, int y) {
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

    public boolean inBounds(int x, int y) {
        return (x >= 0) && (x < MainPanel.ARR_WIDTH) && (y >= 0) && (y < MainPanel.ARR_HEIGHT);
    }

    public void update() {
        // for (int i = MainPanel.ARR_WIDTH - 1; i >= 0; i--) {
        //     for (int j = 0; j < MainPanel.ARR_HEIGHT; j++) {
        //         if (!screen[i][j].hasUpdated()) 
        //             screen[i][j].update();
        //     }
        // }
        // for (int i = MainPanel.ARR_WIDTH - 1; i >= 0; i--) {
        //     for (int j = 0; j < MainPanel.ARR_HEIGHT; j++) {
        //         screen[i][j].notUpdated();
        //     }
        // }
        List<Thread> threads = new ArrayList<>();
        ReentrantLock mutex = new ReentrantLock();
        Condition oddCondition = mutex.newCondition();
        Condition evenCondition = mutex.newCondition();
        AtomicInteger currentPhase = new AtomicInteger(1); // 1 = odd, 0 = even
        AtomicInteger threadsCompleted = new AtomicInteger(0);

        int div = MainPanel.ARR_WIDTH / THREAD_COUNT;

        for (int i = 0; i < THREAD_COUNT; i++) {
            int startX = div * i;
            int endX = (i == THREAD_COUNT - 1) ? MainPanel.ARR_WIDTH : div * (i + 1);
            boolean isOdd = (i % 2 == 1);
            Thread thread = new ScreenThread(startX, endX, mutex, oddCondition, evenCondition, currentPhase, threadsCompleted, THREAD_COUNT, isOdd);
            threads.add(thread);
            thread.start();
        }

        // Join all threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void changeValue(int x, int y, Particle val) {
        screen[x][y] = val;
        val.setCoords(x, y);
    }
}
