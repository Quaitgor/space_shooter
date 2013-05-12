package powerups;

import ent_c.Transporter;

/**
 * This extends the Transporter Class to include a defined Upgrade and drop it on death.
 * */
public class IceTransport extends Transporter{
	
	public IceTransport(double posX, double posY) {
		super(posX, posY);
	}
	public void death(){
		new IcePower(this.posX, this.posY);
		super.death();
	}
}
