import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

class MainPanel extends JPanel implements KeyListener, ActionListener {

    public static final int TILE_SIZE = 65;			// 타일크기 (픽셀단위) 65px
    public static final int SCREEN_WIDTH = 22;		// 화면에 보일 가로 사이즈
    public static final int SCREEN_HEIGHT = 13;		// 화면에 보일 세로 사이즈

    Camera camera;		// 카메라
    World world;			// 맵
    Player player;		// 플레이어
    Resources r;			// 상호작용 가능한 자원
    Interact it;			// 상호작용 위치
    BuildSystem build;	// 설치 시스템 (범위)
    Inventory inven;		// 인벤토리 
    AllTimer at;			// 모든 타이머
    Timer moveTimer;		// 플레이어를 갱신하는 타이머
    Build b;				// 설치할 오브젝트
    UpgradeSystem us;	// 강화 시스템
    CraftTool ct;		// 도구 (도끼, 곡괭이, 낚시대)
    
    HelpPanel help;		// 도움말
    CraftPanel cp;		// 제작 패널
    InventoryPanel ip;	// 인벤토리 패널
    UIPanel topUI;	 	// 상단 UI
    BuildPanel bp;	 	// 설치 패널
    UpgradePanel up;	 	// 강화 패널
    GamePanel game;   	// 맵 패널
    
    JLabel message;		
    JButton ModeButton;	// 설치 버튼
    Random rand = new Random();

    boolean isPressed = false;	// 키 꾹 누름 방지 (누를 때 true, 땔 때 false)
    boolean isInstall = false;	// 오브젝트 설치 가능 여부 (평상시 false, 설치 모드 true)s
    boolean helpOn = false;		// 도움말 열고 닫기
    boolean messageActive = false;	// 메세지 깜빡거리는 거 방지하기
    
    int hunger = 100, thirst = 100, hp = 100, day = 1;	
    // 허기, 갈증, hp, 날짜
    int hungerRate = 1;		// 허기 감소율
    int thirstRate = 1;		// 갈증 감소율
   
    Timer messageT;
    
