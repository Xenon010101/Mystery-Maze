import java.awt.Graphics;
import java.awt.Color;

public class EnemyAI {
    private int x, y;
    private int speed = 3;

    public EnemyAI(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update(Player player) {
        // Implement patrolling and chasing behavior
        // Example logic to use x, y, and speed
        if (player.getX() > x) {
            x += speed;
        } else if (player.getX() < x) {
            x -= speed;
        }
        if (player.getY() > y) {
            y += speed;
        } else if (player.getY() < y) {
            y -= speed;
        }
    }

    public void render(Graphics g) {
        // Render the enemy
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 10, 10); // Example rendering
    }
}
