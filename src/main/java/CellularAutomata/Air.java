package CellularAutomata;

import Util.Color;

public class Air extends Gas {

    public Air(){
        color = new Color(0,0,0);
        density = 0;
    }


    @Override
    protected void oneFrameStep(Cell[][] matrix, int x, int y) {
        return;
    }
}
