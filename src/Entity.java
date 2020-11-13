import processing.core.PApplet;

// Implementing classes must have update() and draw() methods
public interface Entity {
	public void update();
	public void draw(PApplet p); 
}
