import javax.swing.*;


import java.awt.*;
import java.awt.event.*;

class CraftPanel extends JPanel implements ActionListener {
	
	Inventory inv;
	MainPanel p;
	JButton wood, axe, pick, rod, boat;
	
	CraftPanel(MainPanel p, Inventory inv) {
		this.p = p;
		this.inv = inv;		// 자원 소모
		setLayout(null);
		setOpaque(false);
		
		wood = new JButton(ImageShow.WOOD_ICON);
		wood.addActionListener(this);
		wood.setBounds(15, 80, 120, 120);
		wood.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		wood.setIcon(ImageShow.WOOD_HOVER);		// hovering(마우스 올리면 진하게 변함)
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		wood.setIcon(ImageShow.WOOD_ICON);
	        	}
        });
		wood.setBorderPainted(false);					// 버튼의 외곽선 그릴지 여부
		wood.setContentAreaFilled(false);				// 버튼을 투명하게 만들지 (true 기본값)
		wood.setFocusPainted(false);					// 버튼의 테두리 보이게 할지 
		wood.setRolloverEnabled(true);					// 마우스를 올렸을 때 아이콘 변경 할지
		wood.setRolloverIcon(ImageShow.WOOD_HOVER);		// 마우스를 올렸을 때 보여줄 아이콘을 지정
		
		
		axe = new JButton(ImageShow.AXE_1);
		axe.addActionListener(this);
		axe.setBounds(135, 80, 120, 120);
        axe.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		axe.setIcon(ImageShow.AXE_HOVER);
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		axe.setIcon(ImageShow.AXE_1);
	        	}
        });
		axe.setBorderPainted(false);					// 버튼의 외곽선 그릴지 여부
		axe.setContentAreaFilled(false);				// 버튼을 투명하게 만들지 (true 기본값)
		axe.setFocusPainted(false);						// 버튼의 테두리 보이게 할지 
		axe.setRolloverEnabled(true);					// 마우스를 올렸을 때 아이콘 변경 할지
        axe.setRolloverIcon(ImageShow.AXE_HOVER);		// 마우스를 올렸을 때 보여줄 아이콘을 지정
		
		
		pick = new JButton(ImageShow.PICK_1);
		pick.addActionListener(this);
		pick.setBounds(255, 80, 120, 120);
		pick.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		pick.setIcon(ImageShow.PICK_HOVER);
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		pick.setIcon(ImageShow.PICK_1);
	        	}
        });
		pick.setBorderPainted(false);					// 버튼의 외곽선 그릴지 여부
		pick.setContentAreaFilled(false);				// 버튼을 투명하게 만들지 (true 기본값)
		pick.setFocusPainted(false);						// 버튼의 테두리 보이게 할지 
		pick.setRolloverEnabled(true);					// 마우스를 올렸을 때 아이콘 변경 할지
		pick.setRolloverIcon(ImageShow.PICK_HOVER);		// 마우스를 올렸을 때 보여줄 아이콘을 지정
		
		
		
		rod = new JButton(ImageShow.ROD);
		rod.addActionListener(this);
		rod.setBounds(45, 250, 120, 120);
		rod.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		rod.setIcon(ImageShow.ROD_HOVER);
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		rod.setIcon(ImageShow.ROD);
	        	}
        });
		rod.setBorderPainted(false);					// 버튼의 외곽선 그릴지 여부
		rod.setContentAreaFilled(false);				// 버튼을 투명하게 만들지 (true 기본값)
		rod.setFocusPainted(false);						// 버튼의 테두리 보이게 할지 
		rod.setRolloverEnabled(true);					// 마우스를 올렸을 때 아이콘 변경 할지
		rod.setRolloverIcon(ImageShow.ROD_HOVER);		// 마우스를 올렸을 때 보여줄 아이콘을 지정
		
		
		boat = new JButton(ImageShow.BOAT_ICON);
		boat.addActionListener(this);
		boat.setBounds(220, 250, 120, 120);
		boat.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseEntered(MouseEvent e) {
	        		boat.setIcon(ImageShow.BOAT_HOVER);
	        	}
	        	@Override
	        	public void mouseExited(MouseEvent e) {
	        		boat.setIcon(ImageShow.BOAT_ICON);
	        	}
        });
		boat.setBorderPainted(false);					// 버튼의 외곽선 그릴지 여부
		boat.setContentAreaFilled(false);				// 버튼을 투명하게 만들지 (true 기본값)
		boat.setFocusPainted(false);					// 버튼의 테두리 보이게 할지 
		boat.setRolloverEnabled(true);					// 마우스를 올렸을 때 아이콘 변경 할지
		boat.setRolloverIcon(ImageShow.BOAT_HOVER);		// 마우스를 올렸을 때 보여줄 아이콘을 지정
		
		add(wood);
		add(axe);
		add(pick);
		add(rod);
		add(boat);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean crafted = false;		// 제작되면 Panel 닫는 용도 (true: 제작됨)
		
		if (e.getSource() == wood) {
			if (inv.getStick() >= 3) {
				inv.add("stick", -3);
				inv.add("wood", 1);
				p.showMessage("목재를 만들었다.");
				crafted = true; 
			}
			else p.showMessage("재료가 부족하다...");
		}
		
		if (e.getSource() == axe) {
			if (inv.getWood() >= 1 && inv.getStick() >= 3) {
				inv.add("wood", -1);
				inv.add("stick", -3);
				inv.addTool(new CraftTool("도끼", CraftTool.AXE, 50)); 	// 이름: 도끼, AXE (2번), 내구도 50
				p.showMessage("도끼를 만들었다.");
				crafted = true; 
			}
			else p.showMessage("재료가 부족하다...");
		}
		
		if (e.getSource() == pick) {
			if (inv.getWood() >= 1 && inv.getStick() >= 3) {
				inv.add("wood", -1);
				inv.add("stick", -3);
				inv.addTool(new CraftTool("곡괭이", CraftTool.PICKAXE, 50));
				p.showMessage("곡괭이를 만들었다.");
				crafted = true; 
			}
			else p.showMessage("재료가 부족하다...");
		}
		
		if (e.getSource() == rod) {
			if (inv.getWood() >= 1 && inv.getStick() >= 3) {
				inv.add("wood", -1);
				inv.add("stick", -3);
				inv.addTool(new CraftTool("낚시대", CraftTool.ROD, 50));
				p.showMessage("낚싯대를 만들었다.");
				crafted = true; 
			}
			else p.showMessage("재료가 부족하다...");
		}
		
		if (e.getSource() == boat) {
		    if (inv.getWood() >= 15 && inv.getStick() >= 10) {
		        inv.add("wood", -15);
		        inv.add("stick", -10);
		        inv.add("boat", 1);

		        p.showMessage("배를 만들었다.");
		        crafted = true;
		    }
		    else {
		        p.showMessage("재료가 부족하다...");
		    }
		}


		if (crafted) {					// 제작되면 Panel 닫기
			p.cp.setVisible(false);
			p.moveTimer.start();
			p.requestFocusInWindow();
		}
		p.requestFocusInWindow();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		g.setColor(new Color(40, 40, 40, 220));
		g.fillRect(0,  0, getWidth(), getHeight());
	
		

		g.setColor(Color.WHITE);
		g.setFont(new Font("둥근모꼴", Font.BOLD, 25));
		g.drawString("제작", 164, 50);
		
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("둥근모꼴", Font.BOLD, 15));
		g.drawString("목재 제작", 40, 220);
		g.drawString("도끼 제작", 150, 220);
		g.drawString("곡괭이 제작", 260, 220);
		
		g.drawString("낚시대 제작", 50, 385);
		g.drawString("배 제작", 260, 385);
	}
}
