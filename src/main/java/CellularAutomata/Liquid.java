package CellularAutomata;

import java.util.Random;
import java.util.Vector;

public abstract class Liquid extends Element{

    protected int dispersionRate = 5;

    public Liquid(){

    }

    @Override
    protected void oneFrameStep(Cell[][] matrix, int x, int y) {
        if (!checkInsideBonds(matrix, x, y)) return;
        if (y + 1 < matrix[0].length) {
            if (this.isMoreDenseThan(matrix[x][y + 1]) && !matrix[x][y + 1].isSolid()) {
                CellularMatrix.swap(x, x, y, y + 1);
                return;
            }
            boolean checkRightFirst = rand.nextBoolean();
            for (int i = 0; i < 2; i++) {
                int dir = ((i == 0) == checkRightFirst) ? 1 : -1;
                int targetX = x + dir;
                if (targetX >= 0 && targetX < matrix.length) {
                    if (matrix[x][y].isMoreDenseThan(matrix[targetX][y + 1])
                            && !matrix[targetX][y + 1].isSolid()) {
                            CellularMatrix.swap(x, targetX, y, y + 1);
                            return;
                    }

                }
            }
        }
        int distance = (rand.nextBoolean()?-1:1) * (Math.random() > 0.5 ? dispersionRate + 2 : dispersionRate - 1);
        int distanceModifier = distance > 0 ? 1 : -1;
        int lastValidLocation = x;
        for(int i = 0; i<= Math.abs(distance);i++){
            int nextX = x + i * distanceModifier;
            if(nextX>=matrix.length || nextX <0){
                break;
            }
            Cell neighbour = matrix[nextX][y];
            if(neighbour.isSolid()){
                break;
            }
            if(neighbour.isGas()){
                lastValidLocation=nextX;
            }
        }
        CellularMatrix.swap(x,lastValidLocation,y,y);

    }

    @Override
    public boolean isLiquid() {
        return true;
    }


}
