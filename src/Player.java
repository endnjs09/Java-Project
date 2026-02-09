import java.awt.*;
import java.awt.event.KeyEvent;

class Player {
    int worldX, worldY;	
    // 플레이어의 실제 위치, 왼쪽위 (픽셀단위)
    // (36, 66) 이면 36 * 65 x 66 * 65 = 2340px x 4290px 위치
    
    int dir = 0;		// 플레이어가 마지막으로 본 방향 (0: 아래, 1: 위, 2: 왼쪽, 3: 오른쪽)
    int speed = 3;		// 플레이어 속도 4px
    
    int player_img = 65;	// 플레이어 이미지 크기 (가로 세로)
    int hitbox = 36;		// 히트박스 크기 (가로 세로)
    
    private boolean up, down, left, right;
    private boolean visible = true;		// 집에 들어갔는지
    public boolean onBoat = false;		// 플레이어가 배에 탔는지 여부

    MainPanel p;
    World w;
    CraftTool current = null;	// 초기 장비(현재) = 맨손(null)

    Player(MainPanel p, World w, int x, int y) {
        this.p = p;
        this.w = w;
        
        // 타일 좌표로 들어오면 픽셀로 변환
        this.worldX = x * MainPanel.TILE_SIZE;
        this.worldY = y * MainPanel.TILE_SIZE;
    }
    
    // 플레이어 이동, 마지막으로 누른 키
    public void keyPressed(KeyEvent e) {
    	
	    	if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	    		down = true; 
	    		dir = 0; 
	    	}
	    	if (e.getKeyCode() == KeyEvent.VK_UP) {
	    		up = true; 
	    		dir = 1; 
	    	}
	    	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	    		left = true; 
	    		dir = 2; 
	    	}
	    	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	    		right = true; 
	    		dir = 3; 
	    	}
    }
    public void keyReleased(KeyEvent e) {
    	
	    	if (e.getKeyCode() == KeyEvent.VK_UP) 
	    		up = false; 
	    	if (e.getKeyCode() == KeyEvent.VK_DOWN) 
	    		down = false;     	
	    	if (e.getKeyCode() == KeyEvent.VK_LEFT) 
	    		left = false;
	    	if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
	    		right = false;

    }

    public void update() {	// MainPanel에서 16ms 마다 갱신함
        int tempX = worldX, tempY = worldY;	// 이동했을 때의 임시 좌표
        
        // up, down, left, right가 true라면 speed 만큼 더하거나 뺌
        if (up) tempY -= speed;				
        else if (down) tempY += speed;
        else if (left) tempX -= speed;
        else if (right) tempX += speed;

        if (canMove(tempX, tempY)) {	// 이동 가능한지 판단, 이동했을 때의 임시좌표를 넘김
            worldX = tempX;				
            worldY = tempY;
            // 이동 가능하면 위치 갱신함
        }

        // 월드 경계 (맵 끝에 닿았을 때 멈춰야함) 없으면 맵 끝에 닿았을 때 통과함
        int maxX = World.WORLD_WIDTH  * MainPanel.TILE_SIZE - player_img;
        int maxY = World.WORLD_HEIGHT * MainPanel.TILE_SIZE - player_img;
        
        if (worldX < 0) worldX = 0;
        if (worldY < 0) worldY = 0;
        if (worldX > maxX) worldX = maxX;
        if (worldY > maxY) worldY = maxY;
        
        if (p.isInstall) {		// 설치 영역 칠하는 거
            p.build.updateTile(); // 설치 영역 플레이어가 움직인 대로 바로 반영
        }
    }

    private boolean canMove(int x, int y) {
        // 플레이어 충돌 체크는 중심 기준 + 이동방향 끝쪽을 검사
        int LeftHit = x + (player_img - hitbox) / 2;	// 히트박스 왼쪽 (x축)
        int RightHit = LeftHit + hitbox;				// 히트박스 왼쪽 (x축)
        int TopHit = y + (player_img - hitbox);		// 히트박스 위쪽 (y축)
        int BottomHit = TopHit + hitbox;				// 히트박스 아래 (y축)

        // 4개의 모서리 타일 위치
        int X1 = LeftHit / MainPanel.TILE_SIZE;		// 왼쪽 x축이 몇 번 타일인지 (배열의 열 부분)
        int X2 = RightHit / MainPanel.TILE_SIZE;		// 오른쪽 x축이 몇 번 타일인지
        int Y1 = TopHit / MainPanel.TILE_SIZE;		// 위쪽 y축이 몇 번 타일인지 (배열의 행 부분
        int Y2 = BottomHit / MainPanel.TILE_SIZE;	// 아래쪾 y축이 몇 번 타일인지

        // 맵 범위 벗어나면 불가
        if (X1 < 0 || Y1 < 0 || X2 >= World.WORLD_WIDTH || Y2 >= World.WORLD_HEIGHT)
            return false;

        int t1 = w.map[Y1][X1], t2 = w.map[Y1][X2];	// 각각의 모서리(좌표)에 있는 타일?
        int t3 = w.map[Y2][X1], t4 = w.map[Y2][X2];	
        if (!check(t1) || !check(t2) || !check(t3) || !check(t4)) // 4개 모서리의 타일을 check로 넘김
        		return false;		// 4개 모서리 중 하나라도 막혀있으면 false
        
        return true;
        
    }
    
    private boolean check(int tile) {	
        if (!onBoat) {		// 배 안 타고 있음
        		if (tile == 0 || tile == 8 || (tile <= 17 && tile >= 13))
        			return true;	// 통과 가능한 타일(땅 등등)이면 true를 반환함
        		else return false;
        }
        else {				// 배 타고 있음
        		if (tile == 3) // 통과 가능한 타일(바다)묜 true
        			return true;
        		else return false;
        }
            
    }
   

    public void draw(Graphics g, Camera c) {
	    	// 플레이어를 화면 중앙에 그리기
	    	// 카메라의 절대위치를 화면상의 좌표계로 보정함
        int sx = worldX - c.px;	
        int sy = worldY - c.py;

        if (onBoat) {
        		g.drawImage(ImageShow.ONBOAT, sx, sy, p.TILE_SIZE, p.TILE_SIZE, null);
            return;
        }
        
        if (!visible) return;
        
        if(dir == 0)	// 아래
        		g.drawImage(ImageShow.PLAYER1, sx, sy, player_img, player_img, null);
        if(dir == 1)	// 위
        		g.drawImage(ImageShow.PLAYER2, sx, sy, player_img, player_img, null);
        if(dir == 3)	// 왼쪽
        		g.drawImage(ImageShow.PLAYER3, sx, sy, player_img, player_img, null);
        if(dir == 2)	// 오른쪽
        		g.drawImage(ImageShow.PLAYER4, sx, sy, player_img, player_img, null);
    }
    
    
    // 장비 장착 (MainPanel에서 넘겨온 걸 현재 장비로 설정함)
    public void equip(CraftTool t) {
    		current = t;
    }
    
    // CraftTool current 
    // 상호작용 할 때 도구 검사용
    public CraftTool getCurrent() {
    		return current;
    }
    
    public void setVisible(boolean v) { 
    		visible = v; 
    }
    
    public int getSpeed() { 
    		return speed; 
    }
    public void setSpeed(int s) { 
    		speed = s; 
    }
}