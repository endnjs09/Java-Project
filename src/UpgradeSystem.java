import java.util.*;

class UpgradeSystem {
	Inventory inv;
	Random rand = new Random();

	UpgradeSystem(Inventory inv){
		this.inv = inv;
	}
	
	public boolean upgradeTool(CraftTool t) {
		
		if (t.level == 1) {		// 1. 나무 >> 돌
			if (inv.getRock() < 1) 
				return false;
			
			inv.add("rock", -1);;
			
			if (rand.nextDouble() > 0.5) {	// 15퍼
				return false;	// 강화 실패
			}
			
			t.level = 2;
			t.name = "돌 도끼";
			t.maxdurable += 50;
			t.durable = t.maxdurable;
			t.damage = 2;
			return true;		
		}
		
		if (t.level == 2) {	// 1. 돌 > 철
			t.damage = 2;
			if (inv.getIron() < 1) 
				return false;
			
			inv.add("iron", -1);
			
			if (rand.nextDouble() > 0.5) {	// 3퍼
				return false;	// 강화 실패
			}
			
			t.level = 3;
			t.name = "철 도끼";
			t.maxdurable += 100;
			t.durable = t.maxdurable;
			t.damage = 1;
			return true;		
		}
		
		if (t.level == 3) {	// 철 < 금
			t.damage = 1;
			if (inv.getGold() < 1) 
				return false;
			
			inv.add("gold", -1);
			
			if (rand.nextDouble() > 0.01) {	// 1퍼
				return false;	// 강화 실패
			}
			
			t.level = 4;
			t.name = "금 도끼";
			t.maxdurable += 100;
			t.durable = t.maxdurable;
			t.damage = 1;
			return true;		
		}
		
		return false;	
	}
}
