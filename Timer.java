public class Timer {
    private long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public int getElapsedTime() {
        return (int)((System.currentTimeMillis() - startTime) / 1000);
    }
}
