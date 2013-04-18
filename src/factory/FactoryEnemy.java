package factory;

import entites.decor.Moon;
import entities.*;
import graphics.GS;
import observer.*;

public class FactoryEnemy {
	private DeltaUpdater deltaUpdater;
	
	public FactoryEnemy(DeltaUpdater deltaUpdater){
		this.deltaUpdater = deltaUpdater;
	}
	public Entity create(String Type){
		if(Type.equals("TestenemyV2")){
			return new Player(200, 200, deltaUpdater);
		}
		if(Type.equals("Moon")){
			return new Moon(400, 300, deltaUpdater);
		}
		else{
			return null;
		}
	}
}
