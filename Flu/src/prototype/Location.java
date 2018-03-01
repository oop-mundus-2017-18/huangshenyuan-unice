package prototype;

/**
 * @author HUANG Shenyuan
 * @date 2018-02-23 10:20
 * @email shenyuan.huang@etu.unice.fr
 */
public class Location {
    private final int x;
    private final int y;
    Location[] neighbor;

    Location(int x, int y) {
        this.x = x;
        this.y = y;

        neighbor[0] = new Location(x, y + 1);
        neighbor[1] = new Location(x - 1, y + 1);
        neighbor[2] = new Location(x + 1, y + 1);
        neighbor[3] = new Location(x, y - 1);
        neighbor[4] = new Location(x + 1, y + 1);
        neighbor[5] = new Location(x - 1, y + 1);
        neighbor[6] = new Location(x + 1, y);
        neighbor[7] = new Location(x - 1, y);

    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Location[] getNeighbor() {

        return neighbor;

    }
}
