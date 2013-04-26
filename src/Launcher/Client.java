package Launcher;
import observer.*;
import graphics.*;


public class Client {
	public static void main(String[] args) {
		DeltaUpdater deltaUpdater = new DeltaUpdater();
		//Spawner LevelSpawner = new Spawner("level1", deltaUpdater);
		GS graphicSync = new GS(deltaUpdater);
		graphicSync.start();
	}
}
