import javax.swing.*;

public class RaceThread implements Runnable {
    private RaceArena arena;

    public RaceThread(RaceArena arena) {
        this.arena = arena;
    }

    @Override
    public void run() {
        while (arena.running != null) {
            arena.race();
            arena.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(arena, msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
