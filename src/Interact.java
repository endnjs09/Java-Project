import javax.swing.*;


class Interact {
	MainPanel p;
	Player player;	// 플레이어의 위치 기반
	Resources r;	// Resource의 상호작용 호출

	Interact(MainPanel p, Player player, Resources r) {
		this.player = player;
		this.p = p;
		this.r = r;
	}
	
	public void Interaction() {
		// 플레이어 좌표 중심점의 타일 위치
		int px = (player.worldX + player.player_img / 2) / MainPanel.TILE_SIZE;
		int py = (player.worldY + player.player_img / 2) / MainPanel.TILE_SIZE;

	    int dx = 0, dy = 0;	// 방향
	    
	    // 플레이어가 바라보는 방향(마지막에 누른 키)
	    switch (player.dir) {
	        case 1 -> dy = -1;  // 위
	        case 0 -> dy = 1;   // 아래
	        case 2 -> dx = -1;  // 왼쪽
	        case 3 -> dx = 1;   // 오른쪽
	    }
	    
	    // 위치와 방향의 합
	    int tx = px + dx;	
	    int ty = py + dy;
	    
	    r.Interact(tx, ty);
	}
}
