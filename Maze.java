import java.awt.Graphics;
import java.awt.Color;

public class Maze {
    private int[][] grid;
    private int rows, cols;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new int[rows][cols];
        generateMaze();
    }

    private void generateMaze() {
        // Implement depth-first search or recursive backtracking algorithm
        // Example logic to use grid, rows, and cols
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = (i + j) % 2; // Example maze pattern
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    g.fillRect(j * 20, i * 20, 20, 20); // Draw wall
                } else {
                    g.clearRect(j * 20, i * 20, 20, 20); // Draw path
                }
            }
        }
    }
}
