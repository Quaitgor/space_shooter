import observer.*;
import graphics.*;


public class Client {
	public static void main(String[] args) {
		DeltaUpdater deltaUpdater = new DeltaUpdater();
		GS graphicSync = new GS(deltaUpdater);
		graphicSync.start();
	}
}
