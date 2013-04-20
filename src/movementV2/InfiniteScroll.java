package movementV2;

import entities.Entity;
import entities.Move;
import graphics.LayerData2;

public class InfiniteScroll extends Move{
	
	public InfiniteScroll(Entity getOwner, double moveX, double moveY) {
		super(getOwner);
		nposX = moveX;
		nposY = moveY;

			LayerData2 clone = new LayerData2(owner, owner.LayerDatas.get(0).texturepath, 1, 1);
			clone.pos = new double[]{owner.LayerDatas.get(0).spriteDisplayX,0};
			clone.spriteDisplayX = owner.LayerDatas.get(0).spriteDisplayX;
			clone.spriteDisplayY = owner.LayerDatas.get(0).spriteDisplayY;
			clone.layer = owner.LayerDatas.get(0).layer;
			owner.addNewLayer(clone);
		
		/*
		LayerData2 texture1 = new LayerData2(this, "astroidbelt", 1, 1);
		LayerData2 texture2 = new LayerData2(this, "astroidbelt", 1, 1);
		LayerData2 texture3 = new LayerData2(this, "astroidbelt", 1, 1);
		texture1.spriteDisplayX *= 2; 
		texture1.spriteDisplayY *= 2; 
		texture1.layer = 4;
		texture2.spriteDisplayX *= 2; 
		texture2.spriteDisplayY *= 2; 
		texture2.layer = 4;
		texture3.spriteDisplayX *= 2; 
		texture3.spriteDisplayY *= 2; 
		texture3.layer = 4;
		texture2.pos = new double[]{1024,0};
		addNewLayer(texture2);
		 */
	}
	protected void calculateMove(){
	}
	protected void makeMove(){
		if (owner.posX < -owner.LayerDatas.get(0).spriteDisplayX/2) {
			owner.posX = owner.LayerDatas.get(0).spriteDisplayX/2;
		}
		owner.posX -= nposX;
		owner.posY -= nposY;
	}
}
