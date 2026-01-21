package CellularAutomata;

import Util.Color;

public class Sand extends MovableSolid {


    public Sand(){
        super();
        float base = 0.8f + (rand.nextFloat() * 0.2f);
        float redNoise = rand.nextFloat() * 0.1f;
        color = new Color(base, base - 0.1f, redNoise);
        density = 2;
        inertia = 0.33;
    }





}
