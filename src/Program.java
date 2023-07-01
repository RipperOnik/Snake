
import processing.core.*;
public class Program extends PApplet{
	int w; // window width in blocks
	int h;  // window height in blocks
	SnakeFood snakeFood = new SnakeFood(this, 15, 15);
	boolean gameover = false;
	int speed = 8;
	
	Snake snake = new Snake(this);

	public static void main(String[] args) {
		PApplet.main("Program");
	}
	@Override
	public void settings() {
		this.size(600, 600);
	}
	@Override
	public void setup() {
		// initialize snake at (0,15) 
		w = width / Cell.getCellSize();
		h = height / Cell.getCellSize();
	}
	
	@Override
	public void draw() {
		background(0);
		snake.display();
		
		if (!gameover) {
			snakeFood.display();
			renderScore();
			
			// we update the snake every 10 frames
			if (frameCount % speed == 0) {
				// add new element to the head
				snake.addToHead();
				// checking if snake is out of window
				if (snake.getHead().getX() < 0 || snake.getHead().getY() < 0
						|| snake.getHead().getX() > w || snake.getHead().getY() > h) {
					gameover = true;
				}
				// checking for collisions
				if (snake.hasCollided()) {
					gameover = true;
				}
				// if we find food, we create new food and skip tail truncation
				if (snake.getHead().getX() == snakeFood.getX() 
						&& snake.getHead().getY() == snakeFood.getY()) {
					// increase speed
					if (snake.getBodySize() % 3 == 0 && speed >= 2) {
						speed--;
					}
					snakeFood.setX((int)random(0, w));
					snakeFood.setY((int)random(0, h));
				}
				// to keep snake the same size, we remove the last element from the tail
				else {
					snake.truncateTail();
				}
			}
				
		}else {
			renderGameIsOver();
		}
	
	}
	@Override
	public void keyPressed() {
		int newDirection;
		if (keyCode == DOWN) {
			newDirection = 0;			
		}else if (keyCode == UP) {
			newDirection = 1;
			
		}else if (keyCode == RIGHT) {
			newDirection = 2;
		}else if (keyCode == LEFT) {
			newDirection = 3;
		}else {
			newDirection = -1;
		}
		
		if (newDirection != -1) {
			snake.changeDirection(newDirection);
		}
	}
	public void renderScore() {
		textAlign(LEFT);
		textSize(25);
		fill(255);
		text("Score: " + snake.getBodySize(), 10, 10, width - 20, 50);
	}
	public void renderGameIsOver() {
		fill(219, 186, 18);
		textSize(30);
		textAlign(CENTER);
		text("Game Over\n Your score is " + snake.getBodySize() +"\n Press Enter", width/2, height/3);
		if (keyCode == ENTER) {
			snake.clear();
			speed = 8;
			gameover = false;
		}
	}

}
