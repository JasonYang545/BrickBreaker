import processing.core.PApplet;

public class Barrier extends Block{
	// constructor to make barrier with specific position 
    public Barrier (int row) {
    	x = row*150; 
    	w = 50;  
    	h = 10;
    	if (row%2 == 1) {
    		y = 400;
    	} 
    	else {
    		y = 300;
    	}
    }
    
    @Override
	public void draw(PApplet p) {
		p.fill(88);
		p.rect(x, y, w, h);
	}

	@Override
	public boolean collide(Bullet m) {
		if (m.x- m.w/2 > this.x + this.w) {return false;}  //left
		if (m.x+ m.w/2 < this.x) {return false;}  //right
		if (m.y- m.h/2 > this.y + this.h) {return false;}  //bottom
		if (m.y+ m.w/2 < this.y) {return false;}  //top
		return true;
	}

	@Override
	public void update() {}
}
