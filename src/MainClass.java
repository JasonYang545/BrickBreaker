import java.util.ArrayList;
import processing.core.PApplet;
import javax.swing.JOptionPane;


public class MainClass extends PApplet{
	// Declarations
	public int nCols=1200, nRows=600, score=0, bricksLeft=0;
	ArrayList<Entity> entities = new ArrayList<Entity>();
	Block b; Brick t; Bullet bullet; Barrier barr; Base base;
	
	public void setup(){
		// makes screen size
		size(nCols,nRows);
		// targets 
		for (int row=1; row<=5; row++) {
			for (int col=1; col<=10; col++) {
				t = new Brick(row,col);
				// add target object to entity 
		        entities.add(t);
		        bricksLeft++;
		    }
		}
		// barriers
		for (int row=1; row<=6; row++) {
		    barr = new Barrier(row);  
		    entities.add(barr);
		}
		//base and bullet
		base = new Base();
		bullet = new Bullet(base,nCols,nRows);
		entities.add(base);
		entities.add(bullet);
		//All game pieces to entities 
	}
	// end setup
	
	public void draw() { 
		EdgeCheck();  // blocks change direction when hitting edge
		// Tells entity to update: x,y,dX,dY
		for (Entity myentity: entities) {
		     myentity.update();
		}
		// Looks at each entity in ArrayList
		for (Entity myentity: entities) {
			// Block is either a barrier or target
			// checks if region of target hits bullet region, remove target if yes
			// if barrier, make bullet go other direction
			if (myentity instanceof  Block) {
		    	b = (Block) myentity;
		        if (b.collide(bullet)) {
		        	// entities looking for targets, if same col, remove too
		        	if (b instanceof Brick) {
		        		entities.remove(myentity);
		        		score++;
		        		bricksLeft--;
		        		if (bricksLeft==0) {
		        			int input = JOptionPane.showConfirmDialog(null, "Your Score: "+score+"\nPlay again?",null, 
		        					JOptionPane.YES_NO_OPTION);
		        			if(input == JOptionPane.OK_OPTION) {
		        				reset();
		        			}
		        			else {
		        				exit();
		        			}
		        		}
		        		System.out.println(score);
		        		// sets bullet on base -> bullet at bottom of screen
			        	if (bullet.dy<0) {
			        		bullet.setOnBase();
			        	}
			        	// otherwise bounce -> bullet at top of screen
			        	else {
			        		score+=2;
			        		bullet.dy=-bullet.dy; 
			        	}
			        	break; 
		        	}
		        	if (b instanceof Barrier) {
		        		// bounce off barrier
		        		bullet.dx = -bullet.dx; 
		        		bullet.dy=-bullet.dy;
		        	}
		        	// put bullet on base if collide
		        	if (b instanceof Base) {
		        		bullet.setOnBase();
		        	}
		        }
		    }
		}
		// redraw updated screen
		background(128);
		for (Entity myentity: entities) {
		    myentity.draw(this) ;
		}
	}

	// method for arrow key press
	public void keyPressed() { 
		if (keyCode == UP) {
			bullet.fired = true;}	
		// move base right 
		if (keyCode == RIGHT && (base.x+20<1160)) {
			base.x+= 20;
			if (!bullet.fired) {bullet.x+=20;}}
		// move base left
		if (keyCode == LEFT && bullet.x-20>20) {
			base.x-= 20;
			if (!bullet.fired) {bullet.x-=20;}}
	}
					 
	// checks if any entity his screen edge 
	public void EdgeCheck() {
		boolean hitEdge = false;
		for (Entity myentity: entities) {
		    if (myentity instanceof Block) {
		    	b = (Block) myentity;
		    	// if block(target/barrier)hits edge, switch to true
		        if ( b.x + b.w > nCols) {
		        	hitEdge = true;break;}
		        if ( b.x <0) {
		        	hitEdge = true;break;}
		    }	
	    }
		// if barrier or target hits edge, bounce
		if (hitEdge) {
			for (Entity myentity: entities) {
			    if (myentity instanceof  Brick )  {
			    	b = (Block) myentity;
			    	b.dx = -b.dx; 
			    	b.y +=5;
			    }	
		    } 
		}
	}
	
	// method to restart game given user input -> resets game pieces and running varaiables
	public void reset() {
		entities.clear();
		score=0;
		setup();
		draw();
	}
}



