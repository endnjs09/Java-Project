import java.util.Random;
import javax.swing.Timer;

class Resources {
	MainPanel p;		
	World w;			// 타일 검사용
	Inventory inv;	// 인벤토리에 자원 넣기
	Player player;	// 플레이어 위치 
	
	Random rand = new Random();	// 자원에 확률 부여
	int waterHP = 10;			
	boolean rest = true;			// 집에서 쉬었는지
	
	Resources(MainPanel p, World w, Inventory inv, Player player){
		this.w = w;
		this.p = p;
		this.inv = inv;
		this.player = player;
	}
	
	public void Interact(int x, int y) {
	    int pos = w.map[y][x];

	    // ======= 배 관련 =======
	    if (player.onBoat) {		// 배에 탄 상태이면
	        if (pos == 3 || pos >= 13) {	
	            moveBoat(x, y, player);		// 배 타고 이동, 내리기
	            return;
	        }
	    }
	    
	    if (pos == 11) {			// 설치된 배에 상호작용
	        moveBoat(x, y, player);		// 배에 타기
	        return;
	    }
	    
	    if (pos == 3) {     		// 바다에 상호작용
	        Boat(x, y, player);	// 배 설치
	        return;
	    }

	    
	    CraftTool tool = player.getCurrent();	// 플레이어의 현재 장비
    
	    // 강화대
	    if (pos == 12) {	// 강화대에 상호작용
		    	if (tool == null) { 
		    		p.showMessage("맨손을 강화할 수는 없지.."); 
		    		return; 
		    	} 
		    	
		    	p.up.openTool(tool); 
		    	p.closeAllPanel(); 		
		    	p.up.setVisible(true); 	// 강화 Panel 열기
		    	p.moveTimer.stop(); 		// 플레이어 못 움직이게
		    	p.at.stopAll(); 			// 허기, 갈증 등 타이머 allstop
		    	return;
	    }

	    // 그외에 자원 상호작용
	    switch (pos) {
	        case 1 -> Tree(x, y, player);	// 나무 캐기
	        case 21,22,23,24 -> Rock(x, y, player);	// 돌캐기
	        case 9 -> Water(x, y, player);	// 낚시
	        case 4,5 -> Fire(x, y);			// 불 피우기
	        case 25,26 -> House(x, y, player);	// 집 안에 들어감
	        case 7 -> Drink(x, y);		// 물 마시기
	        case 10 -> Bush(x, y);		// 수풀 뒤지기
	        default -> {					// 그외에 땅 같은 곳
	            p.showMessage("상호작용할 것이 없다.");
	            return;
	        }
	    }
	    
	    // 상호작용 할 때 마다 도구 내구도 감소
	    if (tool != null) {		
	        tool.use();		// 내구도 1씩 감소
	        if (tool.isBroken()) 	// 도구 박살나면
	        		player.equip(null);	// 맨손으로 변경
	    }
	}
	
	
	
	
	// ====== 나무 ======
	public void Tree(int x, int y, Player player) {
		CraftTool t = player.getCurrent();
		if (t == null || t.type != CraftTool.AXE) {
			p.showMessage("도끼가 필요하다.");
			return;
		}
		
		t.Fcount++;
		if(t.Fcount < t.damage) {
			p.showMessage("채집 중... (" + t.Fcount + "/" + t.damage + ")");
	        return;   
		}
		
		t.Fcount = 0;
		
		if (w.treeHP[y][x] > 0) {
	        w.treeHP[y][x]--;
			
	        // 금도끼일 때 2개, 나머지 1개
			int drop = (t.level == 4) ? 2 : 1;
			inv.add("wood", drop);
			p.showMessage("목재 획득 +" + drop);
			
	        
	        if (rand.nextDouble() < 0.1) {
				inv.add("fruit", 1);
				p.showMessage("사과 획득 +1");
			}
	        if (rand.nextDouble() < 0.3) {
				inv.add("stick", 1);
				p.showMessage("나무가지 획득 +1");
			}
	        
	        p.repaint();
	    }
		
	    if (w.treeHP[y][x] == 0) {		// 다 캐면 재생성 시키기
		    	w.map[y][x] = 0; 			// 나무 밑칸
		    	w.map[y - 1][x] = 0;			// 나무 윗칸
	        p.repaint();
	        
	        new Timer(10000, e -> {		
		        	w.map[y][x] = 1;		// 나무 밑칸
			    	w.map[y - 1][x] = 2;	// 나무 윗칸
		        	w.treeHP[y][x] = 5;		
		        	p.repaint();
	        }) {{
	            setRepeats(false); 	// 한 번만 실행
	            start();
	        }};
	    }
	}
	
	
	
