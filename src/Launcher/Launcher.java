package Launcher;
import observer.*;
import graphics.*;

/**
 * Initializes all necessary objects and starts the games menu.
 * @author philipp
 *
 */
public class Launcher {
	public static void main(String[] args) {
		DeltaUpdater deltaUpdater = new DeltaUpdater();
		//Spawner LevelSpawner = new Spawner("level1", deltaUpdater);
		GS graphicSync = new GS(deltaUpdater);
		graphicSync.start();
	}
}
