package VIEW;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import MODEL.TwoPlayerSnake;
import VIEW.TwoSnakePanel;

public class TwoSnakeView extends JFrame {
    private TwoPlayerSnake twoSnakeModel;
    private MenuPanel mp;
    public TwoSnakeView(MenuPanel mp) {
        this.twoSnakeModel = new TwoPlayerSnake();
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
        TwoSnakePanel twoSnakePanel = new TwoSnakePanel(this.twoSnakeModel,this);
        this.setLayout(new BorderLayout());
        this.add(twoSnakePanel, BorderLayout.CENTER);
    }
}
