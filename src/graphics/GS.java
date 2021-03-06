package graphics;
import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;

import java.util.Vector;

import observer.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import ent_c.Player;
import entities_decor.*;
import entities.*;
import factory.*;

/**
 * GS is the Graphics-Synchronizer, GS controls the speed of the game, and keeps every element in sync
 * GS creates the Display and sets basic options for the Graphics and its resolution and speed
 * */
public class GS {	
    public static boolean isRunning = true;
    static long lastFrame;
	public static DeltaUpdater deltaUpdater;
	public static double delta = 0;
	public static Vector<Moveable> enemys;
	public static Vector<Moveable> friendlys;
	public static Entity player1;
	public static Entity player2;
	protected Collision colchecker = new Collision();
	public static Spawner levelgen;
	public static int level = 1;
	private static double resetTimer;
	private static boolean resetActive = false;
	public static final int FRAMEWIDTH = 1280;
	public static final int FRAMEHEIGHT = 768;
	private static String startlevel = "level1";
	
	
	public GS(String level) {
		GS.startlevel = level;
		start();
	}
	/**
	 * creates the display with the set options and keeps it open until a close is requested,
	 * every Frame the Loop inside the method updates the deltaUpdater Observer,
	 * which in turn updates every graphical element with the Observer Pattern
	 * */
	public void start() {
		deltaUpdater = new DeltaUpdater();
		enemys = new Vector<Moveable>();
		friendlys = new Vector<Moveable>();
		getDelta();
		// Run Graphics window until close request (X or Alt-F4)
		initGL(FRAMEWIDTH,FRAMEHEIGHT);
		initGame();
        while (isRunning) {
            if (Display.isCloseRequested()) {
           		isRunning = false;
            }
            render();
            checkCollision();
            updateInfo();
            Display.update();
            Display.sync(60);
            checkReset();
        }
        Display.destroy();
        System.exit(0);
    }
	
	/**
	 * Starts the menu and its controller
	 * */
	protected static void initGame(){
		new Moon(300, 400);
		new AstroidBelt(512,400, 2);
		new AstroidBelt(512,470, 3);
		new AstroidBelt(1024,640, 4);
		new Stars(640, 384);
		MenuController menu = new MenuController();
		menu.mainMenuPoint = 0;
		new BlackScreen(640, 384, false);
	}
	
	/**
	 * Method that resets the game after the timer hits 0
	 * */
	public static void checkReset(){
		if(resetActive){
			if (resetTimer > 0){
				resetTimer -= delta;
				if(resetTimer < 2000){
					new BlackScreen(640, 384, true);
				}
			}else{
				resetActive = false;
				deltaUpdater.clearObserver();
				enemys.clear();
				friendlys.clear();
				initGame();
			}
		}
	}
	
	/**
	 * Resets the Game after i time (ms);
	 * */
	public static void resetGame(int i){
		resetTimer = i;
		resetActive = true;
	}
	
	/**
	 * creates the levelgenerator and starts the game itself
	 * */
	public static void startGame(){
		new Player(-100, 384);
		levelgen = new Spawner(startlevel, deltaUpdater);
		//levelgen = new Spawner("level"+level, deltaUpdater);
	}
	
	/**
	 * checks if any object is making contact with another object
	 * objects are either friendly (player-side) or enemys, 
	 * only friendly vs enemy is checked for collisions.
	 * */
	protected void checkCollision(){
		for (Moveable friendlys: GS.friendlys){
			
			Moveable friend = (Moveable) friendlys;

			if(friend.isAlive == true){
        		for (Moveable enemys: GS.enemys){
        			if(enemys.isAlive == true){
            			Moveable enemy= (Moveable) enemys;
            			colchecker.intersects(friend,enemy);
            		}
    			}
			}
		}
	}
	
	/**
	 * updateInfo() displays information on the gamewindow
	 * */
	public void updateInfo() {
		Display.setTitle("ObsCount: " + deltaUpdater.getObserverNumber());
	}
	
	/**
	 * render() prepares the screen for the next frame and calulates
	 * the timedifference since the last calulation (delta) and
	 * updates the deltaUpdater with this delta
	 * */
	private void render(){
    	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        getDelta();
        GS.deltaUpdater.setDelta(delta);
	}
	
	/**
	 * initGL() sets up GL options and prepares the screen for starting the game 
	 * */
	private void initGL(int width, int height){
        try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setFullscreen(true);
			Display.create();
			Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glClearDepth(1.0f);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glViewport(0,0,width,height);
        GL11.glOrtho(0,width,height,0,0,128);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0);
        GL11.glCullFace(GL11.GL_BACK);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        GL11.glLoadIdentity();
	}
	
	/**
	 * getDelta() calulates the time since last calulation (delta) and returns it
	 * */
    private static double getDelta() {
        long currentTime = getTime();
        double delta1 = (double) (currentTime - lastFrame);
        lastFrame = getTime();
        delta = delta1;
        return delta;
    }
    
    /**
     * getTime() grabs the system Time and returns it
     * */
    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}
