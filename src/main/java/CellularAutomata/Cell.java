package CellularAutomata;
import Util.Color;

public abstract class Cell {

    Color color = new Color(1,0,0);


    public Color getColor(){
        return this.color;
    }
}
