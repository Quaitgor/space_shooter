package factory;

import entites.decor.*;
import entities.*;
import observer.*;

/**
 * @author  vmadmin
 */
public class FactoryEnemy {
	/**
	 * @uml.property  name="deltaUpdater"
	 * @uml.associationEnd  
	 */
	private DeltaUpdater deltaUpdater;
	
	public FactoryEnemy(DeltaUpdater deltaUpdater){
		this.deltaUpdater = deltaUpdater;
	}
	public Entity create(String Type,Double posX, Double posY){
		if(Type.equals("TestenemyV2")){
			return new Player(posX, posY, deltaUpdater);
		}
		if(Type.equals("Moon")){
			return new Moon(posX, posY, deltaUpdater);
		}
		if(Type.equals("AstroidbeltL1")){
			return new AstroidBelt(posX, posY, deltaUpdater, 2);
		}
		if(Type.equals("AstroidbeltL2")){
			return new AstroidBelt(posX, posY, deltaUpdater, 3);
		}
		if(Type.equals("AstroidbeltL3")){
			return new AstroidBelt(posX, posY, deltaUpdater, 4);
		}
		if(Type.equals("Stars")){
			return new Stars(posX, posY, deltaUpdater);
		}
		if(Type.equals("Asteroid")){
			return new Asteroid(posX, posY, deltaUpdater);
		}
		if(Type.equals("ForceField")){
			return new ForceField(posX, posY, deltaUpdater);
		}
		if(Type.equals("Hit")){
			return new Hit(posX, posY, deltaUpdater);
		}
		if(Type.equals("BarrierHit")){
			return new BarrierHit(posX, posY, deltaUpdater);
		}
		else{
			return null;
		}
	}
}
