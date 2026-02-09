

class BuildSystem {
	Player player;
	int buildX, buildY;
	
	BuildSystem(Player player){
		this.player = player;
	}
	
	public void updateTile() {
		// 플레이어 (중심)위치, 플레이어가 어느 타일에 있는지
		int px = (player.worldX + player.player_img / 2) / MainPanel.TILE_SIZE;	
		int py = (player.worldY + player.player_img / 2) / MainPanel.TILE_SIZE;
		
		buildX = px;	
		buildY = py;
		
		// 플레이어가 바라보는 방향 확인 후 갱신함
		if (player.dir == 0) 	// 아래
			buildY = py + 1;	
		if (player.dir == 1) 	// 위
			buildY = py - 1;		
		if (player.dir == 2) 	// 왼쪽
			buildX = px - 1;		
		if (player.dir == 3) 	// 오른쪽
			buildX = px + 1;

		// 범위 (맵 밖에 나가는 거 방지)
		if (buildX < 0) buildX = 0;
		if (buildY < 0) buildY = 0;
		if (buildX >= World.WORLD_WIDTH) buildX = World.WORLD_WIDTH - 1;
		if (buildY >= World.WORLD_HEIGHT) buildY = World.WORLD_HEIGHT - 1;
	}
	
	public int getBuildX() {
		return buildX;
	}
	
	public int getBuildY() {
		return buildY;
	}
	
	
}
