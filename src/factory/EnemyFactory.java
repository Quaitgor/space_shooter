package factory;

import graphics.GS;
import moveables.*;
import observer.*;

/**
 * @author  vmadmin
 */
public class EnemyFactory {
	/**
	 * @uml.property  name="deltaUpdater"
	 * @uml.associationEnd  
	 */
	private DeltaUpdater deltaUpdater;
	
	public EnemyFactory(DeltaUpdater deltaUpdater){
		this.deltaUpdater = deltaUpdater;
	}
	
	public Movable create(String Type){
		if(Type.equals("Standard-Gegner")){
			//return new Enemy(new Canon(),deltaUpdater);
		}
		else if(Type.equals("Schwacher-Gegner")){
			//return new Enemy(new Laser(),deltaUpdater);
		}

		if(Type.equals("Testenemy")){
			return new TestEnemy2(200, 200, deltaUpdater);
		}
		if(Type.equals("Testenemy2")){
			return new TestEnemy2(400, 200, deltaUpdater);
		}
		if(Type.equals("Player")){
			GS.player = new Player(200, 200, deltaUpdater);
			return GS.player;
		}
		if(Type.equals("Moon")){
			return new Moon(400, 200, deltaUpdater);
		}
		if(Type.equals("Background1")){
			return new Background1(400, 200, deltaUpdater);
		}
		if(Type.equals("Background2")){
			return new Background2(400, 200, deltaUpdater);
		}
		else{
			return null;
		}
	}
}
