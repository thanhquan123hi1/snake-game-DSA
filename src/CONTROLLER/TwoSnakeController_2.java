package CONTROLLER;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import MODEL.TwoPlayerSnake;
import MODEL.TwoPlayerSnake_2;
import VIEW.TwoSnakePanel;
import VIEW.TwoSnakePanel_2;

public class TwoSnakeController_2 implements KeyListener,ActionListener{
	 private TwoPlayerSnake_2 twoSnake; // Quản lý hai rắn
	    private TwoSnakePanel_2 tspn;          // Giao diện vẽ
	    private Timer timer;             // Bộ hẹn giờ để cập nhật trò chơi
	    private final int BASE_DELAY = 250; // Độ trễ ban đầu
		private Timer timer1;

	    public TwoSnakeController_2(TwoPlayerSnake_2 twoSnake, TwoSnakePanel_2 tspn) {
	        this.twoSnake = twoSnake;
	        this.tspn = tspn;

	        timer = new Timer(BASE_DELAY, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Di chuyển rắn người chơi 1 nếu còn sống
	                if (twoSnake.istrangThaiSnake1()) {
	                    twoSnake.diChuyenRan1();
	                    tspn.repaint();
	                }

	                // Di chuyển rắn người chơi 2 nếu còn sống
	                if (twoSnake.istrangThaiSnake2()) {
	                    twoSnake.diChuyenRan2();
	                    tspn.repaint();
	                }

	                twoSnake.tauDiChuyen();

	                // Cập nhật giao diện
	                //tspn.repaint();

	             // Kiểm tra nếu 1 trong 2 người chơi thua, dừng trò chơi
	                if (!twoSnake.istrangThaiSnake1() || !twoSnake.istrangThaiSnake2()) {
	                    timer.stop();
	                    SoundPlayer.playSound("src/Sources/GameOver.wav");

	                }
	            }
	        });
	        timer.start();
	         timer1 = new Timer(15000, new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	                SoundPlayer.playSound("src/Sources/tau_hoa.wav");
                if (!twoSnake.istrangThaiSnake1() || !twoSnake.istrangThaiSnake2()) {
                    timer1.stop();}
	        	}});
	        timer1.start();
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {}

	    @Override
	    public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();

	        // Điều khiển rắn người chơi 1 (WASD)
	        if (twoSnake.istrangThaiSnake1()) {
	            if (key == KeyEvent.VK_W) {
	                twoSnake.doiHuong1(new Point(0, -1)); // Đi lên
	            } else if (key == KeyEvent.VK_S) {
	                twoSnake.doiHuong1(new Point(0, 1)); // Đi xuống
	            } else if (key == KeyEvent.VK_A) {
	                twoSnake.doiHuong1(new Point(-1, 0)); // Đi trái
	            } else if (key == KeyEvent.VK_D) {
	                twoSnake.doiHuong1(new Point(1, 0)); // Đi phải
	            }
	            SoundPlayer.playSound("src/Sources/Press.wav");
	        }

	        // Điều khiển rắn người chơi 2 (Arrow keys)
	        if (twoSnake.istrangThaiSnake2()) {
	            if (key == KeyEvent.VK_UP) {
	                twoSnake.doiHuong2(new Point(0, -1)); // Đi lên
	            } else if (key == KeyEvent.VK_DOWN) {
	                twoSnake.doiHuong2(new Point(0, 1)); // Đi xuống
	            } else if (key == KeyEvent.VK_LEFT) {
	                twoSnake.doiHuong2(new Point(-1, 0)); // Đi trái
	            } else if (key == KeyEvent.VK_RIGHT) {
	                twoSnake.doiHuong2(new Point(1, 0)); // Đi phải
	            }
	            SoundPlayer.playSound("src/Sources/Press.wav");
	        }

	        // Nhấn "R" để reset trò chơi
	        if (key == KeyEvent.VK_R) {
	            twoSnake.khoiTao2Ran(); // Tạo lại rắn mới
	            timer.setDelay(BASE_DELAY); // Reset độ trễ
	            tspn.repaint();
	            if (!timer.isRunning()) {
	                timer.start();
	            }
	        }

	        // Nhấn "E" để thoát
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
	            timer1.stop();
				tspn.setVisible(false);
				tspn.getTw().setVisible(false);
				tspn.getTw().getMp().setVisible(true);
				
			}
			
		}
	}


