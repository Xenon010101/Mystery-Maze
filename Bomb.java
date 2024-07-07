import java.awt.Graphics;
import java.awt.Color;

public class Bomb {
    private int x, y;
    private long detonateTime;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
        this.detonateTime = System.currentTimeMillis() + 2000; // 2 seconds until explosion
    }

    public boolean isExploded() {
        return System.currentTimeMillis() >= detonateTime;
    }

    public void render(Graphics g) {
        if (isExploded()) {
            // Render explosion
            g.setColor(Color.RED);
            g.fillOval(x - 10, y - 10, 20, 20); // Example explosion
        } else {
            // Render bomb
            g.setColor(Color.BLACK);
            g.fillOval(x - 5, y - 5, 10, 10); // Example bomb
        }
    }

    public void explode() {
        // Check if player or enemy is within explosion radius
        // Remove or update walls if necessary
        // Apply effects like damage to player and enemy
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
