package prototype;

/**
 * @author HUANG Shenyuan
 * @date 2018-02-23 09:23
 * @email shenyuan.huang@etu.unice.fr
 */
public class Sandbox {
    public static final int SIZEOFBOX = 100;
    final Location[][] locations = new Location[SIZEOFBOX][SIZEOFBOX];

    /**
     * Build the sandbox which size is SIZEOFBOX^2
     */
    Sandbox() {
        for (int x = 0; x < SIZEOFBOX; x++)
            for (int y = 0; y < SIZEOFBOX; y++)
                locations[x][y] = new Location();
    }

    /**
     * Fill the sandbox with the vivants random
     */
    void intialVivant() {

    }

    Location[] getNeighor(int x, int y) {
        Location[] neighbor = new Location[8];
        neighbor[0] = locations[x][y + 1];
        neighbor[1] = locations[x - 1][y + 1];
        neighbor[2] = locations[x + 1][y + 1];
        neighbor[3] = locations[x][y - 1];
        neighbor[4] = locations[x + 1][y + 1];
        neighbor[5] = locations[x - 1][y + 1];
        neighbor[6] = locations[x + 1][y];
        neighbor[7] = locations[x - 1][y];
        return neighbor;

    }

}
