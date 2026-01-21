package CellularAutomata;

import java.util.Random;

public abstract class Liquid extends Element{


    Random rand = new Random();

    public Liquid(){
    }

    @Override
    public void step(Cell[][] matrix, int x, int y) {
        if(checkInsideBonds(matrix,x,y)) {
            if (y+1<matrix[0].length) {
                if(matrix[x][y].isMoreDenseThan(matrix[x][y+1]) && !matrix[x][y+1].isSolid()){
                    CellularMatrix.swap(x,x,y,y+1);
                }else if(x+1< matrix.length && matrix[x][y].isMoreDenseThan(matrix[x+1][y])
                        && !matrix[x+1][y].isSolid()){
                        CellularMatrix.swap(x,x+1,y,y);
                }else if(x-1>=0 && matrix[x][y].isMoreDenseThan(matrix[x-1][y])
                        && !matrix[x-1][y].isSolid()){
                        CellularMatrix.swap(x,x-1,y,y);
                }
            }
        }
    }

}
