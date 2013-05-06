package entities;

/**
 * A special object that can be generated with the factory.
 * It changes the level when created.
 * */
import factory.Spawner;
import graphics.GS;
public class LevelChanger extends Mindless {
	
	public LevelChanger(double posX, double posY) {
		super(posX, posY);
		String newLevel = "level"+(GS.level+1);
		GS.levelgen = new Spawner(newLevel, GS.deltaUpdater);
		unsubscribe();
	}
}