	// ====== 돌 ======
	public void Rock(int x, int y, Player player) {
		CraftTool t = player.getCurrent();
		if (t == null || t.type != CraftTool.PICKAXE) {
			p.showMessage("곡괭이가 필요하다.");
			return;
		}
		
		t.Fcount++;
		if(t.Fcount < t.damage) {
			p.showMessage("채집 중... (" + t.Fcount + "/" + t.damage + ")");
	        return;   // 아직 데미지 발생 X → 원본 Tree 로직 건드리지 않음
		}
		
		t.Fcount = 0;
		
		if (w.rockHP[y][x] > 0) {
	        w.rockHP[y][x]--;
			
			int drop = (t.level == 4) ? 2 : 1;
			inv.add("rock", drop);
			p.showMessage("돌 획득 +" + drop);
			
	        
			if (rand.nextDouble() < 0.1) {	// 10퍼
				inv.add("iron", 1);
				p.showMessage("철조각 획득 +1");
			}
			
			if (rand.nextDouble() < 0.03) {	// 3퍼
				inv.add("gold", 1);
				p.showMessage("금조각 획득 +1");
			}
	        
	        p.repaint();
	    }
		
	}
	
	
	
	// ====== 낚시 ======
	private void Water(int x, int y, Player player) {	// 낚시
		CraftTool t = player.getCurrent();
		if (t == null || t.type != CraftTool.ROD) {
			p.showMessage("낚싯대가 필요하다.");
			return;
		}
		
		if (w.map[y][x] == 9) {
			if (rand.nextDouble() < 0.1) {	// 10%
				inv.add("fish", 1);
				p.showMessage("물고기 획득 +1");
				p.repaint();
			}
			else {
				p.showMessage("아무것도 잡히지 않았다.");
				p.repaint();
			}
		}
	}
	
	
	
	// ====== 불 ======
	private void Fire(int x, int y) {
		if(w.map[y][x] == 4) {	// 불 꺼진 상태
			if(inv.wood < 5 || inv.rock < 10) {
				p.showMessage("재료가 부족하다.");
				return;
			}
			
			inv.add("wood", -5);
			inv.add("rock", -10);
			w.map[y][x] = 5;
			p.showMessage("불을 피웠다.");
			p.repaint();
			return;
		}
		
		if(w.map[y][x] == 5) {	// 불 켜진 곳에 상호작용
			if (inv.getFish() > 0) {
				inv.add("fish", -1);
				inv.add("cookedfish", 1);
				p.showMessage("익힌 물고기 획득 +1");
				p.repaint();
			}
			else {
				p.showMessage("물고기가 없네...");
				p.repaint();
			}
		}
	}
	
	
	
	// ====== 집 ======
	private void House(int x, int y, Player player) {
	   
	    // 지어진 집 위에서 상호작용 시
	   if (w.map[y][x] == 25 || w.map[y][x] == 26) {
	       
		    if (!rest) { // 휴식 쿨타임
	            p.showMessage("방금 전에 쉬었어...");
	            return;
	        }  
	        p.showMessage("집에서 조금만 쉬자...");
	        player.setVisible(false);
	        rest = false;
	        p.repaint();
	    
	        // 타이머 멈춤
	        p.at.stopAll();
	        p.moveTimer.stop();

	        // 이틀 경과
	        for (int i = 0; i < 2; i++)
	        		p.at.nextDay();

	        // 회복
	        p.hp += 30;
	        if (p.hp > 100) 
	        		p.hp = 100;
	        
	        	p.hunger += 50;
	    		if (p.hunger > 100) 
	    			p.hunger = 100;
	    		
	    		p.thirst += 50;
	    		if (p.thirst > 100) 
	    			p.thirst = 100;

	        // 휴식시간 3초, 3초후 다시 등장
	    	new Timer(3000, e -> {
	    		p.player.setVisible(true);
	    		p.showMessage("이틀간 집 안에서 쉬었다...");
	        	p.at.startAll();
	        	p.moveTimer.start();
	        	p.repaint();
	    	}) {{
	    		setRepeats(false);
	    		start();
	    	}};

	    	// 쿨타임 3분 (3일)
	    	new Timer(180000, e -> rest = true) {{
	    		setRepeats(false);
	    		start();
	    	}};
	    	
	    	p.repaint();
        	return;	
        	
		}
	}
	
	
	
