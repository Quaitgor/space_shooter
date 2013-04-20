package factory;

import entites.decor.*;
import entities.*;
import entities.combat.Player;
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
	public Entity create(String Type, double posX, double posY, double rotation ){
		if(Type.equals("BarrierHit")){
			return new BarrierHit(posX, posY, rotation);
		}
		else{
			return null;
		}
		
		
	}
	public Entity create(String Type,Double posX, Double posY){
		if(Type.equals("TestenemyV2")){
			return new Player(posX, posY);
		}
		else if(Type.equals("Moon")){
			return new Moon(posX, posY);
		}
		else if(Type.equals("AstroidbeltL1")){
			return new AstroidBelt(posX, posY, 2);
		}
		else if(Type.equals("AstroidbeltL2")){
			return new AstroidBelt(posX, posY, 3);
		}
		else if(Type.equals("AstroidbeltL3")){
			return new AstroidBelt(posX, posY, 4);
		}
		else if(Type.equals("Stars")){
			return new Stars(posX, posY);
		}
		else if(Type.equals("Asteroid")){
			return new Asteroid(posX, posY);
		}
		else if(Type.equals("ForceField")){
			return new ForceField(posX, posY);
		}
		else if(Type.equals("Hit")){
			return new Hit(posX, posY);
		}
		else if(Type.equals("Electro")){
			return new Electro(posX, posY);
		}
		else{
			return null;
		}
	}
}
