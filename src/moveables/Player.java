package moveables;

import movement.*;
import observer.Subject;
import weapons.*;

public class Player extends Movable {
	public Weapon weapon;
	protected Move movement;
	protected double weaponSpriteWidth;
	
	public Player(double posX, double posY, Subject deltaUpdater) {
		super(posX, posY, deltaUpdater);
		System.out.println("Player old created");
		
		//date from database?
		this.layer = 10;
		spritesPerRow = 2;
		spritesPerColumn = 2;
		this.weapon = new Canon();
		//super.setUpGraphics("1spriteship");
		//setInput(new PlayerControl(this, 1));
		setMove(new PlayerMove(((Movable)this), 1));
	}

	public void fire(){
		weapon.fire(posX+spriteWidth/2+7,posY+2);
	}
	public void changeWeapon(Weapon weapon, int weaponSpriteWidth){
		this.weapon = weapon;
		this.weaponSpriteWidth = weaponSpriteWidth;
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
		//if (tempSpriteX != spriteX || tempSpriteY != spriteY) calculateSprite();
	}
}
