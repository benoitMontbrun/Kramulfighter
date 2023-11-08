package main;

import entity.Entity;
import object.*;


public class EntityGenerator {
	
	GamePanel gp;
	
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	
	public Entity getObject(String itemName) {
		
		Entity obj = null;
		switch(itemName) {
		case OBJ_Heart.objName:
			obj = new OBJ_Heart(gp, 0, 0);
			break;
		case OBJ_Key.objName:
			obj = new OBJ_Key(gp, 0, 0);
			break;
		case OBJ_Coin.objName:
			obj = new OBJ_Coin(gp, 0, 0);
			break;
		case OBJ_Bill.objName:
			obj = new OBJ_Bill(gp, 0, 0);
			break;
		case OBJ_Axe.objName:
			obj = new OBJ_Axe(gp, 0, 0);
			break;
		case OBJ_Bat.objName:
			obj = new OBJ_Bat(gp, 0, 0);
			break;
		case OBJ_Bottle.objName:
			obj = new OBJ_Bottle(gp, 0, 0);
			break;
		case OBJ_Bow.objName:
			obj = new OBJ_Bow(gp, 0, 0);
			break;
		case OBJ_Arrow.objName:
			obj = new OBJ_Arrow(gp, 0, 0);
			break;
		case OBJ_BubblePistol.objName:
			obj = new OBJ_BubblePistol(gp, 0, 0);
			break;
		case OBJ_Map.objName:
			obj = new OBJ_Map(gp, 0, 0);
			break;
		case OBJ_PQ.objName:
			obj = new OBJ_PQ(gp, 0, 0);
			break;
		case OBJ_KramulHead.objName:
			obj = new OBJ_KramulHead(gp, 0, 0);
			break;
		case OBJ_BubbleProduct.objName:
			obj = new OBJ_BubbleProduct(gp, 0, 0);
			break;
		case OBJ_Book.objName:
			obj = new OBJ_Book(gp, 0, 0);
			break;
		case OBJ_Lamp.objName:
			obj = new OBJ_Lamp(gp, 0, 0);
			break;
		}
		

		return obj;
	}
	


	
	

}
