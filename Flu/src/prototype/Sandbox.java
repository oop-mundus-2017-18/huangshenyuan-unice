package prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author HUANG Shenyuan
 * @date 2018-02-23 09:23
 * @email shenyuan.huang@etu.unice.fr
 */
public class Sandbox {
    public static final int SIZE = 10;
    final Location[][] locations = new Location[SIZE][SIZE];
    List<Location> videSpace = new ArrayList<>();
    Random rand = new Random();

    /**
     * Build the sandbox which size is SIZEOFBOX^2
     */
    Sandbox() {
        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++) {
                locations[x][y] = new Location();
                videSpace.add(locations[x][y]);
            }
        ;
    }

    void addVivant(Vivant vivant) {
        if (videSpace.isEmpty())
            throw new RuntimeException("no space");
        int target = rand.nextInt(videSpace.size());
        videSpace.get(target).addVivant(vivant);
        videSpace.get(target).setOccupy();
        videSpace.remove(target);
    }

    void move() {
        for (int x = 0; x < SIZE; x++)
            for (int y = 0; y < SIZE; y++) {
                int target = rand.nextInt(7);
                Location[] neighbors = getNeighbor(x, y);
                locations[x][y].exchangeVivant(neighbors[target]);
            }
    }

    /**
     * @param x
     * @param y
     * @return the neighors it is a borderless map
     */
    Location[] getNeighbor(int x, int y) {
        int ym1 = y - 1;
        int yp1 = y + 1;
        int xm1 = x - 1;
        int xp1 = x + 1;
        if (y + 1 > SIZE - 1)
            yp1 = 0;
        if (y - 1 < 0)
            ym1 = SIZE - 1;
        if (x + 1 > SIZE - 1)
            xp1 = 0;
        if (x - 1 < 0)
            xm1 = SIZE - 1;
        Location[] neighbor = new Location[8];
        neighbor[0] = locations[x][yp1];
        neighbor[1] = locations[xm1][yp1];
        neighbor[2] = locations[xp1][yp1];
        neighbor[3] = locations[x][ym1];
        neighbor[4] = locations[xp1][yp1];
        neighbor[5] = locations[xm1][yp1];
        neighbor[6] = locations[xp1][y];
        neighbor[7] = locations[xm1][y];
        return neighbor;

    }

    @Override
    public String toString() {
        String table = "";
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                table += locations[x][y].toString();
            }
            table += "\n";
        }
        return table;
    }

}
