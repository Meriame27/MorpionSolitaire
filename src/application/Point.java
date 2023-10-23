package application;

import java.util.LinkedList;

public class Point {
    private int x;
    private int y;
    private LinkedList<Direction> dirAlignments;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.dirAlignments = new LinkedList<Direction>();
    }

    /**
     * Adds a direction to the list of alignments.
     *
     * @param dir the direction to add.
     * @throws IllegalArgumentException if the specified direction is null.
     */
    protected void addDirection(Direction dir) {
        if (dir == null) {
            throw new IllegalArgumentException("Direction cannot be null.");
        }
        this.dirAlignments.add(dir);
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return the x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return the y-coordinate.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Checks if the point is adjacent to another point in either x or y coordinate.
     *
     * @param other the other point to check.
     * @return true if the point is adjacent to the other point, false otherwise.
     */
    
    public boolean isNextTo(Point other) {
        int deltaX = Math.abs(this.x - other.x);
        int deltaY = Math.abs(this.y - other.y);
        
        // Vérifie si le point est adjacent en x ou en y, mais pas les deux (diagonales non comprises)
        return (deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1);
    }
    
    /**
     * Removes the last direction from the list of alignments.
     */
    protected void deleteLastDirection() {
        if (!dirAlignments.isEmpty()) {
            this.dirAlignments.removeLast();
        }
    }

    /**
     * Computes the hash code for the point based on its coordinates.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    /**
     * Checks if this point is equal to another object.
     *
     * @param obj the object to check.
     * @return true if the object is a Point with the same coordinates, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }

    /**
     * Returns a string representation of the point.
     *
     * @return a string representing the point.
     */
    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + "]";
    }
   
    /**
     * Gets the list of directions indicating alignments with other points.
     *
     * @return a list of directions.
     */
    protected LinkedList<Direction> getDirAlignments() {
        return new LinkedList<>(dirAlignments);
    }

    /**
     * Checks if this point is consecutive to another point.
     *
     * @param other the other point to check.
     * @return true if the points are consecutive, false otherwise.
     */
    public boolean isConsecutive(Point other) {
        return Math.abs(this.x - other.x) <= 1 && Math.abs(this.y - other.y) <= 1;
    }
}
