package CellularAutomata;

public interface Cell {


    void paint(double drawX, double drawY, int cellsize);

    void step(Cell[][] matrix, int i, int j);

    boolean isMoreDenseThan(Cell element);

    double getDensity();

    double getInertia();

    boolean isSolid();
}