	// ====== 물통 ======
	private void Drink(int x, int y) {
		if (w.map[y][x] == 7) {
			if (waterHP > 0) {	
				p.thirst += 10;
				
				if (p.thirst >= 100) {
					p.thirst = 100;
					p.showMessage("목이 마르지 않다.");
				}
				waterHP--;
				p.showMessage("물을 마셨다. 앞으로 " + waterHP + "번 사용가능 할 것 같다.");
				p.repaint();
				
				if(waterHP == 0) {	// 10번 상호작용하면 없앰	
					w.map[y][x] = 0;
					p.showMessage("다시 설치해야 할 것 같다.");
					p.repaint();
				}
			}
			return;
		}
	}
	
	// ====== 수풀 ======
	private void Bush(int x, int y) {
		if (w.map[y][x] == 10) {
			if (rand.nextDouble() < 0.3) {	// 30퍼
				inv.add("stick", 1);
				p.showMessage("나무가지 획득 +1");
			}
			else if (rand.nextDouble() < 0.1) {	// 10퍼
				inv.add("stick", 2);
				p.showMessage("나무가지 획득 +2");
			}
			else p.showMessage("아무것도 없네...");
		}
	}
	
	
	// ====== 배 설치 ====== 
	public void Boat(int x, int y, Player player) {
	    // 배가 인벤토리에 있어야 함
	    if (inv.getBoat() <= 0) {
	        p.showMessage("배가 없다...");
	        return;
	    }

	    // 바다(pos==3)일 때만 설치 가능
	    if (w.map[y][x] != 3) {
	        p.showMessage("배를 설치할 수 없다...");
	        return;
	    }

	    // 배 설치
	    inv.add("boat", -1);
	    w.map[y][x] = 11;    // 배 타일 생성
	    p.showMessage("배를 설치했다!");
	    p.repaint();
	}
	
	
	
	// ====== 배 타고 이동 ====== 
	public void moveBoat(int x, int y, Player player) {

	    // 배에 아직 안 탔으면 타기
	    if (!player.onBoat) {

	        // 정박된 배 tile 제거
	        w.map[y][x] = 3;    // 배 없애고 바다 타일로 교체

	        player.onBoat = true;	// 플레이어가 배에 탄 상태로 변경
	        player.setVisible(false);	
	        
	        // 플레이어를 배가 있는 타일의 중앙으로 이동 (x,y는 배의 타일 좌표)
	        player.worldX = x * MainPanel.TILE_SIZE + (MainPanel.TILE_SIZE - player.player_img) / 2;
	        player.worldY = y * MainPanel.TILE_SIZE + (MainPanel.TILE_SIZE - player.player_img) / 2;
	        // x * TILE_SIZE, y * TILE_SIZE : 배가 있는 타일의 픽셀 좌표
	        // (TILE_SIZE - player_img) / 2 : 중앙 정렬
	        
	        
	        p.showMessage("배에 올랐다!");
	        p.repaint();
	        return;
	    }

	    // 플레이어가 이미 배를 타고 있는 경우 내리기
	    if (player.onBoat) {

	        // 플레이어 현재 타일 위치
		    	int px = (player.worldX + player.player_img / 2) / MainPanel.TILE_SIZE;
		    	int py = (player.worldY + player.player_img / 2) / MainPanel.TILE_SIZE;

	        // 바라보는 방향
	        int dx = 0, dy = 0;
	        switch (player.dir) {
	            case 1 -> dy = -1; 
	            case 0 -> dy = 1;
	            case 2 -> dx = -1;
	            case 3 -> dx = 1;
	        }
	        
	        
	        // 플레이어 위치, 발향 (상호작용과 같음)
	        int tx = px + dx;
	        int ty = py + dy;

	        // 앞의 타일이 땅이면 내리기
	        if (w.map[ty][tx] >= 13) {

	            player.onBoat = false;
	            player.setVisible(true);

	            // 플레이어 위치를 육지로
	            player.worldX = tx * MainPanel.TILE_SIZE;
	            player.worldY = ty * MainPanel.TILE_SIZE;

	            p.showMessage("배에서 내렸다!");
	            return;
	        }

	        p.showMessage("여기서는 내릴 수 없다.");	// 땅이 아닌경우
	        return;
	    }

	    p.showMessage("여기서는 내릴 수 없다.");	// 바다에서 내릴려고 하면
	}
	
	
	
	
	
}