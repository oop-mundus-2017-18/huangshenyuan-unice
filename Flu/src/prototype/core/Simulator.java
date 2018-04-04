package prototype.core;

import static prototype.affair.Event.*;
import static prototype.affair.State.*;
import static prototype.core.Sandbox.SIZE;

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
    private static final double ACCIDENT_CHANCE = 0.01;
    private static final int NOMBER_HUMAN = 20;
    private static final int NOMBER_CHICKEN = 10;
    private static final int NOMBER_PIG = 6;
    private Sandbox sandbox = new Sandbox();
    private final Map<State, Map<Event, Supplier<State>>> dict = new HashMap<>();

    Simulator() {
        buildDict();
        initial();
    }

    void run(int days) {
        for (int i = 0; i < days; i++) {
            move();
            for (int x = 0; x < SIZE; x++)
                for (int y = 0; y < SIZE; y++) {

                    Location location = sandbox.getLocation(x, y);
                    if (!location.isVide()) {
                        if (location.getVivant().toString().equals("H")) {
                            Event event = dectEvent(location);
                            State state1 = location.getVivant().getState();
                            State state2 = dict.get(state1).get(event).get();
                            sandbox.getLocation(x, y).getVivant().setState(state2);
                        }
                    }
                }
            System.out.println("DAY: " + i);
            System.out.println(this);
        }
    }

    Event dectEvent(Location loc) {
        Event event = NOTHING;
        Vivant centre = loc.getVivant();
        if (centre.getState().equals(CONTAGIOUS))
            return CONTAGIOUS_TIME;
        if (centre.getState().equals(CONTAGIOUS_NOT_SICK))
            return CONTAGIOUS_TIME;
        if (centre.getState().equals(CONTAGIOUS_AND_SICK))
            return CONTAGIOUS_TIME;
        if (centre.getState().equals(RECOVERING))
            return RECOVERING_TIME;
        if (centre.getState().equals(RECOVERED))
            return NOTHING;
        if (centre.getState().equals(INFECTED))
            return INCUBATION_TIME;
        if (centre.getState().equals(DEAD))
            return NOTHING;
        if (centre.getState().equals(HEALTHY)) {
            for (Location location : sandbox.getNeighbor(loc)) {
                if (!location.isVide()) {
                    Vivant vivant = location.getVivant();
                    switch (vivant.getState()) {
                    case HEALTHY:
                        break;
                    case CONTAGIOUS:
                        return CONTACT;
                    case CONTAGIOUS_AND_SICK:
                        return CONTACT;
                    case CONTAGIOUS_NOT_SICK:
                        return CONTACT;
                    default:
                        break;
                    }
                }

            }
        }
        return event;
    }

    void buildDict() {
        put(HEALTHY, CONTACT, () -> Math.random() < INFECTED_CHANCE ? HEALTHY : INFECTED);
        put(INFECTED, INCUBATION_TIME, () -> Math.random() < SICK_CHANCE ? CONTAGIOUS : INFECTED);
        put(CONTAGIOUS, CONTAGIOUS_TIME, () -> Math.random() < SICK_CHANCE ? CONTAGIOUS_AND_SICK : CONTAGIOUS_NOT_SICK);
        put(CONTAGIOUS_NOT_SICK, CONTAGIOUS_TIME, () -> Math.random() < SICK_CHANCE ? CONTAGIOUS_AND_SICK : RECOVERING);
        put(RECOVERING, RECOVERING_TIME, () -> Math.random() < RECOVER_CHANCE ? RECOVERED : RECOVERING);
        put(CONTAGIOUS_AND_SICK, CONTAGIOUS_TIME, () -> Math.random() < DIE_CHANCE ? DEAD : RECOVERING);
        put(HEALTHY, NOTHING, () -> Math.random() < ACCIDENT_CHANCE ? HEALTHY : DEAD);
        put(INFECTED, NOTHING, () -> Math.random() < ACCIDENT_CHANCE ? HEALTHY : DEAD);
        put(CONTAGIOUS, NOTHING, () -> Math.random() < ACCIDENT_CHANCE ? HEALTHY : DEAD);
        put(CONTAGIOUS_NOT_SICK, NOTHING, () -> Math.random() < ACCIDENT_CHANCE ? HEALTHY : DEAD);
        put(CONTAGIOUS_AND_SICK, NOTHING, () -> Math.random() < ACCIDENT_CHANCE ? HEALTHY : DEAD);
        put(DEAD, NOTHING, () -> Math.random() < ACCIDENT_CHANCE ? HEALTHY : DEAD);
        put(RECOVERING, NOTHING, () -> Math.random() < ACCIDENT_CHANCE ? HEALTHY : DEAD);
        put(RECOVERED, NOTHING, () -> Math.random() < ACCIDENT_CHANCE ? HEALTHY : DEAD);
        put(RECOVERING, NOTHING, () -> Math.random() < ACCIDENT_CHANCE ? HEALTHY : DEAD);

    }

    void initial() {
        for (int i = 0; i < NOMBER_HUMAN; i++) {
            sandbox.addVivant(new Human());
        }
        for (int i = 0; i < NOMBER_CHICKEN; i++) {
            Vivant aChicken = new Chicken();
            if (Math.random() < SICK_ANIMAL_CHANCE)
                aChicken.setState(CONTAGIOUS);
            sandbox.addVivant(aChicken);
        }
        for (int i = 0; i < NOMBER_PIG; i++) {
            Vivant aPig = new Pig();
            if (Math.random() < SICK_ANIMAL_CHANCE)
                aPig.setState(CONTAGIOUS);
            sandbox.addVivant(aPig);
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
