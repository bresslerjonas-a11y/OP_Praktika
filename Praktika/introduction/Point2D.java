package introduction;

public class Point2D {
	
	int x = 0;
	int y = 0;
	
	public Point2D() {
		this.x = 0;
		this.y = 0;
	}

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point2D add(Point2D other) {
		this.x += other.x;
		this.y += other.y;
			return this; 
		}

	public double getDistanceFromOrigin() {
		return Math.sqrt(x * x + y * y);
	}
}
