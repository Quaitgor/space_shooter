import observer.*;
import graphics.*;
import factory.*;


public class Client {
	public static void main(String[] args) {
		DeltaUpdater deltaUpdater = new DeltaUpdater();
		new Spawner("level1", deltaUpdater);
		//Spawner LevelSpawner = new Spawner("level1", deltaUpdater);
		GS graphicSync = new GS(deltaUpdater);
		graphicSync.start();
	}
}
