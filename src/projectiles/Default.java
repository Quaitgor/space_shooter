package projectiles;

import behaviours.*;
import moveables.*;
import movement.*;
import observer.*;

public class Default extends Movable {
	protected Move movement;
	protected Behave behaviour;
	
	public Default(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY);
		super.setupDelta();
		//date from database?
		this.layer = 2;
		spritesPerRow = 1;
		spritesPerColumn = 4;
		//super.setUpGraphics("projectile/default");

		changeMovement(new Straight(this, -12, 0));
		//Looks like fire?
		//super.glC = new float[] {3.42f, 0.82f, 0.25f, 1.0f};
		
		super.boxWidth = 40;
		super.boxHeight = 30;
		//calculateSprite();
	}

	
	public void move(){
		movement.move();
	}
	protected void specialUpdate(){
		this.move();
		checkSprite();
	}
	protected void changeMovement(Move newMove){
		this.movement = newMove;
	}


	private void checkSprite(){
		//make the animation, improve
		int tempSpriteX = spriteX;
		int tempSpriteY = spriteY;
		anispeed = 50;
		if (anitimer <= anispeed-1) {
			spriteX = 0;
			spriteY = 1;
		}
		else if (anitimer >= 1*anispeed-1 && anitimer <= 2*anispeed) {
			spriteX = 0;
			spriteY = 2;
		}
		else if (anitimer >= 2*anispeed-1 && anitimer <= 3*anispeed) {
			spriteX = 0;
			spriteY = 3;
		}
		anitimer += this.delta;
		if (anitimer >= 3*anispeed) anitimer = 0;
		//if (tempSpriteX != spriteX || tempSpriteY != spriteY) calculateSprite();
	}
	
}
