package CellularAutomata;

import java.util.Random;

public abstract class MovableSolid extends Element{

    int movementChance = 2;

    public MovableSolid(){
    }

    @Override
    protected void oneFrameStep(Cell[][] matrix, int x, int y) {
        if (checkInsideBonds(matrix, x, y)) {
            if (y + 1 < matrix[0].length) {
                if (matrix[x][y].isMoreDenseThan(matrix[x][y + 1]) && !matrix[x][y + 1].isSolid()) {
                    CellularMatrix.swap(x, x, y, y + 1);
                    movementChance = 2;
                    return;
                }
                boolean checkRightFirst = rand.nextBoolean();
                for (int i = 0; i < 2; i++) {
                    int dir = ((i == 0) == checkRightFirst) ? 1 : -1;
                    int targetX = x + dir;
                    if (targetX >= 0 && targetX < matrix.length) {
                        if (matrix[x][y].isMoreDenseThan(matrix[targetX][y + 1])
                                && !matrix[targetX][y + 1].isSolid()) {
                            if (rand.nextFloat() >= matrix[x][y].getInertia() && movementChance > 0) {
                                CellularMatrix.swap(x, targetX, y, y + 1);
                            }
                            movementChance--;
                            break;
                        }
                    }
                }
            }
        }
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
