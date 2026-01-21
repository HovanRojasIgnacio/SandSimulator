package CellularAutomata;

import java.util.Random;

public abstract class MovableSolid extends Element{

    int movementChance = 3;

    Random rand = new Random();

    public MovableSolid(){
    }

    @Override
    public void step(Cell[][] matrix, int x, int y) {
        if(checkInsideBonds(matrix,x,y)) {
            if (y+1<matrix[0].length) {
                if(matrix[x][y].isMoreDenseThan(matrix[x][y+1])){
                    CellularMatrix.swap(x,x,y,y+1);
                    movementChance=3;
                }else if(x+1< matrix.length &&  matrix[x][y].isMoreDenseThan(matrix[x+1][y+1])){
                    if(rand.nextFloat()>=matrix[x][y].getInertia() && movementChance>0){
                        CellularMatrix.swap(x,x+1,y,y+1);
                    }
                    movementChance--;
                }else if(x-1>=0 && matrix[x][y].isMoreDenseThan(matrix[x-1][y+1])){
                    if(rand.nextFloat()>=matrix[x][y].getInertia() && movementChance>0){
                        CellularMatrix.swap(x,x-1,y,y+1);
                    }
                    movementChance--;
                }
            }
        }
    }
}
