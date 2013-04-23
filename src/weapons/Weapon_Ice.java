package weapons;

import entities.Entity;

public class Weapon_Ice extends Weapon {

	public Weapon_Ice(Entity owner) {
		super(owner);
	}

	@Override
	public void fire() {
		new projectiles.Ice(owner.posX, owner.posY, 2, 4);
	}

}
