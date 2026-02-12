# 🏝️Island - Java Swint/AWT 기반 무인도 생존 시뮬레이션

📌 Project Overview
목표: 한정된 자원을 관리하며 30일 동안 무인도에서 생존하기
주요 특징: Timer를 활용한 시간 시스템, 자원 채집 및 오브젝트 상호작용, 제작 및 강화 시스템 구현
진행 시기: 대학교 2학년 2학기 객체지향 프로그래밍 기말 프로젝트


🛠 Tech Stack
Language: Java 17
Library: Java Swing, AWT (GUI 및 그래픽 렌더링)
IDE: Eclipse IDE
Version Control: Git


이름: Island
목표: 자원 채집, 도구 제작, 시설 확충을 통한 30일 생존
개발 환경: Java 17 / Eclipse IDE / Git

✨ Key Features
1. 시간 및 생존 관리 (Time System)
- AllTimer.java를 통한 게임 내 시간 흐름 제어
- 30일이라는 제한된 생존 기한 내의 이벤트 스케줄링 및 일수 계산 로직 구현

2. 상호작용 및 자원 시스템 (Interaction & Resources)
- 월드 내 나무, 돌 등 다양한 자원 오브젝트와의 충돌 체크 및 상호작용 처리 (Interact.java)
- 자원별 데이터 정의 및 리스폰 시스템 (Resources.java)

3. 제작 및 건축 (Crafting & Building)
- 채집한 자원을 소모하여 생존에 필요한 도구 제작 및 시설물 설치 (BuildSystem.java)
- 장비의 능력치를 높여 생존 확률을 올리는 강화 시스템 (UpgradeSystem.java)

4. 데이터 및 GUI 렌더링 (Data & UI)
- 인벤토리 시스템 및 캐릭터 상태(HP, 허기 등) 데이터 관리
- Swing 패널 구조를 활용한 계층적 UI 설계 (Main, Game, Inventory, Craft, Build, Upgrade Panel)
- ImageShow.java를 이용한 실시간 그래픽 리소스 렌더링 최적화


📁 Project Structure
src/
 ├── Core/
 │    ├── AllTimer.java       # 시간 흐름 및 생존 일수 관리
 │    ├── BuildSystem.java    # 시설 설치 로직
 │    ├── UpgradeSystem.java  # 장비 강화 시스템
 │    ├── Player.java         # 캐릭터 상태 및 정보
 │    └── World.java          # 맵 데이터 및 월드 관리
 ├── UI/
 │    ├── MainPanel.java      # 메인 화면
 │    ├── GamePanel.java      # 실시간 게임 화면
 │    ├── InventoryPanel.java # 아이템 보관함
 │    ├── CraftPanel.java      # 도구 제작 메뉴
 │    └── BuildUpgradePanel.java # 건축 및 강화 UI
 └── Interaction/
      ├── Interact.java       # 오브젝트 상호작용 처리
      ├── Resources.java      # 자원 데이터 정의
      └── ImageShow.java      # 그래픽 리소스 로딩 및 출력
