package weapons;

import projectiles.Fire;
import entities.Entity;
import graphics.GS;





public class Weapon_Fire extends Weapon {
	
	public Weapon_Fire(Entity owner) {
		super(owner, true);
		weaponDelay = 200;
	}

	@Override
	public void fire() {
		counter -= owner.delta;
		if(counter <= 0){
			new Fire(owner, owner.posX+weaponOffset[0], owner.posY+weaponOffset[1], (GS.player1.posX + 5), GS.player1.posY);
			counter = weaponDelay;
		}
	}

}
