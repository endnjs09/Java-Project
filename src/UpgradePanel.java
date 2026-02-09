import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UpgradePanel extends JPanel implements KeyListener {

    MainPanel p;
    Inventory inv;
    UpgradeSystem us;

    CraftTool tool;

    JLabel curIcon;		// 도구 이미지
    JLabel nextIcon;	// 다음 레벨 도구 이미지
    JLabel matLabel;	// 소모 재료 라벨
    JLabel resultLabel;		// 결과 라벨
    JLabel titleLabel;		// 제목
    Image bg;  // 배경 이미지 

    UpgradePanel(MainPanel p, Inventory inv, UpgradeSystem us) {
        this.p = p;
        this.inv = inv;
        this.us = us;

        setLayout(null);
        setVisible(false);
        setOpaque(false);

        // 배경 사용
        bg = ImageShow.UPGRADE_BG;

        // ======= 제목 =======
        titleLabel = new JLabel("강화", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("둥근모꼴", Font.BOLD, 26));
        titleLabel.setBounds(0, 20, ImageShow.UPGRADE_W, 40);
        add(titleLabel);

        // ======= 현재 도구 이미지 =======
        curIcon = new JLabel();
        curIcon.setOpaque(false);
        curIcon.setBounds(80, 90, ImageShow.TOOL_ICON_SIZE, ImageShow.TOOL_ICON_SIZE);
        add(curIcon);

        // ======= 화살표 =======
        JLabel arrow = new JLabel("→", SwingConstants.CENTER);
        arrow.setForeground(Color.BLACK);
        arrow.setFont(new Font("둥근모꼴", Font.BOLD, 28));
        arrow.setBounds(210, 115, 60, 60);
        add(arrow);

        // ======= 다음 레벨 이미지 =======
        nextIcon = new JLabel();
        nextIcon.setOpaque(false);
        nextIcon.setBounds(280, 90, ImageShow.TOOL_ICON_SIZE, ImageShow.TOOL_ICON_SIZE);
        add(nextIcon);

        // ======= 소모 재료 =======
        matLabel = new JLabel("", SwingConstants.CENTER);
        matLabel.setForeground(Color.BLACK);
        matLabel.setFont(new Font("둥근모꼴", Font.BOLD, 20));
        matLabel.setBounds(40, 230, 400, 40);
        add(matLabel);

        // ======= 결과 =======
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setForeground(Color.BLACK);
        resultLabel.setFont(new Font("둥근모꼴", Font.BOLD, 22));
        resultLabel.setBounds(40, 280, 400, 40);
        add(resultLabel);

        setFocusable(true);
        addKeyListener(this);
    }

    // 패널 배경
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, ImageShow.UPGRADE_W, ImageShow.UPGRADE_H, null);
    }
    
    // 소모 자원 보여줌
    private String matText(int level) {
	    	if (level == 1) {
	    	    return "소모 재료 : 돌 x1 (보유: " + inv.getRock() + ")";
	    	} 
	    	else if (level == 2) {
	    	    return "소모 재료 : 철조각 x1 (보유: " + inv.getIron() + ")";
	    	} 
	    	else if (level == 3) {
	    	    return "소모 재료 : 금조각 x1 (보유: " + inv.getGold() + ")";
	    	} 
	    	else {
	    	    return "더 이상 강화 불가";
	    	}
    }

    // 강화창 열면 보이는 Panel 구성
    public void openTool(CraftTool t) {
    		tool = t;	// 현재 강화할 도구
        resultLabel.setText("");

        curIcon.setIcon(t.getIcon());			// 현재 레벨 도구 이미지
        nextIcon.setIcon(nextLevelIcon(t));		// 다음 레벨 도구 이미지

        matLabel.setText(matText(t.level));		// 현재 레벨에 필요한 자원 표시용
        titleLabel.setText(t.name + " 강화");		// 돌도끼 강화

        setVisible(true);
        requestFocusInWindow();
    }
 
    // 다음 레벨 아이콘 
    private ImageIcon nextLevelIcon(CraftTool t) {
    	
        int nextLevel = t.level + 1;		// 레벨 1증가
        
        if (nextLevel > 4) return null;
        
        // 도구가 도끼인 경우
        if (t.type == CraftTool.AXE) {
            if (nextLevel == 1) {
                return ImageShow.AXE_1;
            } 
            else if (nextLevel == 2) {
                return ImageShow.AXE_2;
            } 
            else if (nextLevel == 3) {
                return ImageShow.AXE_3;
            } 
            else if (nextLevel == 4) {
                return ImageShow.AXE_4;
            }
        }
        
        // 도구가 곡괭이인 경우
        else if (t.type == CraftTool.PICKAXE) {
            if (nextLevel == 1) {
                return ImageShow.PICK_1;
            } 
            else if (nextLevel == 2) {
                return ImageShow.PICK_2;
            } 
            else if (nextLevel == 3) {
                return ImageShow.PICK_3;
            } 
            else if (nextLevel == 4) {
                return ImageShow.PICK_4;
            }
        }

        return null;
    }
    
    
    
    
    
    
    // 강화 기능적인 부분 (스페이스바)
    private void tryUpgrade() {		
        if (tool == null) return;

        boolean success = us.upgradeTool(tool);	
        // upgradeSystem에서 강화를 수행하고 확률에 의해 성공했는지 안했는지만 판단

        if (!success) {	// 실패시
            resultLabel.setText("강화 실패!");
        } 
        else {			// 성공시
            resultLabel.setText("강화 성공!");

            // 아이콘 갱신
            // 강화 성공하면 upgradeSystem에서 레벨이 1 증가함
            // 그거에 맞춰서 현재 도구 이미지, 다음 도구 이미지 갱신
            curIcon.setIcon(tool.getIcon());	
            nextIcon.setIcon(nextLevelIcon(tool));

            p.repaint();   // UI도 같이 갱신
        }

        matLabel.setText(matText(tool.level));

        new Timer(2000, e -> resultLabel.setText("")) {{	// 2초뒤에 사라지게
            setRepeats(false);
            start();
        }};
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {	// 강화시도
            tryUpgrade();	
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {	// 나가기
            setVisible(false);
            p.moveTimer.start();
            p.at.startAll();
        }
    }

    @Override 
    public void keyReleased(KeyEvent e) {	
    }
    
    @Override 
    public void keyTyped(KeyEvent e) {
    }
}