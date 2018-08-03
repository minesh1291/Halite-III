package hlt;

public class GameMap {
    private int width, height;
    private int[][] grid;

    public GameMap(int w, int h, int[][] g) {
        width = w;
        height = h;
        grid = g;
    }

    public int computeDistance(Location f, Location s) {
        int x_dist = Math.abs(f.x - s.x);
        int y_dist = Math.abs(f.y - s.y);
        return Math.min(x_dist, width - x_dist)
            + Math.min(y_dist, height - y_dist);
    }

    public int getHalite(Location l) { return grid[l.y][l.x]; }
    public int[][] getGrid() { return grid; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setSquare(int x, int y, int halite) {
        grid[y][x] = halite;
    }

    public Location getLocation(Location l, Direction d) {
        if(d == Direction.NORTH) {
            l.y--;
            if(l.y == -1) l.y = height-1;
        }
        else if(d == Direction.EAST) {
            l.x++;
            if(l.x == width) l.x = 0;
        }
        else if(d == Direction.SOUTH) {
            l.y++;
            if(l.y == height) l.y = 0;
        }
        else if(d == Direction.WEST) {
            l.x--;
            if(l.x == -1) l.x = width-1;
        }
        return l;
    }

    public Location normalize(Location l) {
        l.x = (l.x + width) % width;
        l.y = (l.y + height) % height;
        return l;
    }

    public Location locationWithDirection(Location l, Direction d) {
        return normalize(getLocation(l.clone(), d));
    }

    public Direction towards(Location start, Location target) {
        if (start.x != target.x) {
            int east_dist = 0;
            int west_dist = 0;
            if (start.x > target.x) {
                west_dist = start.x - target.x;
                east_dist = width - west_dist;
            }
            else {
                east_dist = target.x - start.x;
                west_dist = width - east_dist;
            }

            if (west_dist < east_dist) {
                return Direction.WEST;
            }
            return Direction.EAST;
        }
        else if (start.y != target.y) {
            int north_dist = 0;
            int south_dist = 0;

            if (start.y > target.y) {
                north_dist = start.y - target.y;
                south_dist = height - north_dist;
            }
            else {
                south_dist = start.y - target.y;
                north_dist = height - south_dist;
            }

            if (north_dist < south_dist) {
                return Direction.NORTH;
            }
            return Direction.SOUTH;
        }
        return Direction.STILL;
    }
}
