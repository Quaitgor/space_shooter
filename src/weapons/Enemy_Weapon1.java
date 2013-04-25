package weapons;

import entities.Entity;
import graphics.GS;

public class Enemy_Weapon1 extends Weapon {

	public Enemy_Weapon1(Entity owner) {
		super(owner);
	}

	@Override
	public void fire() {
		new projectiles.FireTest(owner, owner.posX, owner.posY, GS.player1.posX, GS.player1.posY);
	}

}
