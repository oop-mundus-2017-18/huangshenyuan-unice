package prototype.core;

import prototype.vivant.Vivant;

/**
 * @author HUANG Shenyuan
 * @date 2018-02-23 10:20
 * @email shenyuan.huang@etu.unice.fr
 */
public class Location {
    private boolean isVide;
    private Vivant vivant;

    Location() {
        isVide = true;
    }

    private void setVivant(Vivant vivant) {
        this.vivant = vivant;
    }
    
    Vivant getVivant() {
        return this.vivant;
    }
    
    boolean addVivant(Vivant vivant) {
        if(!isVide)
            return false;
        this.setVivant(vivant);
        return true;
            
    }

    boolean isVide() {
        return isVide;
    }

    void setOccupy() {
        isVide = false;
    }
    void exchangeVivant(Location location) {
        Vivant vivantA = location.getVivant();
        boolean stat = location.isVide;
        location.isVide = isVide;
        location.setVivant(this.vivant);
        this.setVivant(vivantA);
        isVide = stat;
    }
    
    @Override
    public String toString() {
        if(isVide) return "[     ]";
        else return "["+vivant+"]";
    }


}