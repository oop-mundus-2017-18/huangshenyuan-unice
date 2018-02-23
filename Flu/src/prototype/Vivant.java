package prototype;

/**
 * @author HUANG Shenyuan
 * @date 2018-02-23 08:45
 * @email shenyuan.huang@etu.unice.fr
 */
public interface Vivant {

    
    /**
     * Every vivant can move
     */
    void move();

    /**
     * Every vivant may die
     */
    void die();

    /**
     * @param vivant
     * Every vivant may contact with each other
     */
    void touch(Vivant vivant);
}
