package CellularAutomata;

import Util.Color;

public interface Cell {



    void step(Cell[][] matrix, int i, int j);

    boolean isMoreDenseThan(Cell element);

    double getDensity();

    double getInertia();

    boolean isSolid();

    boolean isLiquid();

    boolean isGas();

    boolean hasMoved();

    void reset();

    boolean isFreeFalling();

    void setFreeFalling(boolean freeFalling);

    Color getColor();

}

