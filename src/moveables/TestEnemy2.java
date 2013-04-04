package moveables;

import behaviours.*;
import movement.*;
import observer.Subject;
import weapons.*;

public class TestEnemy2 extends Movable {
	protected Weapon weapon;
	protected Move movement;
	protected Behave behaviour;
	
	public TestEnemy2(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		
		//date from database?
		this.layer = 15;
		spritesPerRow = 1;
		spritesPerColumn = 1;
		this.weapon = new Canon();
		//changeMovement(new RunAway(this, -2, 0));
		changeMovement(new RandomFlying(this, -2, 0));
		super.setUpGraphics("boss2");
		super.rotation = 45.0;
	}
	
	public void fire(){
		weapon.fire();
	}
	public void move(){
		movement.move();
	}
	protected void specialUpdate(){
		this.move();
	}
	protected void changeMovement(Move newMove){
		this.movement = newMove;
	}
}
