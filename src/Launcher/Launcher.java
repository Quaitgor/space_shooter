package Launcher;
import graphics.*;

/**
 * Starts the game. The String passed to GS specifies the level which will be 
 * initialized first.
 */
public class Launcher {
	public static void main(String[] args) {
		new GS("level1");
	}
}
