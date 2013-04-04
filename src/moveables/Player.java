package moveables;

import movement.*;
import inputs.*;
import observer.Subject;
import weapons.*;

public class Player extends Movable {
	public Weapon weapon;
	protected Input input;
	protected Move movement;
	
	public Player(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		
		//date from database?
		this.layer = 10;
		spritesPerRow = 4;
		spritesPerColumn = 4;
		this.weapon = new Canon();
		super.setUpGraphics("1spriteship");
		//setInput(new PlayerControl(this, 1));
		setMove(new PlayerMove(((Movable)this), 1));
	}
	
	public void fire(){
		weapon.fire();
	}
	public void setInput(Input newInput){
		this.input = newInput;
	}
	public void setMove(Move newMove){
		this.movement = newMove;
	}
	protected void specialUpdate(){
		//input.check();
		movement.move();
		checkSprite();
	}
	private void checkSprite(){
		//make the animation, improve
		int tempSpriteX = spriteX;
		int tempSpriteY = spriteY;
		anispeed = 500;
		if (anitimer <= anispeed-1) {
			spriteX = 0;
			spriteY = 0;
		}
		else if (anitimer >= 1*anispeed-1 && anitimer <= 2*anispeed) {
			spriteX = 1;
			spriteY = 0;
		}
		else if (anitimer >= 2*anispeed-1 && anitimer <= 3*anispeed) {
			spriteX = 0;
			spriteY = 1;
		}
		else if (anitimer >= 3*anispeed-1 && anitimer <= 4*anispeed) {
			spriteX = 1;
			spriteY = 1;
		}
		anitimer += this.delta;
		if (anitimer >= 4*anispeed) anitimer = 0;
		if (tempSpriteX != spriteX || tempSpriteY != spriteY) calculateSprite();
	}
}
