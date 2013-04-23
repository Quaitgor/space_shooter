package moveables;

import behaviours.*;
import movement.*;
import observer.Subject;
import weapons.*;

public class Background2 extends Movable {
	protected Weapon weapon;
	protected Move movement;
	protected Behave behaviour;
	
	public Background2(double posX, double posY, Subject deltaUpdater) {
		super(640, 384, deltaUpdater);
		
		//date from database?
		this.layer = 9;
		spritesPerRow = 1;
		spritesPerColumn = 1;
		this.weapon = new Canon();
		//changeMovement(new RunAway(this, -2, 0));
		changeMovement(new RandomFlying(this, -2, 0));
		//super.setUpGraphics("fog");
		super.boxWidth = 1280;
		super.boxHeight = 768;
		super.glC = new float[]{0.5f,0.5f,0.5f,0.5f};
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
