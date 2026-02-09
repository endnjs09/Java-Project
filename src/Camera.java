
class Camera {
    // 화면의 왼쪽 위 모서리의 월드 좌표 
    // 화면에 무엇을 그릴지 결정할 때 기준이 되는 좌표
    int px, py;

    // 한 화면에 보이는 타일 개수 (가로, 세로)
    // 16 x 9 이런 식으로, 카메라가 한 번에 보여줄 타일 수
    private final int tileCountW, tileCountH;	// 가로 개수, 세로 개수

    Camera(int x, int y) {	 // MainPanel 에서 22, 13 넘김
        this.tileCountW = x;  // 가로 타일 개수
        this.tileCountH = y;  // 세로 타일 개수
    }

    // 카메라 위치를 플레이어 위치 기준으로 갱신하는 메소드
    // p: 플레이어 (플레이어 픽셀 좌표 worldX, worldY를 사용)
    public void update(Player player) {

        // 화면(카메라)의 가로 세로 픽셀 크기
        // 타일 크기가 65이고 가로 세로 개수가 각각 22, 13이면 화면 크기는 1430px x 845px
        int screenW = tileCountW * MainPanel.TILE_SIZE;
        int screenH = tileCountH * MainPanel.TILE_SIZE;

        // 카메라의 기본 위치 계산(좌상단), 플레이어를 화면 정중앙에 둠
        px = player.worldX - screenW / 2;
        py = player.worldY - screenH / 2;
        

        // 월드 전체의 픽셀 단위 크기
        int worldW = World.WORLD_WIDTH * MainPanel.TILE_SIZE;
        int worldH = World.WORLD_HEIGHT * MainPanel.TILE_SIZE;
        
        // 카메라 범위 제한(월드 밖으로 안 나가게 막기) 
        // 없으면 카메라가 맵 밖으로 나감
        if (px < 0) px = 0;
        if (py < 0) py = 0;
        if (px > worldW - screenW) px = worldW - screenW;
        if (py > worldH - screenH) py = worldH - screenH;
    }
}








