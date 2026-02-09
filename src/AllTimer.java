import javax.swing.*;

class AllTimer {
	MainPanel p;
	Player player;
    Timer hungerTimer, thirstTimer, effectTimer, dayTimer;
    
    public AllTimer(MainPanel p, Player player) {
        this.p = p;					// 허기, 갈증, 날짜 불러오기
        this.player = player;		// 플레이어 속도 참조

        // 허기 감소
        hungerTimer = new Timer(4000, e -> {	// 4초에 한 번 1씩(정확히는 hungerRate만큼) 감소
            if (p.hunger > 0)
                p.hunger -= p.getHungerRate();
            else
                p.hunger = 0;
            p.repaint();
        });

        // 갈증 감소
        thirstTimer = new Timer(6500, e -> {	// 5초에 한 번 1씩 감소
            if (p.thirst > 0)
                p.thirst -= p.getThirstRate();
            else
                p.thirst = 0;
            p.repaint();
        });

        // 허기, 갈증 0일 때 디버프
        effectTimer = new Timer(5000, e -> effect());	// 5초에 한 번 함수호출

        // 하루 지남 (1분)
        dayTimer = new Timer(60000, e -> nextDay());	
    }

    public void startAll() {
        hungerTimer.start();
        thirstTimer.start();
        effectTimer.start();
        dayTimer.start();
    }

    public void stopAll() {
        hungerTimer.stop();
        thirstTimer.stop();
        effectTimer.stop();
        dayTimer.stop();
    }

    public void nextDay() {
        p.day++;
        if (p.day % 5 == 0)			// 5일에 한 번 씩 
            p.increaseRate();		// hungerRate(허기 감소율), thirstRate(갈증감소율) 1씩 증가시킴

        if (p.day > 30) {
            stopAll();
            JOptionPane.showMessageDialog(p, "축하드립니다, 당신은 30일간 무사히 생존하였습니다.");
        }
    }

    public void effect() {
        // 갈증이 0일 때 HP 감소, HP가 0이 되면 게임종료
        if (p.thirst <= 0) {
            p.thirst = 0;
            p.hp -= 5;
            if (p.hp <= 0) {
                p.hp = 0;
                stopAll();
                JOptionPane.showMessageDialog(p, "사망했습니다. 게임 오버.");
            }
        }
        else if (p.thirst >= 100) {
            if (p.hp < 100) {
                p.hp += 5;
                if (p.hp > 100) p.hp = 100;
            }
        }

        // 허기가 0일 때 속도 감소
        if (p.hunger <= 0) {
            p.hunger = 0;
            int s = player.getSpeed();
            
            if (s > 1)
                player.setSpeed(s - 1);	// 최소 1까지만 
        }
        else if (p.hunger > 0) {		// 허기가 0을 넘기기만 하면
            int s = player.getSpeed();
            
            if (s < 3) {	
                player.setSpeed(s + 1);		// 마찬가지로 5초에 한 번 씩 속도가 1씩 증가
            }
        }

        p.repaint();
    }
}







