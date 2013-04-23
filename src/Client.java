import observer.*;
import menu.*;
import graphics.*;
import factory.*;


public class Client {
	public static void main(String[] args) {
		Menu Options = new Menu();
	}
	public static void initiate(String level){
		DeltaUpdater deltaUpdater = new DeltaUpdater();
		new Spawner(level, deltaUpdater);
		//Spawner LevelSpawner = new Spawner("level1", deltaUpdater);
		GS graphicSync = new GS(deltaUpdater);
		graphicSync.start();
	}
}
