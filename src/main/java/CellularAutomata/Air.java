package CellularAutomata;

import Util.Color;

public class Air extends Gas {

    public Air(){
        float noise = rand.nextFloat() * 0.05f;
        color = new Color(noise, noise, noise);
        density = 0;
    }


    @Override
    protected void oneFrameStep(Cell[][] matrix, int x, int y) {
        return;
    }
}
