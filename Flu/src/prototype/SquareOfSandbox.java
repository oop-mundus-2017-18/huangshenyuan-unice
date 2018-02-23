/**
 * 
 */
package prototype;

/**
 * @author HUANG Shenyuan
 * @date 2018-02-23 10:20
 * @email shenyuan.huang@etu.unice.fr
 */
public class SquareOfSandbox {
    private final int x;
    private final int y;
    private Vivant vivant;

    SquareOfSandbox(int x, int y, Vivant vivant) {
        this.x = x;
        this.y = y;
        this.vivant = vivant;
    }

    SquareOfSandbox(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
