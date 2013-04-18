package moveables;

import observer.*;
import weapons.Canon;
import weapons.Weapon;

public class Enemy implements Observer {
	protected int health;
	protected Weapon weapon;
	double delta;
	private Subject deltaUpdater;

	public Enemy(Weapon Weapon, Subject getdeltaUpdater){
		this.weapon = Weapon;

		this.deltaUpdater = getdeltaUpdater;
		this.deltaUpdater.register(this);
	}
	public void fire(){
		//weapon.fire();
	}
	public void update(double delta) {
		this.delta = delta;
		
	}
}
