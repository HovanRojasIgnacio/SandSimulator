package CellularAutomata;

public abstract class Gas extends Element{


    public Gas(){

    }

    @Override
    protected void oneFrameStep(Cell[][] matrix, int x, int y) {

    }

    @Override
    public boolean isGas() {
        return true;
    }
}
