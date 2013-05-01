package entities;

import graphics.GS;
import graphics.LayerData2;

import java.util.Vector;

import observer.*;

/**
 * Entitiy is the superclass of every class which produces some visual effect.
 * Every subclass extending this class will have the ability to render and
 * display itself.
 */
public abstract class Entity implements Observer {
	public double delta;
	int defaultLayer = 30;
	protected Subject deltaUpdater;
    public double posX;
	public double posY;
    protected int anitimer = 0;
    /**
     * LayerDatas is a the collection of all GraphicLayers of an Entity.
     */
	public Vector<LayerData2> LayerDatas;
	protected int maintexture = 0;
	protected double offscreen = 15000;
	protected double offscreenLive = 0;
	public boolean isAlive = true;

	/**
	 * With the creation of an entity the object registers itself with the 
	 * observer
	 * */
	public Entity(double newPosX, double newPosY){
		this.deltaUpdater = GS.deltaUpdater;
		this.deltaUpdater.register(this);
		LayerDatas = new Vector<LayerData2>();
		this.posX = newPosX;
		this.posY = newPosY;
	}
	/**
	 * The deltaUpdater uses this method to broadcast the delta to all Entities.
	 * The delta value is used to synchronize rendering and the speed of Entities.
	 * */
	public void update(double delta) {
		if(isAlive){
			this.delta = delta;
			this.draw();
			this.checkUnsubscribe();
		}
	}
	
	/**
	 * Adds a new graphical layer to the Vector LayerDatas.
	 * */
	public void addNewLayer(LayerData2 newLayer){
		LayerDatas.add(newLayer);
	}
	public void removeLayer(LayerData2 layer){
		int index = LayerDatas.indexOf(layer);
		LayerDatas.remove(index);
	}
	
	/**
	 * Commands every graphical Layer to draw itself according to its setup.
	 * */
	public void draw(){
		//draw all texturelayers
		for(int i=0;i<LayerDatas.size();i++){
			LayerData2 o = LayerDatas.get(i);
			o.drawLayer();
		}
	}
	/**
	 * Unregisters the Entity at the deltaUpdater-Observer
	 * if its outside the screen for a defined amount of time.
	 * */
	private void checkUnsubscribe(){
		if(this.posX > 2*1280 || this.posX < 0-1280||this.posY > 2*768 || this.posY < 0-768){
			this.offscreenLive += delta;
			if(offscreenLive > offscreen){
				unsubscribe();
			}
		}else{
			offscreenLive = 0;
		}
	}
	/**
	 * Unregisters the Entity at the deltaUpdater-Observer.
	 * Unregistering the object removes the only reference to this object
	 * and will cause the Garbage-Collector to destroy it.
	 * */
	protected void unsubscribe(){
		deltaUpdater.unregister(this);
		for(int i=0;i<LayerDatas.size();i++){
			LayerDatas.remove(i);
		}
	}
}
