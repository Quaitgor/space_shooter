package factory;

import entities.*;
import observer.*;

/**
 * @author  vmadmin
 */
public class ProjectileFactory {
	/**
	 * @uml.property  name="deltaUpdater"
	 * @uml.associationEnd  
	 */
	private DeltaUpdater deltaUpdater;
	
	public ProjectileFactory(DeltaUpdater deltaUpdater){
		this.deltaUpdater = deltaUpdater;
	}
	
	public Entity create(String Type, double posX, double posY){
		if(Type.equals("Plasma")){
			return new projectiles.Plasma(posX, posY, deltaUpdater);
		}
		else if(Type.equals("Ice")){
			return new projectiles.Ice(posX, posY, deltaUpdater);
		}
		else if(Type.equals("Fire")){
			return new projectiles.Fire(posX, posY, deltaUpdater);
		}
		else if(Type.equals("Default")){
			//return new projectiles.Default(posX, posY, deltaUpdater);
			return null;
		}
		else{
			return null;
		}
	}
}
