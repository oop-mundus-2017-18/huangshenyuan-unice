package prototype.core;

/**
 * @author HUANG Shenyuan
 * @date 2018-03-30 01:59
 * @email shenyuan.huang@etu.unice.fr
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Simulator test = new Simulator();
        System.out.println(test);
        System.out.println("\n\n\nAFTER MOVE:");
        test.move();
        System.out.println(test);

    }

}
