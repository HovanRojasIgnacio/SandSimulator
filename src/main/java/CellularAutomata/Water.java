package CellularAutomata;

import Util.Color;

public class Water extends Liquid {


    public Water(){
        super();
        float base = 0.8f + (rand.nextFloat() * 0.2f);
        float redNoise = rand.nextFloat() * 0.1f;
        color = new Color(redNoise, redNoise, base);
        density = 1;
    }





}
