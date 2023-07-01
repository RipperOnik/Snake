public class Cell {
	private static int cellSize = 20;
	public static int getCellSize() {
		return cellSize;
	}
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
