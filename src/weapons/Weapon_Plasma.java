package weapons;

import entities.Entity;

public class Weapon_Plasma extends Weapon {

	public Weapon_Plasma(Entity owner) {
		super(owner);
	}

	@Override
	public void fire() {
		new projectiles.Plasma(owner.posX, owner.posY, 2, 4);
	}

}
