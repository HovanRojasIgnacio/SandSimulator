package CellularAutomata;

import Util.Color;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glColor3f;

public abstract class Cell {

    Color color = new Color(1,0,0);


    public void paint(double x, double y, int size) {
        glColor3f(color.r, color.g, color.b);

        glBegin(GL_QUADS);
        glVertex2d(x, y);              // Bottom-Left
        glVertex2d(x + size, y);       // Bottom-Right
        glVertex2d(x + size, y + size);// Top-Right
        glVertex2d(x, y + size);       // Top-Left
        glEnd();
    }
}
