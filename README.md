# Island - Java Swint/AWT 기반 무인도 생존 시뮬레이션

이름: Island
목표: 자원 채집, 도구 제작, 시설 확충을 통한 30일 생존
개발 환경: Java 17 / Eclipse IDE / Git

<시간 시스템> 게임 내 30일 생존 기한을 관리하는 타이머 로직	
<상호작용>	월드 내 자원(나무, 돌 등) 채집 및 오브젝트 반응 처리
<제작/건축> 수집된 자원을 소모하여 도구 제작 및 생존 시설 설치
<장비 강화> 장비의 능력치 강화	
<데이터 관리> 인벤토리 시스템 및 월드 데이터 동기화	
<GUI 렌더링> Swing 기반의 실시간 게임 화면 및 UI 패널 구성

Core
- AllTimer.java: 시간 흐름 및 생존 일수 계산
- BuildSystem.java / UpgradeSystem.java: 설치 및 강화 로직
- Player.java / World.java: 캐릭터 정보 및 맵 데이터 관리

User Interface (UI)
- MainPanel.java / GamePanel.java: 메인 게임 화면 렌더링
- InventoryPanel.java / CraftPanel.java: 아이템 및 제작 인터페이스
- BuildPanel.java / UpgradePanel.java: 시설, 강화 관련 메뉴 구성

Interaction & Resources
- Interact.java: 오브젝트 상호작용 처리
- Resources.java: 자원 데이터 정의
- ImageShow.java: 그래픽 리소스 로딩 및 출력
