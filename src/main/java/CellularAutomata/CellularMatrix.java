package CellularAutomata;

public class CellularMatrix {

    private Cell[][] matrix;

    public final static int WIDTH = 800;
    public final static int HEIGHT = 800;
    public final static int CELLSIZE = 4;

    public CellularMatrix(){
        matrix = new Cell[HEIGHT/ CELLSIZE][WIDTH/ CELLSIZE];
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public void draw(){
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                Cell cell = matrix[x][y];
                if (cell != null) {
                    double drawX = x * CELLSIZE;
                    double drawY = y * CELLSIZE;
                    cell.paint(drawX, drawY, CELLSIZE);
                }
            }
        }
    }

    public void setCell(double x, double y){
        if(x<0 || y<0 || x>=WIDTH || y>=HEIGHT){
            return;
        }
        int posX = (int) Math.floor(x/CELLSIZE);
        int posY = (int) Math.floor(y/CELLSIZE);
        matrix[posX][posY] = new Sand();
    }
}
