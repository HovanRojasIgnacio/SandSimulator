package Util;



public class Util{

    private static Util util;

    public static Util getInstance() {
        if(util == null){
            util = new Util();
        }
        return util;
    }

    private Util(){}

}
