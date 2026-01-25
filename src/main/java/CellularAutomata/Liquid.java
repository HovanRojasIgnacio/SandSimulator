package CellularAutomata;

public abstract class Liquid extends Element {

    protected int dispersionRate = 10;

    public Liquid() {
        super();
    }

    @Override
    public boolean isLiquid() {
        return true;
    }

    @Override
    protected void oneFrameStep(Cell[][] matrix, int x, int y) {
        if (!checkInsideBounds(matrix, x, y)) return;

        if (canMoveTo(matrix, x, y + 1)) {
            moveTo(x, y, x, y + 1);
            setFreeFalling(true);
            return;
        }
        if (tryMoveDiagonally(matrix, x, y)) {
            return;
        }
        setFreeFalling(false);
        flowHorizontally(matrix, x, y);
    }

    private boolean tryMoveDiagonally(Cell[][] matrix, int x, int y) {
        boolean checkRightFirst = rand.nextBoolean();
        for (int i = 0; i < 2; i++) {
            int dir = ((i == 0) == checkRightFirst) ? 1 : -1;
            int targetX = x + dir;
            int targetY = y + 1;

            if (canMoveTo(matrix, targetX, targetY)) {
                moveTo(x, y, targetX, targetY);
                return true;
            }
        }
        return false;
    }

    private void flowHorizontally(Cell[][] matrix, int x, int y) {
        int dir = rand.nextBoolean() ? 1 : -1;
        int range = dispersionRate + (rand.nextBoolean() ? 1 : -1);

        int lastValidX = x;

        for (int i = 1; i <= range; i++) {
            int nextX = x + (i * dir);

            if (nextX < 0 || nextX >= matrix.length) break;

            Cell neighbor = matrix[nextX][y];
            if (neighbor.isSolid()) break;

            if (y + 1 < matrix[0].length) {
                Cell belowNeighbor = matrix[nextX][y + 1];
                if (belowNeighbor.isGas() || belowNeighbor.isLiquid()) {
                    if (canSwapWith(matrix, nextX, y)) {
                        lastValidX = nextX;
                    }
                    break;
                }
            }

            if (canSwapWith(matrix, nextX, y)) {
                lastValidX = nextX;
            } else {
                break;
            }
        }

        if (lastValidX != x) {
            moveTo(x, y, lastValidX, y);
        }
    }
}