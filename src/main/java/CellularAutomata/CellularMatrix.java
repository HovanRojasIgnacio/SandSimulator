package CellularAutomata;

public class CellularMatrix {


    public final static int WIDTH = 800;
    public final static int HEIGHT = 800;
    public final static int CELLSIZE = 4;
    private static final Cell[][] matrix = new Element[HEIGHT/ CELLSIZE][WIDTH/ CELLSIZE];


    public CellularMatrix(){
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                matrix[x][y]= new Air();
            }
        }
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

    public void setCell(double x, double y, Cell cell){
        if(x<0 || y<0 || x>=WIDTH || y>=HEIGHT){
            return;
        }
        int posX = (int) Math.floor(x/CELLSIZE);
        int posY = (int) Math.floor(y/CELLSIZE);
        matrix[posX][posY] = cell;
    }

    public void stepAll(){
        for(int i = matrix[0].length-1;i>=0;i--){
            for(int j = matrix.length-1;j>=0;j--){
                if( matrix[i][j] != null && !matrix[i][j].hasMoved())
                   matrix[i][j].step(matrix, i, j);
            }
        }
        reset();
    }

    public static void swap(int xFirst, int xSecond, int yFirst, int ySecond){
        Cell temporary = matrix[xFirst][yFirst];
        matrix[xFirst][yFirst] = matrix[xSecond][ySecond];
        matrix[xSecond][ySecond]=temporary;
    }

    private void reset(){
        for(int i = matrix[0].length-1;i>=0;i--){
            for(int j = matrix.length-1;j>=0;j--){
                if( matrix[i][j] != null)
                     matrix[i][j].reset();
            }
        }
    }
}
