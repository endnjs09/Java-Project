import java.util.*;

class Inventory{
	int stick, wood, rock, fruit, fish, cookedfish, boat, iron, gold;
	// 나무가지, 목재, 돌, 열매, 생선, 익힌생선, 배, 철조각, 금조각
	ArrayList<CraftTool> tool = new ArrayList<>();	// 도구 Arraylist
	
	Inventory(){
		this.stick = 99;
		this.wood = 500;
		this.rock = 500;
		this.fruit = 99;
		this.fish = 99;
		this.cookedfish = 99;
		this.boat = 0;
		this.iron = 500;
		this.gold = 500;
	}
	
	public void add(String name, int amount) {
		if (name.equals("stick")) 
			stick += amount;
		else if (name.equals("wood"))
	        wood += amount;
	    else if (name.equals("rock"))
	        rock += amount;
	    else if (name.equals("fruit"))
	    		fruit += amount;
	    else if (name.equals("fish"))
	        fish += amount;
	    else if (name.equals("cookedfish"))
	    		cookedfish += amount;
	    else if (name.equals("boat"))
	    		boat += amount;
	    else if (name.equals("iron"))
	    		iron += amount;
	    else if (name.equals("gold"))
	    		gold += amount;
	}
	
	public void addTool(CraftTool t) {
		tool.add(t);		// ArrayList에 t 추가
	}

	
	public CraftTool getToolType(int type) {	
		for (int i = 0; i < tool.size(); i++) {	// Arraylist 뒤지기
		    CraftTool t = tool.get(i);			// i 번째 도구 가져오기, 도끼라면 t는 {"axe", type = 1}
		    
		    if (t.type == type && !t.isBroken()) {
		        return t;
		    }
		}
		return null;
	}
	
	public int getStick() {
		return stick;
	}
	public int getBoat() {
		return boat;
	}
	public int getWood() {
		return wood;
	}
	public int getRock() {
		return rock;
	}
	public int getFruit() {
		return fruit;
	}
	public int getFish() {
		return fish;
	}
	public int getCookedFish() {
		return cookedfish;
	}
	public int getIron() {
		return iron;
	}
	public int getGold() {
		return gold;
	}
}
