package weapons;

import entities.Entity;

public class Weapon_Fire extends Weapon {

	public Weapon_Fire(Entity owner) {
		super(owner);
	}

	@Override
	public void fire() {
		new projectiles.Fire(owner.posX, owner.posY, 2, 4);
	}

}
