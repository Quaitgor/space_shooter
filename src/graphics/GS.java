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
	
	public GS() {
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
		// while running send out Delta to DeltaObserver
		initGL(1280,768);
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
	 * Starts the menu and its controler
	 * */
	protected static void initGame(){
		//levelgen = new Spawner("menu", deltaUpdater);
		new Moon(300, 400);
		new AstroidBelt(512,400, 2);
		new AstroidBelt(512,470, 3);
		new AstroidBelt(512,640, 4);
		new Stars(640, 384);
		new MenuController();
	}
	public static void checkReset(){
		if(resetActive){
			if (resetTimer > 0){
				resetTimer -= delta;
			}else{
				resetActive = false;
				deltaUpdater.clearObserver();
				enemys.clear();
				friendlys.clear();
				initGame();
			}
		}
	}
	public static void resetGame(int i){
		resetTimer = i;
		resetActive = true;
	}
	
	/**
	 * creates the levelgenerator and starts the game itself
	 * */
	public static void startGame(){
		System.out.println("Starting");
		//levelgen = new Spawner("boss", deltaUpdater);
		levelgen = new Spawner("level"+level, deltaUpdater);
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
			/*
    		if(crashed){
				new Hit(posX, posY);
				crashed = false;
			}
			*/
			//if(health <= 0) this.unsubscribe();
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
		//GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClearDepth(1.0f);
        //GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glDepthFunc(GL11.GL_ADD); //Wenn nicht auskommentiert führt es zu Exception
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glViewport(0,0,width,height);
        GL11.glOrtho(0,width,height,0,0,128);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glBlendEquation( BLENDING_EQUATIONS[blendingEquationIndex]);
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
