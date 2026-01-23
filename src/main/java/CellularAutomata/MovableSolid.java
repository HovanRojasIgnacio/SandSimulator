package CellularAutomata;

public abstract class MovableSolid extends Element {

    public MovableSolid() {
        super();
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    protected void oneFrameStep(Cell[][] matrix, int x, int y) {
        if (!checkInsideBounds(matrix, x, y)) return;
        if (canMoveTo(matrix, x, y + 1)) {
            moveTo(x, y, x, y + 1);
            this.setFreeFalling(true);
            wakeUpNeighbors(matrix, x, y);
            return;
        }
        if (rand.nextFloat() < this.getInertia()) {
            this.setFreeFalling(false);
            return;
        }
        if (!isFreeFalling()) return;
        boolean checkRightFirst = rand.nextBoolean();
        for (int i = 0; i < 2; i++) {
            int dir = ((i == 0) == checkRightFirst) ? 1 : -1;
            int targetX = x + dir;
            int targetY = y + 1;
            if (canMoveTo(matrix, targetX, targetY)) {
                moveTo(x, y, targetX, targetY);
                this.setFreeFalling(true);
                wakeUpNeighbors(matrix, x, y);
                return;
            }
        }
        this.setFreeFalling(false);
    }

    private void wakeUpNeighbors(Cell[][] matrix, int x, int y) {
        wakeUpNeighbor(matrix, x - 1, y);
        wakeUpNeighbor(matrix, x + 1, y);
    }

    private void wakeUpNeighbor(Cell[][] matrix, int x, int y) {
        if (checkInsideBounds(matrix, x, y)) {
            matrix[x][y].setFreeFalling(true);
        }
    }
}