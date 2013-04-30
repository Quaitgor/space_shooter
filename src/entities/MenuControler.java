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
public class MenuControler extends Entity{
	private int mainMenuPoint = 0;
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
	public MenuControler(){
		super(640,384);
		System.out.println("created");
		mainTexture = new LayerData2(this, "player_big", 1, 1);
		mainTexture.layer = 60;
		mainScreenTex = new LayerData2(this, "mainscreen", 1, 1);
		mainScreenTex.layer = 61;
		mainScreenTex.pos[1] += 130;
		optionScreenTex = new LayerData2(this, "optionscreen", 1, 1);
		optionScreenTex.layer = 61;
		optionScreenTex.pos[1] += 130;
		addNewLayer(mainTexture);
		addNewLayer(mainScreenTex);
		addNewLayer(optionScreenTex);
		
		/*
		KeyMap = new HashMap<String, Integer>();
		KeyMap.put("Fire", 20);
		KeyMap.put("Charge", 20);
		KeyMap.put("AutoFire", 20);
		KeyMap.put("Up", 20);
		KeyMap.put("Down", 20);
		KeyMap.put("Right", 20);
		KeyMap.put("Left", 20);
		*/
		readJson();
		System.out.print(KeyMap.toString());
	}

	public void update(double delta) {
		super.update(delta);
		checkInput();
		renderCursor();
	}
	protected void renderCursor(){
		if(mainscreen){
			mainScreenTex.color[3] = 1f;
			optionScreenTex.color[3] = 0f;

			switch (mainMenuPoint){
			case(0):
				mainTexture.pos = new double[]{0, -30};
				break;
			case(1):
				mainTexture.pos = new double[]{0, 130};
				break;
			case(2):
				mainTexture.pos = new double[]{0, 310};
				break;
			}
		}
		if(optionscreen){
			optionScreenTex.color[3] = 1f;
			mainScreenTex.color[3] = 0f;
			switch (optionMenuPoint){
			case(0):
				mainTexture.pos = new double[]{-240, 30};
				break;
			case(1):
				mainTexture.pos = new double[]{-240, 130};
				break;
			case(2):
				mainTexture.pos = new double[]{-240, 230};
				break;
			case(3):
				mainTexture.pos = new double[]{240, 0};
				break;
			case(4):
				mainTexture.pos = new double[]{240, 80};
				break;
			case(5):
				mainTexture.pos = new double[]{240, 160};
				break;
			case(6):
				mainTexture.pos = new double[]{240, 250};
				break;
			case(7):
				mainTexture.pos = new double[]{0, 280};
				break;
			case(8):
				mainTexture.pos = new double[]{-0, 360};
				break;
			}
		}
		
	}
	protected void checkInput(){
		while (Keyboard.next()) {
        	if (Keyboard.getEventKeyState()) {
        		if(releaseLock){
        			getKey = false;
        			releaseLock = false;
        		}
        		if(getKey){
        			// JSON READ WRITE HERE
        			KeyMap.put(MenuPositions[optionMenuPoint]
        					, Keyboard.getEventKey());
        			overwriteJson();
        			System.out.println(Keyboard.getEventKey());
        			releaseLock = true;
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
