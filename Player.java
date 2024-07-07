import java.awt.Graphics;
import java.awt.Color;

public class Player {
    private int x, y;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void move(String direction) {
        switch (direction) {
            case "UP": y -= 1; break;
            case "DOWN": y += 1; break;
            case "LEFT": x -= 1; break;
            case "RIGHT": x += 1; break;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x * 20, y * 20, 20, 20); // Draw player
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

	public void update() {
		// TODO Auto-generated method stub
		
	}
}
