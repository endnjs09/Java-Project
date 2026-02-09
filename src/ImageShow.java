import javax.swing.ImageIcon;
import java.awt.Image;

class ImageShow {
	public static final int TOOL_ICON_SIZE = 120;
	public static final int ITEM_ICON_SIZE = 65;
    // 강화 패널 사이즈(배경용) 패널 크기에 맞춰서
    public static final int UPGRADE_W = 480;
    public static final int UPGRADE_H = 340;
    
    
    // ====== 아이콘 ======
    public static final ImageIcon AXE_1;
    public static final ImageIcon AXE_2;
    public static final ImageIcon AXE_3;
    public static final ImageIcon AXE_4;

    public static final ImageIcon PICK_1;
    public static final ImageIcon PICK_2;
    public static final ImageIcon PICK_3;
    public static final ImageIcon PICK_4;

    public static final ImageIcon ROD;
    public static final ImageIcon HAND;
    public static final ImageIcon BOAT_ICON;
    
    public static final ImageIcon AXE_HOVER;
    public static final ImageIcon PICK_HOVER;
    public static final ImageIcon ROD_HOVER;
    public static final ImageIcon BOAT_HOVER;
    public static final ImageIcon WOOD_ICON;
    public static final ImageIcon WOOD_HOVER;
    
    // ====== 자원 ======
    public static final ImageIcon STICK;
    public static final ImageIcon WOOD;
    public static final ImageIcon ROCK;
    public static final ImageIcon FRUIT;
    public static final ImageIcon FISH;
    public static final ImageIcon COOKEDFISH;
    public static final ImageIcon BOATICON;
    public static final ImageIcon IRON;
    public static final ImageIcon GOLD;

    // ====== 패널 배경 ======
    public static final Image UPGRADE_BG;
    public static final Image TOP_UI_BG;
    
    // ====== 플레이어 ======
    public static final Image PLAYER1;
    public static final Image PLAYER2;
    public static final Image PLAYER3;
    public static final Image PLAYER4;
    
    // ====== 타일 이미지 ======
    public static final Image SEA;
    public static final Image FISHING;
    public static final Image FISHING_TILE;
    
    public static final Image SAND1;
    public static final Image SAND2;
    public static final Image SAND3;
    public static final Image SAND4;
    public static final Image SAND5;
    public static final Image SAND6;
    public static final Image SAND7;
    public static final Image SAND8;
    
    public static final Image GRASS;
    public static final Image GRASS1;
    public static final Image GRASS2;
    public static final Image GRASS3;
    public static final Image GRASS4;
    public static final Image GRASS5;
    public static final Image GRASS6;
    public static final Image GRASS7;
    public static final Image GRASS8;
    
    public static final Image TREE1;
    public static final Image TREE2;

    public static final Image ROCK1;
    public static final Image ROCK2;
    public static final Image ROCK3;
    public static final Image ROCK4;
    
    public static final Image FIRE1;
    public static final Image FIRE2;

    public static final Image ONBOAT;
    
    public static final Image BOAT;
    
    public static final Image WATER;
    public static final Image BUSH;
    
    public static final Image HOUSE1;
    public static final Image HOUSE2;
    public static final Image HOUSE3;
    public static final Image HOUSE4;
    public static final Image UPGRADE;
    
    
    
