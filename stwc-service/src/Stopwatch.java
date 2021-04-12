import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Stopwatch {
    private volatile long now;
    private volatile long lastTime;
    private volatile long elapsed;
    private boolean paused = false;
    private Panel panel;
    private ScheduledFuture<?> running = null;
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final ReentrantLock lock = new ReentrantLock();

    public Stopwatch(Panel panel) {
        this.panel = panel;
    }

    public void makeTime() {
        lock.lock();
        try {
            now = System.currentTimeMillis();
            long delta = now - lastTime;
            lastTime = now;
            if (!paused) {
                elapsed += delta;
                panel.displayTime(elapsed);
            }
        } finally {
            lock.unlock();
        }
    }

    protected void start() {
        lock.lock();
        try {
            if (running != null) {
                return;
            }
            paused = false;
            lastTime = System.currentTimeMillis();
            running = executor.scheduleAtFixedRate(new TimeThread(this),
                    panel.getDelay(),panel.getPeriod(), TimeUnit.SECONDS);
        } finally {
            lock.unlock();
        }
    }

    protected void pause() {
        lock.lock();
        try {
            if (running == null) {
                return;
            }
            running.cancel(false);
            paused = true;
            running = null;
        } finally {
            lock.unlock();
        }
    }

    protected void reset() {
        lock.lock();
        try {
            if (running != null) {
                return;
            }
            elapsed = 0;
        } finally {
            lock.unlock();
        }
    }
}
