package VIEW;

import javax.swing.JFrame;
import  javax.swing.JPanel;
import MODEL.Snake;
import VIEW.SnakePanel;
import CONTROLLER.SnakeController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class SnakeView extends JFrame {
	private Snake snakemodel;
	private MenuPanel mp ;

	public MenuPanel getMp() {
		return mp;
	}

	public SnakeView(boolean withObstacles, MenuPanel mp) {
		this.snakemodel = new Snake(withObstacles);
		this.mp = mp;
		this.init_Snake();
		this.setVisible(true);
	}

	private void init_Snake() {
		this.setTitle("SnakeGame");
		this.setBackground(Color.DARK_GRAY);
		this.setSize(820, 640);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // Tạo SnakePanel, chứa giao diện bàn chơi và các thành phần trò chơi.
        SnakePanel sk = new SnakePanel(this.snakemodel, this);
        this.setLayout(new BorderLayout()); // Sử dụng BorderLayout để bố trí giao diện.
        this.add(sk, BorderLayout.CENTER); // Thêm SnakePanel vào khu vực trung tâm của BorderLayout.
	}
}

