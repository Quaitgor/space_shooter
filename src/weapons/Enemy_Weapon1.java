package weapons;

import entities.Entity;
import graphics.GS;

public class Enemy_Weapon1 extends Weapon {
	public Enemy_Weapon1(Entity owner) {
		super(owner);
		weaponDelay = 200;
	}

	@Override
	public void fire() {
		counter -= owner.delta;
		if(counter <= 0){
			new projectiles.FireTest(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], GS.player1.posX, GS.player1.posY);
			counter = weaponDelay;
		}
	}
}
