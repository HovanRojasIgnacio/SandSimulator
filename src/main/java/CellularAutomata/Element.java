package CellularAutomata;

import Util.Color;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glColor3f;

public abstract class Element implements Cell {

    protected static final Random rand = new Random();
    Color color = new Color(0,0,0);
    double density = 0;
    double inertia = 0;
    boolean hasMoved = false;
    boolean freeFalling = true;

    public double getInertia() {
        return inertia;
    }

    public void step(Cell[][] matrix, int x, int y) {
        hasMoved = true;
        oneFrameStep(matrix,x,y);
    }

    protected abstract void oneFrameStep(Cell[][] matrix, int x, int y);

    protected boolean checkInsideBounds(Cell[][] matrix, int x, int y){
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

    public boolean hasMoved(){
        return hasMoved;
    }

    public void reset(){
        hasMoved=false;
    }

    public boolean isGas() {
        return false;
    }

    public boolean isLiquid(){
        return false;
    }

    public boolean isFreeFalling() {
        return freeFalling;
    }

    public void setFreeFalling(boolean freeFalling) {
        this.freeFalling = freeFalling;
    }

    protected boolean canMoveTo(Cell[][] matrix, int targetX, int targetY) {
        if (!checkInsideBounds(matrix, targetX, targetY)) {
            return false;
        }
        Cell target = matrix[targetX][targetY];

        if (target.isSolid()) return false;

        return this.isMoreDenseThan(target);
    }

    protected boolean canSwapWith(Cell[][] matrix, int targetX, int targetY) {
        Cell target = matrix[targetX][targetY];
        return !target.isSolid() && this.isMoreDenseThan(target);
    }

    protected void moveTo(int x1, int y1, int x2, int y2) {
        CellularMatrix.swap(x1, x2, y1, y2);
    }

    public Color getColor() {
        return color;
    }
}
