package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import MODEL.TwoPlayerSnake;
import MODEL.TwoPlayerSnake_2;

public class TwoSnakeView_2 extends JFrame {
	 private TwoPlayerSnake_2 twoSnakeModel;
	    private MenuPanel mp;
	    public TwoSnakeView_2(MenuPanel mp) {
	        this.twoSnakeModel = new TwoPlayerSnake_2();
	        this.mp = mp;
	        this.twoSnakeModel.khoiTao2Ran();
	        this.init_TwoSnake();
	        this.setVisible(true);
	    }

	    public MenuPanel getMp() {
			return mp;
		}

		private void init_TwoSnake() {
	        this.setTitle("Two Player Snake Game"); // Cập nhật tiêu đề cho chế độ hai người chơi
	        this.setBackground(Color.DARK_GRAY);
	        this.setSize(820, 640);
	        this.setLocationRelativeTo(null);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Tạo và thêm TwoSnakePanel vào giao diện
	        TwoSnakePanel_2 twoSnakePanel = new TwoSnakePanel_2(this.twoSnakeModel,this);  // Tạo panel giao diện chính của trò chơi (TwoSnakePanel_2)
	        this.setLayout(new BorderLayout());// Đặt layout của JFrame thành BorderLayout
	        this.add(twoSnakePanel, BorderLayout.CENTER); // Thêm panel giao diện trò chơi vào vị trí trung tâm của cửa sổ
	    }
}
