import java.awt.*;

import javax.swing.ImageIcon;


class UIPanel {
	Player player;
	
	UIPanel(Player player){
		this.player = player;
	}
	public void drawTopUI(Graphics g, int hp, int hunger, int thirst, int day) {
		
        g.setColor(Color.BLACK);
        g.setFont(new Font("둥근모꼴", Font.BOLD, 25));
        g.drawString("DAY: " + day, 20, 30);

        drawBar(g, "HP", 30, 80, hp, Color.BLACK);
		drawBar(g, "HUNGER", 270, 80, hunger, Color.BLACK);
		drawBar(g, "THR", 510, 80, thirst, Color.BLACK);
		drawCurrentTool(g, player.getCurrent());
    }

	
	// 체력, 갈증, 허기 바
	private void drawBar(Graphics g, String label, int x, int y, int value, Color color) {
		int maxWidth = 200;
		int height = 5;
		int filled = (int)(maxWidth * (value / 100.0));
		
		g.setColor(Color.BLACK);
		g.drawString(label + ": " + value, x, y - 5);
		
		g.setColor(Color.BLACK);
		g.drawRect(x,  y,  maxWidth, height);
		
		g.setColor(color);
		g.fillRect(x + 1, y + 1, filled - 1, height - 1);
	}
	
	// 현재 들고 있는 무기 표시용
	public void drawCurrentTool(Graphics g, CraftTool t) {
	    int x = 750, y = 15;
	    int size = 80;

	    // 사각형 틀
	    g.setColor(Color.BLACK);
	    g.drawRect(x, y, size, size);

	    if (t == null) {
	        ImageIcon hand = ImageShow.HAND;
	        g.drawImage(hand.getImage(), x+2, y+2, size-4, size-4, null);
	        return;
	    }

	    // 장비 이미지
	    ImageIcon icon = t.getIcon();
	    if (icon != null) {
	        g.drawImage(icon.getImage(), x+2, y+2, size-4, size-4, null);
	    }

	    g.setColor(Color.BLACK);
	    g.setFont(new Font("둥근모꼴", Font.BOLD, 20));
	    g.drawString(t.durable + " / " + t.maxdurable, x, y + size + 20);
	}
}
