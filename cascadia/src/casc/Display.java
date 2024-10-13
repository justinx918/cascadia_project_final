package casc;

public class Display {
	private int x;
	private int y;
	private boolean isDisplayed;

	public Display() {
		isDisplayed = false;
	}

	public void setXY(int xx, int yy) {
		x = xx;
		y = yy;
	}

	public void setY(int yy) {
		y = yy;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
}