    MainPanel() {
        setLayout(null);
        setFocusable(true);
        addKeyListener(this);

        inven = new Inventory();
        // 인벤토리
        
        world = new World();
        // 맵 
        
        camera = new Camera(SCREEN_WIDTH, SCREEN_HEIGHT);	
        // 가로, 세로 22, 13개 타일만 보여줌
              
        player = new Player(this, world, 36, 66);			
        // 플레이어의 위치 초기화 (36, 66)부터 시작, 충돌감지 할 때 world의 맵이 필요함

        r = new Resources(this, world, inven, player);		
        // 오브젝트에 상호작용 (멥의 데이터(타일 변경 등), 자원 추가, 배탈 때 플레이어 위치가 필요)
        
        it = new Interact(this, player, r);
        // 상호작용 범위, 플레이어 위치기반, Resource의 메서드 호출
          
        build = new BuildSystem(player);			
        // 플레이어 위치 기반으로 영역 갱신
        
        b = new Build(this, world, inven, player);	
        // 맵 (타일 바꾸기), 자원소모, 플레이어 방향
        
        us = new UpgradeSystem(inven);
        // 강화 시스템
             
        // ================= Panel =====================
        topUI = new UIPanel(player);
        JPanel uiLayer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
        		g.drawImage(ImageShow.TOP_UI_BG, 0, 0, 1280, 130, null);
                topUI.drawTopUI(g, hp, hunger, thirst, day);
            }
        };
        uiLayer.setOpaque(false);			
        uiLayer.setBounds(0, 0, 1280, 130);
        
        game = new GamePanel(this, player, camera, build, world);
        game.setBounds(0, 100, 1280, 800);

        // 인벤 Panel
        ip = new InventoryPanel(inven);
        ip.setBounds(440, 300, 400, 400);
        ip.setVisible(false);
        
        
        // 설치 Panel
        bp = new BuildPanel(this, build, b);
        bp.setBounds(440, 300, 400, 400);
        bp.setVisible(false);
        
        
        // 제작 Panel
        cp = new CraftPanel(this, inven);
        cp.setBounds(440, 300, 400, 400);
        cp.setVisible(false);

        
        // 강화 Panel
        up = new UpgradePanel(this, inven, us);
        up.setBounds(400, 200, 480, 400);
        up.setVisible(false);

        
        // help Panel
        help = new HelpPanel();
        help.setBounds(280, 50, 700, 800);
        help.setVisible(false);
        
        
        // 메시지 
        message = new JLabel("");
        message.setForeground(Color.WHITE);
        message.setBounds(20, 800, 400, 70);
        message.setFont(new Font("둥근모꼴", Font.BOLD, 20));

        
        // 설치 모드
        ImageIcon installbt = new ImageIcon("img/installbt.png");		// 기본 버튼
        ImageIcon installbt2 = new ImageIcon("img/installbt2.png");		// 마우스 올렸을 때
        ModeButton = new JButton(installbt);
        ModeButton.setBounds(1130, 135, 120, 48);
        ModeButton.addActionListener(this);
        ModeButton.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		ModeButton.setIcon(installbt2);
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		ModeButton.setIcon(installbt);
	        	}
        });
        ModeButton.setBorderPainted(false);			// 버튼의 외곽선 그릴지 여부
        ModeButton.setContentAreaFilled(false);		// 버튼을 투명하게 만들지 (true 기본값)
        ModeButton.setFocusPainted(false);			// 버튼의 테두리 보이게 할지 
        ModeButton.setRolloverEnabled(true);		// 마우스를 올렸을 때 아이콘 변경 할지
        ModeButton.setRolloverIcon(installbt2);		// 마우스를 올렸을 때 보여줄 아이콘을 지정
        
        
        // 타이머
        moveTimer = new Timer(10, this);
        moveTimer.start();

        at = new AllTimer(this, player);
        at.startAll();
        
        
        add(help);
        add(up);
        add(ip);
        add(bp);
        add(cp);
        add(ModeButton);
        add(message);
        add(uiLayer);
        add(game);
        
        setFocusable(true);
        requestFocusInWindow();
    }

    
    
    
    
    // ===== 메시지 출력 =====
    public void showMessage(String s) {
        message.setText(s);
        
        if(messageActive) return;
        
        messageActive = true;
        
        messageT = new Timer(2000, e -> {
	        	message.setText("");
	        	messageActive = false;
	        	messageT.stop();
        });
        
        messageT.setRepeats(false);
        messageT.start();
    }
    
    
    
    
    
    public void closeAllPanel() {	// 패널들 중복으로 열리는 거 방지
        ip.setVisible(false);
        cp.setVisible(false);
        bp.setVisible(false);
        up.setVisible(false);
    }

    
    
    
    public void increaseRate() {
        hungerRate++;
        thirstRate++;
    }
    
    public int getHungerRate() { 
    		return hungerRate; 
    }
	
    public int getThirstRate() { 
    		return thirstRate; 
    }
    
    
    
    
    
    // ===== 이벤트 =====
    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        camera.update(player);
        
        // 설치모드
        if(e.getSource() == ModeButton) {
	        	isInstall = !isInstall;
	        	build.updateTile();
	        	requestFocusInWindow(); 
        }
        repaint();
    }

    
    
    
    
    @Override
    public void keyPressed(KeyEvent e) { 	
    	
        // F 설치모드 패널 (BuildPanel 오픈)
        if (isInstall && e.getKeyCode() == KeyEvent.VK_F) {
            if (bp.isVisible()) {
                // 열려있으면 닫기
                closeAllPanel();
                moveTimer.start();
            } else {
                // 닫혀있으면 열기
                closeAllPanel();
                bp.setVisible(true);
                moveTimer.stop();
            }
            return;
        }
        
        
        // 플레이어 이동 관련 
        player.keyPressed(e);
        
        
        // 도웅말 Panel      
        if (e.getKeyCode() == KeyEvent.VK_F1) {
	        	helpOn = !helpOn;
	        	
	        	help.setVisible(helpOn);	
	        	repaint();
        }
        
        
        // F 상호작용
        if (e.getKeyCode() == KeyEvent.VK_F && !isPressed) {
            isPressed = true;
            it.Interaction();
        }
        

        // 제작 패널 (V)
        if (e.getKeyCode() == KeyEvent.VK_V) {
            if (cp.isVisible()) {
                closeAllPanel();	
                moveTimer.start();
            } 
            else {
                closeAllPanel();
                cp.setVisible(true);
                moveTimer.stop();
            }
        }
        
        
        // 인벤토리 (B)
        if (e.getKeyCode() == KeyEvent.VK_B) {

            if (ip.isVisible()) {
                closeAllPanel();
                moveTimer.start();
            } 
            else {
                closeAllPanel();
                ip.setVisible(true);
                moveTimer.stop();
            }
        }
        
        
        // 장비 장착 (1~4)
        if (e.getKeyCode() == KeyEvent.VK_1) {
            player.equip(null);		// 플레이어 장비를 null로 갱신
            showMessage("맨손");
        }

        if (e.getKeyCode() == KeyEvent.VK_2) {
            CraftTool axe = inven.getToolType(CraftTool.AXE);	
            // 인벤토리에서 ToolType이 도끼 2번(있다면)을 불러옴
            if (axe != null) {				// axe == 2번이면
                player.equip(axe);			// 플레이어 장비를 도끼로	
                showMessage("도끼 장착");
            } 
            else showMessage("도끼가 없다.");
        }

        if (e.getKeyCode() == KeyEvent.VK_3) {
            CraftTool pick = inven.getToolType(CraftTool.PICKAXE);
            if (pick != null) {
                player.equip(pick);
                showMessage("곡괭이 장착");
            } 
            else showMessage("곡괭이가 없다.");
        }

        if (e.getKeyCode() == KeyEvent.VK_4) {
            CraftTool rod = inven.getToolType(CraftTool.ROD);
            if (rod != null) {
                player.equip(rod);
                showMessage("낚시대 장착");
            } 
            else showMessage("낚시대가 없다.");
        }   
        
       
        // 음식 먹기
        if (e.getKeyCode() == KeyEvent.VK_Z) {
	        	if (inven.getCookedFish() > 0) {
	        		inven.add("cookedfish", -1);
	        		hunger += 30;
	        		if (hunger > 100) 
	        			hunger = 100;
	        		
	        		showMessage("익힌 생선을 먹었다.");
		        return;
	        	}
        	
	        	if (inven.getFruit() > 0) {
	            inven.add("fruit", -1);
	            hunger += 5;
	            if (hunger > 100) 
	                	hunger = 100;
	
	            showMessage("과일을 먹었다.");
	            return;
	        }
	        	
	        	if(inven.getFish() > 0) {
	        		inven.add("fish", -1);
	        		hunger += 15;
	        		if (hunger > 100) hunger = 100;
	        		
	        		if(rand.nextDouble() < 0.2) {
	        			thirst -= 50;
	        			if (thirst < 0) 
	        				thirst = 0;
	        			showMessage("식중독에 걸렸다...");
	        		}
	        		else showMessage("생선을 먹었다.");
	        		
	        		return;
	        	}

            if (inven.fish == 0 && inven.fruit == 0 && inven.cookedfish == 0) {
            		showMessage("먹을 것이 없다...");
            		return;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
        if (e.getKeyCode() == KeyEvent.VK_F)
            isPressed = false;
    }

    @Override 
    public void keyTyped(KeyEvent e) {}
    

}