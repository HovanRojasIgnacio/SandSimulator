package CellularAutomata;

import Util.Color;

import java.util.Random;

public class Sand extends Element {



    public Sand(){
        Random rand = new Random();
        float base = 0.8f + (rand.nextFloat() * 0.2f);
        float redNoise = rand.nextFloat() * 0.1f;
        color = new Color(
                base,              // Red
                base - 0.1f,      // Green (less green = more orange)
                redNoise           // Blue (tiny bit adds "dustiness")
        );
        density = 2;
    }


    @Override
    public void step(Cell[][] matrix, int x, int y) {
        if(checkInsideBonds(matrix,x,y)) {
            if (y+1<matrix[0].length) {
                if(matrix[x][y].isMoreDenseThan(matrix[x][y+1])){
                    CellularMatrix.swap(x,x,y,y+1);

                }else if(x+1< matrix.length &&  matrix[x][y].isMoreDenseThan(matrix[x+1][y+1])){
                    CellularMatrix.swap(x,x+1,y,y+1);
                }else if(x-1>=0 && matrix[x][y].isMoreDenseThan(matrix[x-1][y+1])){
                    CellularMatrix.swap(x,x-1,y,y+1);
                }
            }
        }
    }



}
