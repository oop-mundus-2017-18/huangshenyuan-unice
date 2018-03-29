package prototype;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import static prototype.State.*;
import static prototype.Event.*;

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
    Map<State, Map<Event, Supplier<State>>> dict = new HashMap<>();
    Simulator(){
        put(HEALTHY,CONTACT,()->Math.random()<INFECTED_CHANCE ? HEALTHY:INFECTED);
        put(INFECTED,INCUBATION_TIME,()->Math.random()<SICK_CHANCE ? CONTAGIOUS_AND_SICK:CONTAGIOUS_NOT_SICK);
        put(CONTAGIOUS_NOT_SICK,CONTAGIOUS_TIME,()->Math.random()<SICK_CHANCE ? CONTAGIOUS_AND_SICK:RECOVERING);
        put(RECOVERING,RECOVERING_TIME,()->Math.random()<RECOVER_CHANCE ? RECOVERED:RECOVERING);
        put(CONTAGIOUS_AND_SICK,CONTAGIOUS_TIME,()->Math.random()<DIE_CHANCE ? CONTAGIOUS_AND_SICK:RECOVERING);
    }
    void put(State state, Event event, Supplier<State> todo) {
        if (dict.get(state)==null) 
            dict.put(state, new HashMap<Event,Supplier<State>>());
        dict.get(state).put(event, todo);
    }

    
}
