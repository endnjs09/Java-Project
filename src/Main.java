import javax.swing.*;

class Main extends JFrame {
	MainPanel p;
	Main() {
		setSize(1280, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		p = new MainPanel();
		p.setBounds(0, 0, 1280, 900);
		add(p);
		
		setVisible(true);           // 먼저 보이게 하고
	}

}
