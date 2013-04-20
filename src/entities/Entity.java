package entities;

import graphics.LayerData2;

import java.util.Vector;

import observer.*;

/**
 * Entitiy is the superclass of each visual class.
 * Every subclass extending this class will have the ability to render itself for the user
 */
public abstract class Entity implements Observer {
	public double delta;
	int defaultLayer = 30;
	protected Subject deltaUpdater;
    public double posX;
	public double posY;
	// Graphics Variables
    protected int anitimer = 0;
	public Vector<LayerData2> LayerDatas;
	protected int maintexture = 0;
	protected double offscreen = 15000;
	protected double offscreenLive = 0;

	public Entity(double newPosX, double newPosY, Subject getdeltaUpdater){
		this.deltaUpdater = getdeltaUpdater;
		this.deltaUpdater.register(this);
		LayerDatas = new Vector<LayerData2>();
		this.posX = newPosX;
		this.posY = newPosY;
	}
	/**
	 * update is the core of timing, the delta Value is synced with every Entity to sync rendering and speed
	 * */
	public void update(double delta) {
		this.delta = delta;
		this.draw();
		this.checkUnsubscribe();
	}
	
	/**
	 * addNewLayer adds a new graphical layer to the Vector LayerDatas, LayerDatas is a the collection of all GraphicLayers of an Entity
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
	
	/**
	 * checkUnsubscribe() unregister with the deltaUpdater Observer when the Entity is outside the screen for a defined time
	 * unregistering the object removes the only reference to this object and will enable the Garbage Collector to remove this object
	 * */
	private void checkUnsubscribe(){
		if(this.posX > 2*1280 || this.posX < 0-1280||this.posY > 2*768 || this.posY < 0-768){
			this.offscreenLive += delta;
			if(offscreenLive > offscreen){
				deltaUpdater.unregister(this);
			}
		}else{
			offscreenLive = 0;
		}
	}
}
