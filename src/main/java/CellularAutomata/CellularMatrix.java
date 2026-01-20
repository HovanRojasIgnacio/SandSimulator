package CellularAutomata;

public class CellularMatrix {

    private Cell[][] matrix;

    private int height = 8;
    private int width = 8;

    public CellularMatrix(){
        matrix = new Cell[height][width];
    }

    public Cell[][] getMatrix() {
        return matrix;
    }
}
