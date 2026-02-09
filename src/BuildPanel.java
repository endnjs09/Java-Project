import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

class BuildPanel extends JPanel implements ActionListener{
	MainPanel p;
	BuildSystem build;
	Build b;
	JButton b1, b2, b3, b4;
	
	BuildPanel(MainPanel p, BuildSystem build, Build b) {
		this.p = p;
		this.build = build;
		this.b = b;
		setLayout(null);
		setOpaque(false);
		
		b1 = new JButton("집 짓기");
		b1.addActionListener(this);
		b2 = new JButton("불 피우기");
		b2.addActionListener(this);
		b3 = new JButton("물통 설치");
		b3.addActionListener(this);
		b4 = new JButton("강화대 설치");
		b4.addActionListener(this);
		
		b1.setBounds(75, 50, 250, 40);
		b2.setBounds(75, 110, 250, 40);
		b3.setBounds(75, 170, 250, 40);
		b4.setBounds(75, 230, 250, 40);
		
		
		add(b1); add(b2); add(b3); add(b4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) {
			int x = build.buildX;		// 설치할 위치 x
			int y = build.buildY;		// 설치할 위치 y
			b.BuildHouse(x, y);			// 이 위치에 집 짓기
		}
		if(e.getSource() == b2) {
			int x = build.buildX;
			int y = build.buildY;
			b.BuildFire(x, y);
		}
		if(e.getSource() == b3) {
			int x = build.buildX;
			int y = build.buildY;
			b.BuildWater(x, y);
		}

		if(e.getSource() == b4) {
			int x = build.buildX;
			int y = build.buildY;
			b.BuildUpgrade(x, y);
		}
		
		p.isInstall = false;     // 설치모드 OFF, 평상시로 전환
		p.bp.setVisible(false);  // 패널 닫기
		p.moveTimer.start();     // 움직임 재시작
		p.at.startAll();         // 타이머 재시작
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(40, 40, 40, 220));
		g.fillRect(0,  0, getWidth(), getHeight());
	}
}
