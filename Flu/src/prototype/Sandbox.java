package prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HUANG Shenyuan
 * @date 2018-02-23 09:23
 * @email shenyuan.huang@etu.unice.fr
 */
public class Sandbox {
    public static final int SIZEOFBOX = 100;
    private final static Sandbox MYWORLD = new Sandbox();
    private List<Vivant[][]> field = new ArrayList<Vivant[][]>();
    private boolean[][] stateOfVide;

    static Sandbox getSandbox() {
        return MYWORLD;
    }

    boolean isVide(Location location) {
        return stateOfVide[location.getX()][location.getY()];
    }

    private Sandbox() {
        for (int i = 0; i < SIZEOFBOX; i++) {
            for (int j = 0; j < SIZEOFBOX; j++) {
                stateOfVide[i][j] = true;
            }

        }
    }
}
