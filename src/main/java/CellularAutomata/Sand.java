package CellularAutomata;

import Util.Color;

public class Sand extends Cell{


    public Sand(){
        color = new Color(255,255,0);
    }


    @Override
    public void step(Cell[][] matrix, int x, int y) {
        if(checkInsideBonds(matrix,x,y)) {
            if (y+1<matrix[0].length) {
                if (matrix[x][y + 1] == null) {
                    matrix[x][y + 1] = this;
                    matrix[x][y] = null;
                }
            }
        }
    }
}
