package model;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import ui.MainPanel;

public class ScreenThread extends Thread {
    private int startX;
    private int endX;
    private Particle[][] screen;
    private ReentrantLock mutex;
    private Condition oddCondition;
    private Condition evenCondition;
    private AtomicInteger currentPhase;
    private AtomicInteger threadsCompleted;
    private int totalThreads;
    private boolean isOdd;

    public ScreenThread(int startX, int endX, ReentrantLock mutex, Condition oddCondition, Condition evenCondition, AtomicInteger currentPhase, AtomicInteger threadsCompleted, int totalThreads, boolean isOdd) {
        this.startX = startX;
        this.endX = endX;
        this.screen = Screen.getInstance().screen;
        this.mutex = mutex;
        this.oddCondition = oddCondition;
        this.evenCondition = evenCondition;
        this.currentPhase = currentPhase;
        this.threadsCompleted = threadsCompleted;
        this.totalThreads = totalThreads;
        this.isOdd = isOdd;
    }

    @Override
    public void run() {
        // System.out.print(threadsCompleted);
        try {
            mutex.lock();
            try {
                while (currentPhase.get() != (isOdd ? 1 : 0)) {
                    if (isOdd) {
                        oddCondition.await();
                    } else {
                        evenCondition.await();
                    }
                }
            } finally {
                mutex.unlock();
            }
            
            // System.out.print(Thread.currentThread().getId());
            // System.out.print(": isOdd: ");
            // System.out.println(isOdd);

            // Perform screen updates
            for (int i = startX; i < endX; i++) {
                for (int j = MainPanel.ARR_HEIGHT - 1; j >= 0; j--) {
                    if (!screen[i][j].hasUpdated()) {
                        screen[i][j].update();
                    }
                }
            }
            for (int i = startX; i < endX; i++) {
                for (int j = 0; j < MainPanel.ARR_HEIGHT; j++) {
                    screen[i][j].notUpdated();
                }
            }

            // Notify phase completion
            mutex.lock();
            try {
                threadsCompleted.incrementAndGet();
                if (threadsCompleted.get() == totalThreads / 2) { // All threads in the phase have completed
                    threadsCompleted.set(0); // Reset for next phase
                    currentPhase.set(1 - currentPhase.get()); // Switch phase
                    if (currentPhase.get() == 1) {
                        oddCondition.signalAll(); // Odd phase starts
                    } else {
                        evenCondition.signalAll(); // Even phase starts
                    }
                }
            } finally {
                mutex.unlock();
            }
        } catch (InterruptedException ex) {
           ex.printStackTrace();
        }
    }
}
