package prototype.core;

import static prototype.affair.Event.*;
import static prototype.affair.State.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import prototype.affair.Event;
import prototype.affair.State;
import prototype.vivant.Chicken;
import prototype.vivant.Human;
import prototype.vivant.Pig;
import prototype.vivant.Vivant;

/**
 * @author HUANG Shenyuan
 * @date 2018-03-01 23:15
 * @email shenyuan.huang@etu.unice.fr
 */
public class Simulator {
    private static final double INFECTED_CHANCE = 0.5;
    private static final double RECOVER_CHANCE = 0.5;
    private static final double DIE_CHANCE = 0.5;
    private static final double SICK_CHANCE = 0.5;
    private static final double SICK_ANIMAL_CHANCE = 0.5;
    private static final int NOMBER_HUMAN = 40;
    private static final int NOMBER_CHICKEN = 20;
    private static final int NOMBER_PIG = 20;
    private Sandbox sandbox = new Sandbox();
    Map<State, Map<Event, Supplier<State>>> dict = new HashMap<>();

    Simulator() {
        put(HEALTHY, CONTACT, () -> Math.random() < INFECTED_CHANCE ? HEALTHY : INFECTED);
        put(INFECTED, INCUBATION_TIME, () -> Math.random() < SICK_CHANCE ? CONTAGIOUS : INFECTED);
        put(CONTAGIOUS, CONTAGIOUS_TIME, () -> Math.random() < SICK_CHANCE ? CONTAGIOUS_AND_SICK : CONTAGIOUS_NOT_SICK);
        put(CONTAGIOUS_NOT_SICK, CONTAGIOUS_TIME, () -> Math.random() < SICK_CHANCE ? CONTAGIOUS_AND_SICK : RECOVERING);
        put(RECOVERING, RECOVERING_TIME, () -> Math.random() < RECOVER_CHANCE ? RECOVERED : RECOVERING);
        put(CONTAGIOUS_AND_SICK, CONTAGIOUS_TIME, () -> Math.random() < DIE_CHANCE ? CONTAGIOUS_AND_SICK : RECOVERING);
        
        for(int i=0;i<NOMBER_HUMAN;i++) {
            sandbox.addVivant(new Human());
        }
        for(int i=0;i<NOMBER_CHICKEN;i++) {
            Vivant aChicken = new Chicken();
            if(Math.random() < SICK_ANIMAL_CHANCE)
                aChicken.setState(CONTAGIOUS);
            sandbox.addVivant(aChicken);
        }
        for(int i=0;i<NOMBER_PIG;i++) {
            Vivant aPig = new Pig();
            if(Math.random() < SICK_ANIMAL_CHANCE)
                aPig.setState(CONTAGIOUS);
            sandbox.addVivant(aPig);
        }
    }
    

    void run(int days) {
        for(int i=0; i<days; i++) {
            move();
            /**
             * 
             * 
             * 
             * 
             * 
             * 
             */
        }
    }
    

    void put(State state, Event event, Supplier<State> todo) {
        if (dict.get(state) == null)
            dict.put(state, new HashMap<Event, Supplier<State>>());
        dict.get(state).put(event, todo);
    }
    
    void move() {
        sandbox.move();
    }
    
    @Override
    public String toString() {
        return sandbox.toString();
    }

}
