package application;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class BaseGrid implements GameInterface {
    private ArrayList<Point> GridPoints;
    private LinkedList<Line> listOfAlignment;
    private LinkedList<Point> PointPlayed;
    private int maxConsecutivePoints = 5; 

    public BaseGrid() {
        this.GridPoints = new ArrayList<Point>();
        this.listOfAlignment = new LinkedList<Line>();
        this.PointPlayed = new LinkedList<Point>();
        gridInitialization(10, 3, 4);
    }
    
    /**
     * Initializes the grid with a cross pattern.
     * 
     * @param x         X-coordinate of the center of the cross.
     * @param y         Y-coordinate of the center of the cross.
     * @param widthCross Width of the cross.
     */
    private void gridInitialization(int x, int y, int widthCross) {
        if (widthCross <= 3) {
            throw new IllegalArgumentException();
        }
        this.GridPoints = new ArrayList<Point>();
        for (int i = 0; i < widthCross; ++i) {
            this.GridPoints.add(new Point(x + i, y));
            this.GridPoints.add(new Point(x + i - widthCross + 1, y + widthCross - 1));
            this.GridPoints.add(new Point(x + i + widthCross - 1, y + widthCross - 1));
            this.GridPoints.add(new Point(x + i - widthCross + 1, y + 2 * widthCross - 2));
            this.GridPoints.add(new Point(x + i + widthCross - 1, y + 2 * widthCross - 2));
            this.GridPoints.add(new Point(x + i, y + 3 * widthCross - 3));
            this.GridPoints.add(new Point(x, y + i));
            this.GridPoints.add(new Point(x + widthCross - 1, y + i));
            this.GridPoints.add(new Point(x - widthCross + 1, y + i + widthCross - 1));
            this.GridPoints.add(new Point(x + 2 * widthCross - 2, y + i + widthCross - 1));
            this.GridPoints.add(new Point(x, y + i + 2 * widthCross - 2));
            this.GridPoints.add(new Point(x + widthCross - 1, y + i + 2 * widthCross - 2));
        }
    }

    /**
     * Returns a string representation of the grid points.
     * 
     * @return String representation of the grid points.
     */
    public String toString() {
        return this.GridPoints.toString();
    }
    
    /**
     * Identifies aligned points in a specified direction.
     * 
     * @param x                     X-coordinate of the starting point.
     * @param y                     Y-coordinate of the starting point.
     * @param dir                   Direction to check for alignment.
     * @param alignNumber           Number of points to check for alignment.
     * @param nbPointAlreadyAligned Number of points already aligned.
     * @return List of aligned points.
     */
    private LinkedList<Point> AlignedPointsByDirection(int x, int y, Direction dir, int alignNumber, int nbPointAlreadyAligned) {
        if (alignNumber < 1 || nbPointAlreadyAligned < 0) {
            throw new IllegalArgumentException();
        }
        Objects.requireNonNull(dir);
        LinkedList<Point> alignPoint = new LinkedList<Point>();
        alignPoint.add(new Point(x, y));
        int cptPointAlreadyAligned = 0;
        for (int j = 1; this.GridPoints.contains(new Point(x + dir.getDx() * j, y + dir.getDy() * j))
                && alignPoint.size() <= alignNumber - 1; ++j) {
            Point p = this.GridPoints.get(this.GridPoints.indexOf(new Point(x + dir.getDx() * j, y + dir.getDy() * j)));
            if (p.getDirAlignments().contains(dir)) {
                ++cptPointAlreadyAligned;
                if (cptPointAlreadyAligned > nbPointAlreadyAligned) {
                    break;
                }
            }
            alignPoint.add(p);
        }
        for (int j = 1; this.GridPoints.contains(new Point(x - dir.getDx() * j, y - dir.getDy() * j))
                && alignPoint.size() <= alignNumber - 1; ++j) {
            Point p = this.GridPoints.get(this.GridPoints.indexOf(new Point(x - dir.getDx() * j, y - dir.getDy() * j)));
            if (p.getDirAlignments().contains(dir)) {
                ++cptPointAlreadyAligned;
                if (cptPointAlreadyAligned > nbPointAlreadyAligned) {
                    break;
                }
            }
            alignPoint.add(p);
        }
        return alignPoint;
    }
    
    /**
     * Checks if points are aligned in a specified direction.
     */
    protected boolean isAligned(int x, int y, int alignmentNumber, int nbPointAlreadyAligned) {
        for (Direction d : Direction.values()) {
            if (AlignedPointsByDirection(x, y, d, alignmentNumber, nbPointAlreadyAligned).size() >= alignmentNumber) {
                LinkedList<Point> alignment = AlignedPointsByDirection(x, y, d, alignmentNumber, nbPointAlreadyAligned);
                if (containsMoreThanMaxConsecutivePoints(alignment)) {
                    return false;
                }
                listOfAlignment.add(new Line(new ArrayList<Point>(alignment)));
                for (Point p : alignment) {
                    p.addDirection(d);
                }
                GridPoints.add(alignment.getFirst());
                PointPlayed.add(alignment.getFirst());
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the list of points contains more than the maximum number of consecutive points.
     */
    private boolean containsMoreThanMaxConsecutivePoints(LinkedList<Point> points) {
        int consecutiveCount = 1;
        Point prevPoint = null;

        for (Point point : points) {
            if ((prevPoint != null) && (point.isConsecutive(prevPoint))) {
                consecutiveCount++;
                if (consecutiveCount > maxConsecutivePoints) {
                    return true;
                }
            } else {
                consecutiveCount = 1;
            }

            prevPoint = point;
        }

        return false;
    }

    /**
     * Returns a copy of the grid points list.
     */
    public ArrayList<Point> getGridPoints() {
        return new ArrayList<Point>(this.GridPoints);
    }
    
    /**
     * Returns a copy of the list of alignments.
     */
    public ArrayList<Line> getListOfAlignment() {
        return new ArrayList<Line>(this.listOfAlignment);
    }

    /**
     * Returns the set of neighboring points.
     */
    private Set<Point> getNeighborsPoints() {
        Set<Point> setPoints = new LinkedHashSet<Point>();
        for (Point p : this.GridPoints) {
            for (Direction d : Direction.values()) {
                setPoints.add(new Point(p.getX() + d.getDx(), p.getY() + d.getDy()));
                setPoints.add(new Point(p.getX() - d.getDx(), p.getY() - d.getDy()));
            }

        }
        setPoints.removeAll(this.GridPoints);
        return setPoints;
    }

    /**
     * Returns the set of possible points.
     */
    public Set<Point> getPossiblePoint(int nbPointForAlign, int nbPointsAlreadyALigned) {
        Set<Point> setPossiblePoints = new LinkedHashSet<Point>();
        for (Point p : this.getNeighborsPoints()) {
            if (this.isAligned(p.getX(), p.getY(), nbPointForAlign, nbPointsAlreadyALigned)) {
                setPossiblePoints.add(p);
            }
        }

        return new LinkedHashSet<Point>(setPossiblePoints);
    }

    /**
     * Returns the list of played points.
     */
    public LinkedList<Point> getPointPlayed() {
        return PointPlayed;
    }

    /**
     * Restarts the game by resetting the grid and resetting game variables.
     */
    public void restartGame() {
        this.gridInitialization(10, 3, 4);
        this.PointPlayed = new LinkedList<Point>();
        this.listOfAlignment = new LinkedList<Line>();
    }
    
    /**
     * Cancels the last play.
     */

    public void cancelPlay() {
        if (this.getPointPlayed().size() > 0) {
            Point lasPointPlaced = this.PointPlayed.getLast();
            Line line = this.listOfAlignment.getLast();
            for (Point p : line.getLinePoints()) {
                p.deleteLastDirection();
            }
            this.GridPoints.remove(lasPointPlaced);
            this.PointPlayed.removeLast();
            this.listOfAlignment.removeLast();
        }
    }
    
    /**
     * Places a point on the grid at specified coordinates if alignment conditions are met.
     */
    public void placePoint(int x, int y) {
        if (isAligned(x, y, 5, 0)) {
            Point point = new Point(x, y);
            GridPoints.add(point);
            PointPlayed.add(point);
        }
    }
    
    /**
     * Returns the list of crosses.
     */
    public List<Point> getCrosses() {
        return PointPlayed;
    }
    

    /**
     * Checks if a cross can be placed.
     */
    public boolean canPlaceCross(Point point) {
        return getPossiblePoint(5, 0).contains(point);
    }

    /**
     * Places a cross on the grid.
     */
    public void placeCross(Point point) {
        if (canPlaceCross(point)) {
            placePoint(point.getX(), point.getY());
        }
    }

    /**
     * Returns the set of possible points.
     */
	public Set<Point> getPossiblePoints() {
		// TODO Auto-generated method stub
		return null;
	}

}
