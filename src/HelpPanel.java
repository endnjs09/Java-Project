import java.awt.*;
import javax.swing.*;

class HelpPanel extends JPanel {

    HelpPanel() {
        setLayout(null);
        setOpaque(true);
        setBackground(new Color(20, 20, 20, 230));   // 반투명 검은 배경

        // 제목
        JLabel title = new JLabel("가이드");
        title.setFont(new Font("둥근모꼴", Font.BOLD, 35));
        title.setForeground(Color.WHITE);
        title.setBounds(40, 30, 400, 50);
        add(title);

        JTextArea text = new JTextArea();
        text.setFont(new Font("둥근모꼴", Font.PLAIN, 18));
        text.setForeground(Color.WHITE);
        text.setOpaque(false);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBounds(40, 100, 600, 600);

        text.setText(
            "● 목제 제작\n\n" +
            "  나무가지 5개\n\n" +
            "● 도끼(곡괭이, 낚시대) 제작\n\n" +
            "  목재 3개, 나무가지 5개\n\n" +
            "● 배 제작\n\n" +
            "  목재 15개, 나무가지 10개\n\n\n" +
            "● 집 설치\n\n" +
            "  목재 100개 + 돌 100개 + 나무가지 50개\n\n" +
            "● 모닥불 설치\n\n" +
            "  목재 30개\n\n" +
            "● 물통 설치\n\n" +
            "  목재 30개 + 돌 30개\n\n" +
            "● 강화대 설치\n\n" +
            "  목재 30개 + 돌 30개 + 철조각 3개\n"
        );

        add(text);
    }
}