import processing.core.PApplet;

public class SnakeFood extends Cell{
	PApplet parent;
	public SnakeFood(PApplet parent, int x, int y) {
		super(x, y);
		this.parent = parent;
	}
	public void display() {
		parent.fill(255);
		parent.rect(getX() * getCellSize(), getY() * getCellSize(), getCellSize(), getCellSize());
	}	
}
