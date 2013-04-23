package moveables;

import behaviours.*;
import movement.*;
import observer.Subject;
import weapons.*;

public class Moon extends Movable {
	protected Move movement;
	protected Behave behaviour;
	
	public Moon(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		
		//date from database?
		this.layer = 15;
		spritesPerRow = 1;
		spritesPerColumn = 1;
		//changeMovement(new RunAway(this, -2, 0));
		changeMovement(new RandomFlying(this, -2, 0));
		//super.setUpGraphics("moon");
		super.glC = new float[] {0.0f, 1.0f, 1.0f, 1.0f};
	}
	
	public void move(){
		movement.move();
	}
	protected void specialUpdate(){
		//this.move();
	}
	protected void changeMovement(Move newMove){
		this.movement = newMove;
	}
}
