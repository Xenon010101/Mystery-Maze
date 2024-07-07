import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
    private Maze maze;
    private Player player;
    private List<Item> items;
    private List<Bomb> bombs;
    private Score score;
    private boolean isRunning;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        Game game = new Game();
        frame.add(game);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        game.start();
    }

    public void start() {
        // Initialize game components
        maze = new Maze(20, 20); // Example maze dimensions
        player = new Player(1, 1); // Starting position
        items = new ArrayList<>();
        bombs = new ArrayList<>();
        score = new Score();
        isRunning = true;

        // Generate items
        generateItems();

        // Generate bombs
        generateBombs();

        // Start the game loop
        new Thread(this).start();

        // Add key listener for player movement
        addKeyListener(this);
        setFocusable(true);
    }

    private void generateItems() {
        // Generate items at random positions
        for (int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * maze.getCols());
            int y = (int) (Math.random() * maze.getRows());
            items.add(new Item(x, y));
        }
    }

    private void generateBombs() {
        // Generate bombs at random positions
        for (int i = 0; i < 2; i++) {
            int x = (int) (Math.random() * maze.getCols());
            int y = (int) (Math.random() * maze.getRows());
            bombs.add(new Bomb(x, y));
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            // Game loop logic

            // Check collision with items
            checkItemCollision();

            // Check collision with bombs
            checkBombCollision();

            // Repaint to update game state
            repaint();

            try {
                Thread.sleep(100); // Adjust speed of game loop
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkItemCollision() {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (player.getX() == item.getX() && player.getY() == item.getY()) {
                score.addPoints(10); // Example: Increase score on collecting item
                iterator.remove();
                generateItems(); // Generate new item
            }
        }
    }

    private void checkBombCollision() {
        for (Bomb bomb : bombs) {
            if (player.getX() == bomb.getX() && player.getY() == bomb.getY()) {
                isRunning = false; // Game over on hitting bomb
                break;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        // Render game state
        maze.render(g);
        player.render(g);

        for (Item item : items) {
            item.render(g);
        }

        for (Bomb bomb : bombs) {
            bomb.render(g);
        }

        // Render score and HUD
        renderScore(g);
    }

    private void renderScore(Graphics g) {
        g.drawString("Score: " + score.getPoints(), 20, 20);
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                player.move("UP");
                break;
            case KeyEvent.VK_DOWN:
                player.move("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                player.move("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                player.move("RIGHT");
                break;
            case KeyEvent.VK_Q:
                isRunning = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}
