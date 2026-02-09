import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class InventoryPanel extends JPanel{
	Inventory inven;
	MainPanel p;
	JButton b1;
	
	InventoryPanel(Inventory inven) {
		this.inven = inven;
		setLayout(null);
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(40, 40, 40, 220));
		g.fillRect(0,  0, getWidth(), getHeight());
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("둥근모꼴", Font.BOLD, 25));
		g.drawString("가방", 18, 50);
		
		
		int[] res = {		// 자원 개수들 
				inven.getStick(),
				inven.getWood(),
				inven.getRock(),
				inven.getFruit(),
				inven.getFish(),
				inven.getCookedFish(),
				inven.getIron(),
				inven.getGold(),
				inven.getBoat()
		};
		
		String[] name = { "나무가지", "목재", "돌", "열매", "생선", "익힌생선", "철조각", "금조각", "배" };
		
		ImageIcon[] icon = {
				ImageShow.STICK,
			    ImageShow.WOOD,
			    ImageShow.ROCK,
			    ImageShow.FRUIT,
			    ImageShow.FISH,
			    ImageShow.COOKEDFISH,
			    ImageShow.IRON,
			    ImageShow.GOLD,
			    ImageShow.BOATICON

		};
		
		int size = 65;	// 슬롯 사이즈
		int p = 10;		// 슬롯간 간격
		int startX = 18;	// 칸 시작 x 좌표 (Panel 내부 픽셀 좌표)
		int startY = 90;	// 칸 시작 y 좌표
		int index = 0;
		g.setFont(new Font("둥근모꼴", Font.PLAIN, 10));	
		
		for (int i = 0; i< 3; i++) {
			for (int j = 0; j < 5; j++) {
				// size + p 만큼 사각형 오른쪽, 아래로 그림 				
				int x = startX + j * (size + p);		
				int y = startY + i * (size + p);
				// j = 0 >> x = 18 + 0 * (65 + 10)
				// j = 1 >> x = 18 + 1 * (65 + 10) = 93
				// j = 2 >> x = 18 + 2 * (65 + 10) = 168 
				
				g.setColor(Color.GRAY);			
				g.drawRect(x, y, size, size);	// 네모 칸
				
				if (index < res.length) {		
					g.drawImage(icon[index].getImage(), x + 3, y, size - 5, size - 5, null);
					g.setColor(Color.WHITE);
					g.drawString(name[index] + ": " + res[index], x + 3, y + 60);
				}
				index++;
			}
		}
	}
}
