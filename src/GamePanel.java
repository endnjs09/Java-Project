import javax.swing.*;
import java.awt.*;

class GamePanel extends JPanel {
	MainPanel p;
	Player player;
	Camera c;
	BuildSystem build;
	World w;

    GamePanel(MainPanel p, Player player, Camera c, BuildSystem build, World w) {
    		this.p = p;
        this.player = player;
        this.c = c;
        this.build = build;
        this.w = w;
        setFocusable(false);
        setRequestFocusEnabled(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 카메라의 시작점이 어느 타일인지 체크용
        int startTileX = c.px / MainPanel.TILE_SIZE;
        int startTileY = c.py / MainPanel.TILE_SIZE;
        
        // 얼마만큼 밀어야 하는지 계산 
        // 오른쪽으로 1px 갔다면 px = 66, startTileX는 여전히 1, offsetX = -1
        int offsetX = -(c.px % MainPanel.TILE_SIZE);
        int offsetY = -(c.py % MainPanel.TILE_SIZE);

        for (int i = 0; i < MainPanel.SCREEN_HEIGHT; i++) {
            for (int j = 0; j < MainPanel.SCREEN_WIDTH; j++) {

                int wx = startTileX + j;	// 시작 타일로부터 가로로 22개
                int wy = startTileY + i;	// 세로로 13개
                
                // 맵 밖으로 나가는 거 방지
                if (wx < 0 || wy < 0 || wx >= World.WORLD_WIDTH || wy >= World.WORLD_HEIGHT)
                    continue;
                
                // (-1 ~ 63) (64 ~ 128) (129 ~ 
                // (-2 ~ 62) (63 ~ 127) (128 ~
                int sx = offsetX + j * MainPanel.TILE_SIZE;		
                int sy = offsetY + i * MainPanel.TILE_SIZE;
                
                int tile = w.map[wy][wx];

                switch (tile) {
                    case 0 -> {		// 땅(잔디)
                    		g.drawImage(ImageShow.GRASS, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    case 1 -> {		// 나무 아래
                    		g.drawImage(ImageShow.TREE1, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }
                    case 2 -> {		// 나무 위쪽
                    		g.drawImage(ImageShow.TREE2, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }			
                    case 3 -> {		// 바디
                    		g.drawImage(ImageShow.SEA, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);		
                    }
                    case 4 -> {
                    		g.drawImage(ImageShow.FIRE1, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }				// 불 꺼짐
                    case 5 -> {
                    		g.drawImage(ImageShow.FIRE2, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }				// 불 켜짐
                    case 6 -> {
//                    	g.drawImage(ImageShow.HOUSE, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }		// 집
                    case 7 -> {		// 물통
                    		g.drawImage(ImageShow.WATER, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }				
                    case 8 -> {  	// 낚시터
                    		g.drawImage(ImageShow.FISHING_TILE, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }	
                    case 9 -> {		// 바다2 (낚시)
                    		g.drawImage(ImageShow.FISHING, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);		
                    }
                    case 10 -> {  	// 수풀
                    		g.drawImage(ImageShow.BUSH, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }	
                    case 11 -> {	// 배
                    		g.drawImage(ImageShow.BOAT, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }
                    case 12 -> {	// 강화대
                    		g.drawImage(ImageShow.UPGRADE, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }		
                    
                    
                    // ============== 모서리,가장자리 ================
                    case 13 -> {	// 좌상단 모서리
                    		g.drawImage(ImageShow.SAND1, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    case 14 -> {	// 우상단 가장자리
                    		g.drawImage(ImageShow.SAND2, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    case 15 -> {	// 우상단 모서리
                    		g.drawImage(ImageShow.SAND3, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    case 16 -> {	// 우측 가장자리
                    		g.drawImage(ImageShow.SAND4, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    case 17 -> {	// 좌측 가장자리
                    		g.drawImage(ImageShow.SAND5, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    case 18 -> {	// 우하단 모서리
                    		g.drawImage(ImageShow.SAND6, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }       
                    case 19 -> {	// 좌하단 모서리
                    		g.drawImage(ImageShow.SAND7, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }                
                    case 20 -> {	// 하단 가장자리
                    		g.drawImage(ImageShow.SAND8, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    
                    
                    // ============== 돌 ================
                    case 21 -> {	// 돌 우하단
                    		g.drawImage(ImageShow.ROCK1, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    case 22 -> {	// 돌 좌하단
                    		g.drawImage(ImageShow.ROCK2, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    case 23 -> {	// 돌 좌상단
                    		g.drawImage(ImageShow.ROCK3, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    case 24 -> {	// 돌 우상단
                    		g.drawImage(ImageShow.ROCK4, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);	
                    }
                    
                    
                 // ============== 집 ================
                    case 25 -> {
                    		g.drawImage(ImageShow.HOUSE1, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }		// 집
                    case 26 -> {
                    		g.drawImage(ImageShow.HOUSE2, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }		// 집
                    case 27 -> {
                    		g.drawImage(ImageShow.HOUSE3, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }		// 집
                    case 28 -> {
                    		g.drawImage(ImageShow.HOUSE4, sx, sy, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE, null);
                    }		// 집
                }
            }
        }

        // ======= 플레이어 =======
        player.draw(g, c);
        
        
        
        // ======= 설치모드(설치 영역) =======
        if (p.isInstall) {
        		// 설치할 위치
        		int bx = build.buildX;	
        		int by = build.buildY;	
        
        		// 화면 상에서 색칠해야할 위치 보정
        		// 카메라 시작점 px, py가 500, 500이면 0,0으로 보정해야함
        		int screenX = bx * MainPanel.TILE_SIZE - c.px;	
        		int screenY = by * MainPanel.TILE_SIZE - c.py;	
        	
        		g.setColor(new Color(0, 100, 255, 130));
        		g.fillRect(screenX, screenY, MainPanel.TILE_SIZE, MainPanel.TILE_SIZE);
        		
        }
        
    }
}