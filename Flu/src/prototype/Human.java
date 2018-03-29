package prototype;

/**
 * @author HUANG Shenyuan
 * @date 2018-02-23 09:06
 * @email shenyuan.huang@etu.unice.fr
 */
public class Human implements Vivant {
    Location location;
    State state;
    Human(Location location){
        state=State.HEALTHY;
        this.location=location;
    }
    /* 
     * @see prototype.Vivant#move()
     */
    @Override
    public void move(int addX, int addY) {
        
    }

}
