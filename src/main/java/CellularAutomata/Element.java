package CellularAutomata;

import Util.Color;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glColor3f;

public abstract class Element implements Cell {

    Color color = new Color(0,0,0);

    double density = 0;
    double inertia = 0;

    public double getInertia() {
        return inertia;
    }

    public void paint(double x, double y, int size) {
        glColor3f(color.r, color.g, color.b);

        glBegin(GL_QUADS);
        glVertex2d(x, y);              // Bottom-Left
        glVertex2d(x + size, y);       // Bottom-Right
        glVertex2d(x + size, y + size);// Top-Right
        glVertex2d(x, y + size);       // Top-Left
        glEnd();
    }

    public void step(Cell[][] matrix, int x, int y) {
    }

    protected boolean checkInsideBonds(Cell[][] matrix, int x, int y){
        return x < matrix.length && y < matrix[0].length && x >= 0 && y >= 0;
    }

    public boolean isMoreDenseThan(Cell element1){
        return this.density > element1.getDensity();
    }

    public double getDensity(){
        return density;
    }

    public boolean isSolid(){
        return false;
    }
}
