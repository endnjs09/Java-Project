import java.awt.*;

import javax.swing.ImageIcon;

class CraftTool {
	// 식별 번호
	public static final int HAND = 1;
	public static final int AXE = 2;
	public static final int PICKAXE = 3;
	public static final int ROD = 4;
	
	String name;						// 장비 이름
	int type, durable, maxdurable;	// 식별번호, 내구도, 최대 내구도
	int level = 1; 					// 장비 등급
	int damage = 4;					// 상호작용 해야할 횟수
	int Fcount = 0; 					// 상호작용 키 누른 횟수
	
	CraftTool(String name, int type, int durable) {
		this.name = name;	
		this.type = type;
		this.durable = durable;
		this.maxdurable = durable;	
	}
	
	public void use() {		// 상호작용 시에 호출, 내구도 1씩 감소되게
		if (durable > 0)
			durable--;
	}
	
	public boolean isBroken() {
		return durable <= 0;
	}
	
	public ImageIcon getIcon() {
	    // 도끼(AXE)인 경우
	    if (type == AXE) {
	        if (level == 1) {
	            return ImageShow.AXE_1;
	        } 
	        else if (level == 2) {
	            return ImageShow.AXE_2;
	        } 
	        else if (level == 3) {
	            return ImageShow.AXE_3;
	        } 
	        else if (level == 4) {
	            return ImageShow.AXE_4;
	        } 
	        else {
	            return ImageShow.AXE_1; // 그 외(default)는 1레벨 이미지
	        }
	    }
	    // 곡괭이(PICKAXE)인 경우
	    else if (type == PICKAXE) {
	        if (level == 1) {
	            return ImageShow.PICK_1;
	        } 
	        else if (level == 2) {
	            return ImageShow.PICK_2;
	        } 
	        else if (level == 3) {
	            return ImageShow.PICK_3;
	        } 
	        else if (level == 4) {
	            return ImageShow.PICK_4;
	        } 
	        else {
	            return ImageShow.PICK_1; // 그 외(default)는 1레벨 이미지
	        }
	    }
	    // 낚싯대(ROD)인 경우
	    else if (type == ROD) {
	        return ImageShow.ROD;
	    }
	    // 맨손(HAND)인 경우
	    else if (type == HAND) {
	        return ImageShow.HAND;
	    }

	    return null;
	}
	
}
