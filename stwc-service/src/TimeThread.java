public class TimeThread implements Runnable {
    private Stopwatch stopwatch;

    public TimeThread(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    @Override
    public void run() {
      stopwatch.makeTime();
    }
}
