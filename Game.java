import java.awt.Canvas;
import java.awt.Color;
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
    private EnemyAI enemyAI;
    private Timer timer;
    private Score score;
    private List<Item> items;
    private List<Bomb> bombs;
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

    public Game() {
        // Initialize game components
        maze = new Maze(20, 20); // Example dimensions
        player = new Player(1, 1); // Starting position
        enemyAI = new EnemyAI(10, 10); // Starting position
        timer = new Timer();
        score = new Score();
        items = new ArrayList<>();
        bombs = new ArrayList<>();
        isRunning = false;

        // Add items (example: 3 items)
        items.add(new Item(100, 100));
        items.add(new Item(200, 200));
        items.add(new Item(300, 300));

        // Add bombs (example: 2 bombs)
        bombs.add(new Bomb(150, 150));
        bombs.add(new Bomb(250, 250));

        // Add key listener to the canvas
        addKeyListener(this);
        setFocusable(true);
    }

    public void start() {
        // Game starting logic here
        System.out.println("Game started!");
        isRunning = true;

        // Start the timer
        timer.start();

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (isRunning) {
            // Update game state
            player.update(); // Implement player update logic
            enemyAI.update(player); // Implement enemy AI update logic

            // Check for collisions with items
            checkItemCollisions();

            // Check for collisions with bombs
            if (checkBombCollisions()) {
                isRunning = false; // End game on bomb collision
            }

            // Repaint the canvas to trigger rendering
            repaint();

            // Example logic to end the game after a certain number of iterations or based on conditions
            if (timer.getElapsedTime() > 60) { // End game after 60 seconds
                isRunning = false;
            }

            // Sleep for a short duration to control game speed
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Game Over");
    }

    @Override
    public void paint(Graphics g) {
        // Use double buffering to reduce flickering
        super.paint(g);

        // Render game state
        renderGame(g);
    }

    private void renderGame(Graphics g) {
        // Clear screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Render maze
        maze.render(g); // Implement actual rendering

        // Render player
        player.render(g); // Implement actual rendering

        // Render enemy
        enemyAI.render(g); // Implement actual rendering

        // Render items
        for (Item item : items) {
            item.render(g); // Implement actual rendering
        }

        // Render bombs
        for (Bomb bomb : bombs) {
            bomb.render(g); // Implement actual rendering
        }

        // Render HUD and score
        renderHUD(g);
    }

    private void renderHUD(Graphics g) {
        // Render HUD elements (score, timer, etc.)
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score.getPoints(), 10, 20);
        g.drawString("Time: " + timer.getElapsedTime(), 10, 40);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
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
            case KeyEvent.VK_ESCAPE:
                isRunning = false; // Example: Exit game on ESC key
                break;
        }

        // Repaint the canvas after movement
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Implement any necessary logic for key released events
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implement any necessary logic for key typed events
    }

    // Method to handle collisions with items
    private void checkItemCollisions() {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (player.getX() == item.getX() && player.getY() == item.getY()) {
                score.addPoints(10); // Add points for collecting item
                iterator.remove(); // Remove item after collection (or reset its position)
            }
        }
    }

    // Method to check collisions with bombs
    private boolean checkBombCollisions() {
        for (Bomb bomb : bombs) {
            if (player.getX() == bomb.getX() && player.getY() == bomb.getY()) {
                return true; // End game on bomb collision
            }
        }
        return false;
    }
}
