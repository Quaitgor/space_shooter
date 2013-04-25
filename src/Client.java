import observer.*;
import graphics.*;
import factory.*;


public class Client {
	public static void main(String[] args) {
		DeltaUpdater deltaUpdater = new DeltaUpdater();
		Spawner LevelSpawner = new Spawner("asteroids", deltaUpdater);
		GS graphicSync = new GS(deltaUpdater);
		graphicSync.start();
	}
}
