package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Main methods of a Grid Game, which interact with the controller and the view.
 */

public interface GameInterface {
	public void placePoint(double d, double e);
	public ArrayList<Point> getGridPoints();
	public ArrayList<Line> getListOfAlignment();
	public Set<Point> getPossiblePoint();
	public void randomSolve();
	public void restartGame();
	public void cancelPlay();
	void placePoint(int x, int y);
	public List<Point> getCrosses();
	public boolean canPlaceCross(int x, int y);
	public void placeCross(int x, int y);
	public boolean canPlaceCross(Point clickedPoint);
	public void placeCross(Point clickedPoint);

}
