package entities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import graphics.GS;
import graphics.LayerData2;
import org.lwjgl.input.Keyboard;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import observer.Subject;

/**
 * Displays the entire menu appearing when the game is started. It also reads
 * and overwrites the changes done to the control-configurations.
 */
public class MenuController extends Entity{
	public int mainMenuPoint = 0;
	private int optionMenuPoint = 0;
	private boolean mainscreen = true;
	private boolean optionscreen = false;
	private boolean getKey = false;
	private boolean releaseLock = true;
	public double delta;
	protected Subject deltaUpdater;
	private LayerData2 mainTexture = null;
	private LayerData2 mainScreenTex = null;
	private LayerData2 optionScreenTex = null;
	private LayerData2 optionScreenTex2 = null;
	private String[] MenuPositions = {
			"Fire",
			"Charge",
			"AutoFire",
			"Up",
			"Down",
			"Right",
			"Left"
	};
	private static HashMap<String, Integer> KeyMap;
	private Gson GsonParser = new Gson();
	public static HashMap<String, Integer> getKeyMap(){return KeyMap;}
	
	
	public MenuController(){
		super(640,384);
		System.out.println("created");
		mainTexture = new LayerData2(this, "selector", 1, 1);
		mainTexture.layer = 60;
		mainScreenTex = new LayerData2(this, "mainscreen", 1, 1);
		mainScreenTex.layer = 61;
		mainScreenTex.pos[1] += 130;
		optionScreenTex = new LayerData2(this, "optionscreen", 1, 1);
		optionScreenTex.layer = 61;
		optionScreenTex.pos[1] += 130;
		optionScreenTex.color[3] = 0f;
		optionScreenTex2 = new LayerData2(this, "newkey", 1, 1);
		optionScreenTex2.layer = 61;
		optionScreenTex2.pos[1] += 130;
		optionScreenTex2.color[3] = 0f;
		addNewLayer(mainTexture);
		addNewLayer(mainScreenTex);
		addNewLayer(optionScreenTex);
		addNewLayer(optionScreenTex2);
		readJson();
	}

	/**
	 * Receives new delta-values, checks Keyboard-inputs and renders the Cursor.
	 * */
	public void update(double delta) {
		super.update(delta);
		checkInput();
		renderCursor();
	}
	
	/**
	 * Position and actions of the cursor.
	 * */
	protected void renderCursor(){
		if(mainscreen){
			mainTexture.color[3]  = 1f;
			optionScreenTex2.color[3] = 0f;	
			mainScreenTex.color[3] = 1f;
			optionScreenTex.color[3] = 0f;

			switch (mainMenuPoint){
			case(0):
				mainTexture.pos = new double[]{0, -20};
				mainTexture.spriteDisplayX = 290;
				break;
			case(1):
				mainTexture.pos = new double[]{0, 138};
				mainTexture.spriteDisplayX = 410;
				break;
			case(2):
				mainTexture.pos = new double[]{0, 310};
				mainTexture.spriteDisplayX = 238;
				break;
			}
		}
		if(getKey){
			optionScreenTex2.color[3] = 1f;
			mainScreenTex.color[3] = 0f;
			optionScreenTex.color[3] = 0f;
			mainTexture.color[3]  = 0f;
		}
		if(optionscreen){
			mainTexture.color[3]  = 1f;
			optionScreenTex2.color[3] = 0f;	
			optionScreenTex.color[3] = 1f;
			mainScreenTex.color[3] = 0f;
			switch (optionMenuPoint){
			case(0):
				mainTexture.pos = new double[]{-260, 50};
			mainTexture.spriteDisplayX = 180;
				break;
			case(1):
				mainTexture.pos = new double[]{-257, 121};
				mainTexture.spriteDisplayX = 280;
				break;
			case(2):
				mainTexture.pos = new double[]{-260, 192};
				mainTexture.spriteDisplayX = 380;
				break;
			case(3):
				mainTexture.pos = new double[]{253, 7};
			mainTexture.spriteDisplayX = 84;
				break;
			case(4):
				mainTexture.pos = new double[]{253, 78};
			mainTexture.spriteDisplayX = 186;
				break;
			case(5):
				mainTexture.pos = new double[]{253, 150};
			mainTexture.spriteDisplayX = 180;
				break;
			case(6):
				mainTexture.pos = new double[]{253, 221};
			mainTexture.spriteDisplayX = 228;
				break;
			case(7):
				mainTexture.pos = new double[]{0, 278};
			mainTexture.spriteDisplayX = 238;
				break;
			case(8):
				mainTexture.pos = new double[]{-0, 350};
			mainTexture.spriteDisplayX = 188;
				break;
			}
		}
		
	}
	
