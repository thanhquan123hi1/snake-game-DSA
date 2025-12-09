package CONTROLLER;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import MODEL.Snake;
import VIEW.SnakePanel;

public class SnakeController implements KeyListener,ActionListener {

    private Snake sk;
    private SnakePanel spn;
    private Timer timer;
    private int level; // Cấp độ
    private final int BASE_DELAY = 200; // Độ trễ ban đầu

    public SnakeController(Snake sk, SnakePanel spn) {
        this.sk = sk;
        this.spn = spn;
        this.level = 1;

        timer = new Timer(BASE_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sk.getTrangThaiGame()) {
                    sk.ranDiChuyen();
                    spn.repaint();
                    
                    // Cập nhật cấp độ
                    int currentScore = sk.getdemThucAn();
                    int newLevel = 1 + currentScore / 5;
                    if (newLevel > level) {
                        level = newLevel;
                        updateSpeed();
                    }
                } else {
                    timer.stop();
                }
            }
        });

        timer.start();
    }
    // Cập nhật speed
    private void updateSpeed() {
        int newDelay = BASE_DELAY - (level - 1) * 50;
        newDelay = Math.max(newDelay, 20);
        timer.setDelay(newDelay);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (sk.getTrangThaiGame()) {
            if (key == KeyEvent.VK_UP) {
                sk.doiHuong(new Point(0, -1));
                SoundPlayer.playSound("src/Sources/Press.wav");
            } else if (key == KeyEvent.VK_DOWN) {
                sk.doiHuong(new Point(0, 1));
                SoundPlayer.playSound("src/Sources/Press.wav");
            } else if (key == KeyEvent.VK_LEFT) {
                sk.doiHuong(new Point(-1, 0));
                SoundPlayer.playSound("src/Sources/Press.wav");
            } else if (key == KeyEvent.VK_RIGHT) {
                sk.doiHuong(new Point(1, 0));
                SoundPlayer.playSound("src/Sources/Press.wav");
            }
        }
        // Nhấn để reset
        if (key == KeyEvent.VK_R) {
            sk.KhoiDongGame();
            level = 1;
            timer.setDelay(BASE_DELAY);
            updateSpeed();
            spn.repaint();
            if (!timer.isRunning()) {
                timer.start();
            }
        }

        if (key == KeyEvent.VK_E) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
	@Override
	public void actionPerformed(ActionEvent e) {
String src = e.getActionCommand();
		
		if(src.equals("Quay lai")) {
            timer.stop();
			spn.setVisible(false);
			spn.getSp().setVisible(false);
			spn.getSp().getMp().setVisible(true);
			
		}
	}
}
