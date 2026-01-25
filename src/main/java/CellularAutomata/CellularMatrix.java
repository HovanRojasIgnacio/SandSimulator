package CellularAutomata;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import Util.Color; // Assuming your Color class is here

import static org.lwjgl.opengl.GL11.*;

public class CellularMatrix {

    public final static int WIDTH = 800;
    public final static int HEIGHT = 800;
    public final static int CELLSIZE = 4;

    private static final int ROWS = HEIGHT / CELLSIZE;
    private static final int COLS = WIDTH / CELLSIZE;

    private static final Cell[][] matrix = new Element[ROWS][COLS];

    private final FloatBuffer vertexBuffer;
    private final FloatBuffer colorBuffer;

    public CellularMatrix() {
        int totalQuads = ROWS * COLS;
        vertexBuffer = BufferUtils.createFloatBuffer(totalQuads * 4 * 2);
        colorBuffer = BufferUtils.createFloatBuffer(totalQuads * 4 * 3);

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                matrix[x][y] = new Air();
            }
        }
    }

    public void draw() {
        vertexBuffer.clear();
        colorBuffer.clear();

        int count = 0;

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                Cell cell = matrix[x][y];
                if (cell != null) {
                    Color c = cell.getColor();

                    float drawX = x * CELLSIZE;
                    float drawY = y * CELLSIZE;
                    float size = CELLSIZE;

                    vertexBuffer.put(drawX).put(drawY);
                    colorBuffer.put(c.r).put(c.g).put(c.b);

                    vertexBuffer.put(drawX + size).put(drawY);
                    colorBuffer.put(c.r).put(c.g).put(c.b);

                    vertexBuffer.put(drawX + size).put(drawY + size);
                    colorBuffer.put(c.r).put(c.g).put(c.b);

                    vertexBuffer.put(drawX).put(drawY + size);
                    colorBuffer.put(c.r).put(c.g).put(c.b);

                    count++;
                }
            }
        }

        vertexBuffer.flip();
        colorBuffer.flip();

        if (count > 0) {
            glEnableClientState(GL_VERTEX_ARRAY);
            glEnableClientState(GL_COLOR_ARRAY);

            glVertexPointer(2, GL_FLOAT, 0, vertexBuffer);
            glColorPointer(3, GL_FLOAT, 0, colorBuffer);

            glDrawArrays(GL_QUADS, 0, count * 4);

            glDisableClientState(GL_VERTEX_ARRAY);
            glDisableClientState(GL_COLOR_ARRAY);
        }
    }

    public void setCell(double x, double y, Cell cell) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) {
            return;
        }
        int posX = (int) Math.floor(x / CELLSIZE);
        int posY = (int) Math.floor(y / CELLSIZE);

        // Safety bounds check for array access
        if (posX < matrix.length && posY < matrix[0].length) {
            matrix[posX][posY] = cell;
        }
    }

    public void stepAll() {
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (matrix[i][j] != null && !matrix[i][j].hasMoved()) {
                    matrix[i][j].step(matrix, i, j);
                }
            }
        }
        reset();
    }

    public static void swap(int xFirst, int xSecond, int yFirst, int ySecond) {
        // Bounds check to prevent crashes during physics calculations
        if (xFirst >= 0 && xFirst < matrix.length && yFirst >= 0 && yFirst < matrix[0].length &&
                xSecond >= 0 && xSecond < matrix.length && ySecond >= 0 && ySecond < matrix[0].length) {

            Cell temporary = matrix[xFirst][yFirst];
            matrix[xFirst][yFirst] = matrix[xSecond][ySecond];
            matrix[xSecond][ySecond] = temporary;
        }
    }

    private void reset() {
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (matrix[i][j] != null)
                    matrix[i][j].reset();
            }
        }
    }
}