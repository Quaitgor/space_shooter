package moveables;

import behaviours.*;
import movement.*;
import observer.Subject;
import weapons.*;

public class Background1 extends Movable {
	protected Weapon weapon;
	protected Move movement;
	protected Behave behaviour;
	
	public Background1(double posX, double posY, Subject deltaUpdater) {
		super(640, 384, deltaUpdater);
		
		//date from database?
		this.layer = 20;
		spritesPerRow = 1;
		spritesPerColumn = 1;
		this.weapon = new Canon();
		//changeMovement(new RunAway(this, -2, 0));
		changeMovement(new RandomFlying(this, -2, 0));
		//super.setUpGraphics("bg1");
		super.boxWidth = 1280;
		super.boxHeight = 768;
	}
	
	public void fire(){
		//weapon.fire();
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
