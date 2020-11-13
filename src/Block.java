import processing.core.PApplet;

// Block can be either bullet base or barrier
public abstract class Block implements Entity{
	public int row,col,x,y,dx,dy,w,h;
    public abstract boolean collide(Bullet m); 
   }
 
