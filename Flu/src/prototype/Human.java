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
    public void move() {
        
    }

    /* 
     * @see prototype.Vivant#die()
     */
    @Override
    public void die() {
        
    }

    /* 
     * @see prototype.Vivant#touch(prototype.Vivant)
     */
    @Override
    public void touch(Vivant vivant) {
        
    }



}
