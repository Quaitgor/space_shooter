package entities;

import graphics.GS;
import graphics.LayerData2;

import java.util.Vector;

import movementV2.Move;

import observer.*;

/**
 * Entitiy is the superclass of each visual object, together with LayerData and with objects added to the subclasses via strategic pattern the objects can move, change visual (animation) and fire
 */
public abstract class Entity implements Observer {
	protected int health;
	public double delta;
	/**
	 * @uml.property  name="deltaUpdater"
	 * @uml.associationEnd  
	 */
	protected Subject deltaUpdater;
    public double posX;
	public double posY;
	// Graphics Variables
    protected int anitimer = 0;
	public Vector<LayerData2> LayerDatas;
	protected int maintexture = 0;

	
	public Entity(double newPosX, double newPosY, Subject getdeltaUpdater){
		this.deltaUpdater = getdeltaUpdater;
		this.deltaUpdater.register(this);
		LayerDatas = new Vector<LayerData2>();
		this.posX = newPosX;
		this.posY = newPosY;
	}
	/*
	public Entity(double newPosX, double newPosY) {
		this.deltaUpdater = GS.deltaUpdater;
		this.deltaUpdater.register(this);
		this.posX = newPosX;
		this.posY = newPosY;
	}
	*/
	/**
	 * update is the core of timing, the observer sends to update and the update-method uses it
	 * */
	public void update(double delta) {
		this.delta = delta;
		this.draw();
	}
	
	/**
	 * addNewLayer adds a new graphical layer to LayerDatas, LayerDatas is a the collection of GraphicLayers
	 * */
	public void addNewLayer(LayerData2 newLayer){
		LayerDatas.add(newLayer);
	}
	
	/**
	 * draw commands every LayerData to draw itself according to its setup
	 * */
	public void draw(){
		//draw all texturelayers
		for(int i=0;i<LayerDatas.size();i++){
			LayerData2 o = LayerDatas.get(i);
			o.drawLayer();
		}
	}
}
