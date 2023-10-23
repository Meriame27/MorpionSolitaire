package application;

import java.util.ArrayList;
import java.util.Objects;

public class Line {
    private ArrayList<Point> linePoints;

    public Line() {
        this.linePoints = new ArrayList<>();
    }

    /**
     * Default constructor that initializes an empty list of points.
     */
    public Line(ArrayList<Point> linePoints) {
        this.linePoints = new ArrayList<>(Objects.requireNonNull(linePoints));
    }
    
    /**
     * Gets the points in this line.
     * @return A list of points in this line.
     */
    public ArrayList<Point> getLinePoints() {
        return new ArrayList<>(linePoints);
    }
}