	/**
	 * Actions happening when Keys are pressed.
	 * */
	protected void checkInput(){
		while (Keyboard.next()) {
        	if (Keyboard.getEventKeyState()) {
        		if(releaseLock){
        			getKey = false;
        			releaseLock = false;
        		}
        		if(getKey){
        			KeyMap.put(MenuPositions[optionMenuPoint]
        					, Keyboard.getEventKey());
        			overwriteJson();
        			releaseLock = true;
					mainscreen = false;
					optionscreen = true;
        		}
        		if (Keyboard.getEventKey() == Keyboard.KEY_RETURN && !getKey) {
        			if(mainscreen){
        				switch (mainMenuPoint){
        				case(0):
        					unsubscribe();
        					GS.startGame();
        					break;
        				case(1):
        					mainscreen = false;
        					optionMenuPoint = 0;
        					optionscreen = true;
        					break;
        				case(2):
        					GS.isRunning = false;
        					break;
        				}
        			}
        			else if (optionscreen){
        				if (optionMenuPoint==0||optionMenuPoint==1||
        						optionMenuPoint==2||optionMenuPoint==3||
        						optionMenuPoint==4||optionMenuPoint==5||
        						optionMenuPoint==6){
        					getKey = true;
        					mainscreen = false;
        					optionscreen = false;
        				}
        				else if(optionMenuPoint==7){
        					KeyMap.put("Fire", Keyboard.KEY_A);
        					KeyMap.put("Charge", Keyboard.KEY_S);
        					KeyMap.put("AutoFire", Keyboard.KEY_D);
        					KeyMap.put("Up", Keyboard.KEY_UP);
        					KeyMap.put("Down", Keyboard.KEY_DOWN);
        					KeyMap.put("Right", Keyboard.KEY_RIGHT);
        					KeyMap.put("Left", Keyboard.KEY_LEFT);
        					overwriteJson();
        				}
        				else if(optionMenuPoint==8){
        					mainscreen = true;
        					optionscreen = false;
        				}
        			}
        		}
        		else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN && !getKey) {
        			if(mainscreen){
        				if(mainMenuPoint < 2){
        					mainMenuPoint++;
        				}else{
        					mainMenuPoint = 0;
        				}
        			}
        			if(optionscreen){
        				if(optionMenuPoint < 8){
        					optionMenuPoint++;
        				}else{
        					optionMenuPoint = 0;
        				}
        			}
        		}
        		else if (Keyboard.getEventKey() == Keyboard.KEY_UP && !getKey) {
        			if(mainscreen){
        				if(mainMenuPoint > 0){
        					mainMenuPoint--;
        				}else{
        					mainMenuPoint = 2;
        				}
        			}
        			if(optionscreen){
        				if(optionMenuPoint > 0){
        					optionMenuPoint--;
        				}else{
        					optionMenuPoint = 8;
        				}
        			}
        		}
        	}
        }
	}
	
	/**
	 * Method to read the JSON-file.
	 * */
	private void readJson(){
		try {
			KeyMap = GsonParser.fromJson(
					new FileReader("json/keyconfiguration.json")
					, (new TypeToken<HashMap<String,Integer>>(){}).getType());
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to Overwrite a Json file.
	 * */
	private void overwriteJson(){
		try {
			String einstring = GsonParser.toJson(KeyMap);
			System.out.print(einstring);
			FileWriter fw = new FileWriter("json/keyconfiguration.json");
			fw.write(einstring);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
