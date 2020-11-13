import processing.core.PApplet;

public class Bullet implements Entity{
    public Base base;
    int x,y,dx,dy,w,h;
    boolean fired = false;
    int nRows,nCols;
    
    // constructor makes missile with specified base
	public Bullet(Base inbase, int inCols,int inRows ) {
		base= inbase;
		w=20;
		h=30;
		nRows=inRows; 
		nCols=inCols;
		setOnBase();
	}
	
	public void update() {
		if (fired) {
			x = x+dx;  
			y = y+dy*2;
		}
		if (y-h/2 <0) {
			dy=-dy;
			}
		if (y+h/2 >nCols) {
			setOnBase();
		}
	}

	@Override
	public void draw(PApplet p) {
		p.fill(10,10,50);
		p.ellipse(x,y,w,h);
		
	}
	
	public void setOnBase() {
		x= base.x + base.w/2; 
		y= base.y-15;
		dx = 0; 
		dy=-5; 
		fired=false;
	}
}