import java.awt.Color;
import java.awt.Graphics;

public class Item {
    private int x, y;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        // Render the item
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 10, 10); // Example rendering
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
