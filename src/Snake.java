import java.util.ArrayList;

import processing.core.*;

public class Snake{
	private PApplet parent;
	private ArrayList<Cell> body = new ArrayList<Cell>();
	private int direction = 2; // 0=down, 1=up, 2=right, 3=left
	public void changeDirection(int newDirection) {
		direction = newDirection;
	}
	private int[] xDirection = {0,0, 1,-1}, yDirection = {1,-1,0,0};
	
	public Snake(PApplet parent) {
		this.parent = parent;
		body.add(new Cell(0, 15));	
	}
	public void addToHead() {
		int headX = getHead().getX();
		int headY = getHead().getY();
		Cell cell = new Cell(headX + xDirection[direction], headY + yDirection[direction]);
		body.add(0, cell);
	}
	public void truncateTail() {
		body.remove(body.size() - 1);
	}
	public void display() {
		parent.fill(56, 168, 50); 
		for (int i = 0; i < body.size(); i++) {
			int x = body.get(i).getX();
			int y = body.get(i).getY();
			parent.rect(x*Cell.getCellSize(), y*Cell.getCellSize(),
					Cell.getCellSize(), Cell.getCellSize());
		}	
	}
	public Cell getHead() {
		return body.get(0);
	}
	public boolean hasCollided() {
		for (int i = 1; i < body.size(); i++) {
			if (getHead().getX() == body.get(i).getX() 
					&& getHead().getY() == body.get(i).getY()) {
				return true;
			}
		}
		return false;
	}
	public int getBodySize() {
		return body.size();
	}
	public void clear() {
		body.clear();
		body.add(new Cell(0, 15));
		direction = 2;
	}
}
