package application;

public enum Direction {
    VERTICAL(0,-1),HORIZONTAL(-1,0),DIAGONALRIGHT(1,1),DIAGONALLEFT(1,-1);

	private final int dx, dy;

	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
     * Gets the delta value for the x-coordinate.
     * @return The delta x-value.
     */
	public int getDx() {
		return dx;
	}

	/**
     * Gets the delta value for the y-coordinate.
     * @return The delta y-value.
     */
	public int getDy() {
		return dy;
	}

}
