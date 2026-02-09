
public class Build {
	public MainPanel p;
	public World w;			// 맵에서의 타일 확인
	public Inventory inv;	// 자원을 소모해야 하니까
	public Player player;
	
	Build(MainPanel p, World w, Inventory inv, Player player){
		this.p = p;
		this.w = w;			
		this.inv = inv;
		this.player = player;
	}
	
	public void BuildHouse(int x, int y) {
		if (inv.getWood() < 100 || inv.getStick() < 50 || inv.getRock() < 100) {	// 나무 100, 나무가지 50개 필요
			p.showMessage("재료가 부족하다");
			return;
		}

		inv.add("wood", -100);	
		inv.add("rock", -100);
		inv.add("stick", -50);
		
		// 플레이어가 마지막으로 본 방향 (0: 아래, 1: 위, 2: 왼쪽, 3: 오른쪽)
		if (player.dir == 0) {			// 아래에 집 지을 때
			// 땅에만 지을 수 있게
			if (w.map[y][x] != 0 || w.map[y][x + 1] != 0 || w.map[y + 1][x] != 0 || w.map[y + 1][x + 1] !=0) {
				p.showMessage("여기에 집을 지을 수 없다.");
				return;
			}
			else {
				w.map[y][x] = 27;
				w.map[y][x + 1] = 28;
				w.map[y + 1][x] = 26;
				w.map[y + 1][x + 1] = 25;
				
				p.showMessage("집을 지었다.");	
				p.repaint();
			}
		}
		else if (player.dir == 1) {		// 위에 집 지을 때
			// 땅에만 지을 수 있게 
			if (w.map[y][x] != 0 || w.map[y][x + 1] != 0 || w.map[y - 1][x] != 0 || w.map[y - 1][x + 1] != 0) {
				p.showMessage("여기에 집을 지을 수 없다.");
				return;
			}
			else {
				w.map[y][x] = 26;
				w.map[y][x + 1] = 25;
				w.map[y - 1][x] = 27;
				w.map[y - 1][x + 1] = 28;
				
				p.showMessage("집을 지었다.");	
				p.repaint();
			}
		}
		else if (player.dir == 2) {		// 왼쪽에 집 지을때
			// 땅에만 지을 수 있게 
			if (w.map[y][x] != 0 || w.map[y][x - 1] != 0 || w.map[y - 1][x - 1] != 0 || w.map[y - 1][x] != 0) {		
				p.showMessage("여기에 집을 지을 수 없다.");
				return;
			}
			else {
				w.map[y][x] = 25;
				w.map[y][x - 1] = 26;
				w.map[y - 1][x - 1] = 27;
				w.map[y - 1][x] = 28;
				
				p.showMessage("집을 지었다.");	
				p.repaint();
			}
		}
		else if (player.dir == 3) {		// 오른쪽에 집 지을 때 
			// 땅에만 지을 수 있게
			if (w.map[y][x] != 0 || w.map[y - 1][x] != 0 || w.map[y - 1][x + 1] != 0 || w.map[y][x + 1] != 0) {
				p.showMessage("여기에 집을 지을 수 없다.");
				return;
			}
			else {
				w.map[y][x] = 26;
				w.map[y - 1][x] = 27;
				w.map[y - 1][x + 1] = 28;
				w.map[y][x + 1] = 25;
				
				p.showMessage("집을 지었다.");	
				p.repaint();
			}
		}
	}
	
	// ====== 불 피우기 ======
	public void BuildFire(int x, int y) {
		if (inv.getWood() < 30) {
			p.showMessage("재료가 부족하다");
			return;
		}
		
		if(w.map[y][x] != 0) {
			p.showMessage("여기에는 설치할 수 없다.");
			return;
		}
		
		inv.add("wood", -30);
		w.map[y][x] = 4;
		p.showMessage("이제 불을 피울 수 있을 것 같다.");
		p.repaint();
	}
	
	
	// ====== 물통 ======
	public void BuildWater(int x, int y) {
		if (inv.getWood() < 30 || inv.getRock() < 30) {
			p.showMessage("재료가 부족하다");
			return;
		}
		
		if(w.map[y][x] != 0) {
			p.showMessage("여기에는 설치할 수 없다.");
			return;
		}
		
		inv.add("wood", -1);
		w.map[y][x] = 7;
		p.showMessage("이제 물을 마실 수 있을 것 같다.");
		p.repaint();
	}
	
	
	// ====== 강화대 ======
	public void BuildUpgrade(int x, int y) {
		if (inv.getWood() < 30 || inv.getIron() < 3 || inv.getRock() < 30) {
			p.showMessage("재료가 부족하다");
			return;
		}
		
		if(w.map[y][x] != 0) {
			p.showMessage("여기에는 설치할 수 없다.");
			return;
		}
		
		inv.add("wood", -30);
		inv.add("rock", -30);
		inv.add("iron", -3);
		w.map[y][x] = 12;
		p.showMessage("강화대를 설치했다.");
		p.repaint();
	}
}