    static {
        // 아이콘들
        AXE_1  = loadToolIcon("img/axe_1.png");
        AXE_2  = loadToolIcon("img/axe_2.png");
        AXE_3  = loadToolIcon("img/axe_3.png");
        AXE_4  = loadToolIcon("img/axe_4.png");

        PICK_1 = loadToolIcon("img/pick_1.png");
        PICK_2 = loadToolIcon("img/pick_2.png");
        PICK_3 = loadToolIcon("img/pick_3.png");
        PICK_4 = loadToolIcon("img/pick_4.png");

        ROD = loadToolIcon("img/rod.png");   
        HAND = loadToolIcon("img/hand.png");
        
        WOOD_ICON = loadToolIcon("img/wood.png");
        BOAT_ICON = loadToolIcon("img/boaticon.png");
        
        WOOD_HOVER = loadToolIcon("img/woodhover.png");
        AXE_HOVER  = loadToolIcon("img/axehover.png");
        PICK_HOVER  = loadToolIcon("img/pickhover.png");
        ROD_HOVER  = loadToolIcon("img/rodhover.png");
        BOAT_HOVER = loadToolIcon("img/boathover.png");
        

        // 강화 패널 배경
        UPGRADE_BG = loadBg("img/upgrade_bg.png");
        TOP_UI_BG = new ImageIcon("img/topui_bg.png").getImage();
        
        // 플레이어
        PLAYER1 = new ImageIcon("img/Player1.png").getImage();
        PLAYER2 = new ImageIcon("img/Player2.png").getImage();
        PLAYER3 = new ImageIcon("img/Player3.png").getImage();
        PLAYER4 = new ImageIcon("img/Player4.png").getImage();
        
        // 맵 타일
        SEA = new ImageIcon("img/Sea.png").getImage();
        FISHING = new ImageIcon("img/sand8f.png").getImage();
        FISHING_TILE = new ImageIcon("img/fishing.png").getImage();
        
        SAND1 = new ImageIcon("img/sand1.png").getImage();
        SAND2 = new ImageIcon("img/sand2.png").getImage();
        SAND3 = new ImageIcon("img/sand3.png").getImage();
        SAND4 = new ImageIcon("img/sand4.png").getImage();
        SAND5 = new ImageIcon("img/sand5.png").getImage();
        SAND6 = new ImageIcon("img/sand6.png").getImage();
        SAND7 = new ImageIcon("img/sand7.png").getImage();
        SAND8 = new ImageIcon("img/sand8.png").getImage();
        
        GRASS = new ImageIcon("img/grass0.png").getImage();
        GRASS1 = new ImageIcon("img/grass1.png").getImage();
        GRASS2 = new ImageIcon("img/grass2.png").getImage();
        GRASS3 = new ImageIcon("img/grass3.png").getImage();
        GRASS4 = new ImageIcon("img/grass4.png").getImage();
        GRASS5 = new ImageIcon("img/grass5.png").getImage();
        GRASS6 = new ImageIcon("img/grass6.png").getImage();
        GRASS7 = new ImageIcon("img/grass7.png").getImage();
        GRASS8 = new ImageIcon("img/grass8.png").getImage();
        
        TREE1 = new ImageIcon("img/tree1.png").getImage();
        TREE2 = new ImageIcon("img/tree2.png").getImage();
        
        ROCK1 = new ImageIcon("img/rock1.png").getImage();
        ROCK2 = new ImageIcon("img/rock2.png").getImage();
        ROCK3 = new ImageIcon("img/rock3.png").getImage();
        ROCK4 = new ImageIcon("img/rock4.png").getImage();
        
        FIRE1 = new ImageIcon("img/fire1.png").getImage();
        FIRE2 = new ImageIcon("img/fire2.png").getImage();
        
        ONBOAT = new ImageIcon("img/onboat.png").getImage();
        BOAT = new ImageIcon("img/boat.png").getImage();
        
        BUSH = new ImageIcon("img/bush.png").getImage();
        
        WATER = new ImageIcon("img/water.png").getImage();
        
        HOUSE1 = new ImageIcon("img/house1.png").getImage();
        HOUSE2 = new ImageIcon("img/house2.png").getImage();
        HOUSE3 = new ImageIcon("img/house3.png").getImage();
        HOUSE4 = new ImageIcon("img/house4.png").getImage();
        
        UPGRADE = new ImageIcon("img/upgrade.png").getImage();
        
        
        // 자원
        STICK = loadItemIcon("img/stick.png");
        WOOD = loadItemIcon("img/wood.png");
        ROCK = loadItemIcon("img/rock.png");
        FRUIT = loadItemIcon("img/fruit.png");
        FISH = loadItemIcon("img/fish.png");
        COOKEDFISH = loadItemIcon("img/cookedfish.png");
        BOATICON = loadItemIcon("img/boaticon.png");
        IRON = loadItemIcon("img/iron.png");
        GOLD = loadItemIcon("img/gold.png");
        
    }

    private static ImageIcon loadToolIcon(String path) {
        ImageIcon origin = new ImageIcon(path);
        Image scaled = origin.getImage().getScaledInstance(TOOL_ICON_SIZE, TOOL_ICON_SIZE, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
    
    private static ImageIcon loadItemIcon(String path) {
        ImageIcon origin = new ImageIcon(path);
        Image scaled = origin.getImage().getScaledInstance(ITEM_ICON_SIZE, ITEM_ICON_SIZE, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private static Image loadBg(String path) {	
        ImageIcon origin = new ImageIcon(path);
        return origin.getImage();   
    }
}
