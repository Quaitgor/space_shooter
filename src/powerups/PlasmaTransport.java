package powerups;

import ent_c.Transporter;

/**
 * This is a WeaponUpgrade that changes the Weapon of the object picking it up
 * to a set weapon. It extends the Powerup class.
 * */
public class PlasmaTransport extends Transporter{
	
	public PlasmaTransport(double posX, double posY) {
		super(posX, posY);
	}
	public void death(){
		new PlasmaPower(this.posX, this.posY);
		super.death();
	}
}
