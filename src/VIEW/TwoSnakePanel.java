package VIEW;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.LinkedList;

import CONTROLLER.SnakeController;
import CONTROLLER.TwoSnakeController;
import MODEL.TwoPlayerSnake;

public class TwoSnakePanel extends JPanel { // Tạo lớp TwoSnakePanel kế thừa JPanel
    private static final int TILE_SIZE = 20; // Kích thước ô
    private TwoPlayerSnake twoSnakeModel;    // Model quản lý hai rắn
    private TwoSnakeView tw;
    private Image foodImage;
    private Image sanImage;

    public TwoSnakePanel(TwoPlayerSnake twoSnakeModel,TwoSnakeView tw) {
        this.twoSnakeModel = twoSnakeModel;
        this.tw = tw;
        int chieuRong = 800; // Chiều rộng bàn chơi
        int chieuDai = 600;  // Chiều dài bàn chơi

        this.setPreferredSize(new Dimension(chieuRong, chieuDai));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        TwoSnakeController t =new TwoSnakeController(this.twoSnakeModel, this);
        // Gán bộ điều khiển cho trò chơi hai người
        this.addKeyListener(t);
        
        JButton nutQuayLai = new JButton("Quay lai");
		nutQuayLai.setFont(new Font("Arial", Font.BOLD,10));
		//nutQuayLai.setLocation(new Point(0,29));
		nutQuayLai.setSize(25,25);
		nutQuayLai.setOpaque(false);
		nutQuayLai.addActionListener(t);
		this.add(nutQuayLai);
    }

    public TwoSnakeView getTw() {
		return tw;
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ sàn (San.png)
	    this.sanImage = new ImageIcon(getClass().getResource("/Sources/San2.png")).getImage();
        for (int i = 0; i < getWidth() / TILE_SIZE; i++) {
            for (int j = 0; j < getHeight() / TILE_SIZE; j++) {
                g.drawImage(sanImage, i * TILE_SIZE, j * TILE_SIZE, 24, 24, this);
            }
        }
		// Vẽ thức ăn chung (đỏ)
		Point thucAn = twoSnakeModel.getThucAn();
		// Tải hình ảnh icon
	    this.foodImage = new ImageIcon(getClass().getResource("/Sources/Apple.png")).getImage();
	    g.drawImage(foodImage, thucAn.x * TILE_SIZE, thucAn.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);

        // Vẽ thân rắn của người chơi 1 (Xanh lam)
        LinkedList<Point> snake1 = twoSnakeModel.getSnake1();
        g.setColor(Color.CYAN);
        for (Point than : snake1) {
            if (than != snake1.getFirst()) { // Không vẽ đầu
                g.fillRect(than.x * TILE_SIZE, than.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // Vẽ đầu rắn người chơi 1 (Vàng)
        Point dauRan1 = snake1.getFirst();
        g.setColor(Color.YELLOW);
        g.fillRect(dauRan1.x * TILE_SIZE, dauRan1.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Vẽ mắt cho đầu rắn người chơi 1
        g.setColor(Color.BLACK);
        int eyeSize = TILE_SIZE / 5;
        g.fillOval(dauRan1.x * TILE_SIZE + TILE_SIZE / 4, dauRan1.y * TILE_SIZE + TILE_SIZE / 4, eyeSize, eyeSize);
        g.fillOval(dauRan1.x * TILE_SIZE + TILE_SIZE / 2, dauRan1.y * TILE_SIZE + TILE_SIZE / 4, eyeSize, eyeSize);

        // Vẽ thân rắn của người chơi 2 (Xanh lá)
        LinkedList<Point> snake2 = twoSnakeModel.getSnake2();
        g.setColor(Color.GREEN);
        for (Point than : snake2) {
            if (than != snake2.getFirst()) {
                g.fillRect(than.x * TILE_SIZE, than.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // Vẽ đầu rắn người chơi 2 (Đỏ cam)
        Point dauRan2 = snake2.getFirst();
        g.setColor(Color.ORANGE);
        g.fillRect(dauRan2.x * TILE_SIZE, dauRan2.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Vẽ mắt cho đầu rắn người chơi 2
        g.setColor(Color.BLACK);
        g.fillOval(dauRan2.x * TILE_SIZE + TILE_SIZE / 4, dauRan2.y * TILE_SIZE + TILE_SIZE / 4, eyeSize, eyeSize);
        g.fillOval(dauRan2.x * TILE_SIZE + TILE_SIZE / 2, dauRan2.y * TILE_SIZE + TILE_SIZE / 4, eyeSize, eyeSize);


        // Hiển thị điểm số của người chơi 1
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Player 1 Score: " + twoSnakeModel.getScore1(), 10, 20);

        // Hiển thị điểm số của người chơi 2
        g.drawString("Player 2 Score: " + twoSnakeModel.getScore2(), 10, 40);

        // Hiển thị trạng thái Game kết thúc nếu 1 trong 2 người thua 
		if (!twoSnakeModel.istrangThaiSnake1() || !twoSnakeModel.istrangThaiSnake2()) {
			if (!twoSnakeModel.istrangThaiSnake1() && !twoSnakeModel.istrangThaiSnake2()) {
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.BOLD, 60));
				g.drawString("HÒA", 280, 300);
			} else if (twoSnakeModel.istrangThaiSnake1()) {
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.BOLD, 60));
				g.drawString("PLAYER 1 THẮNG", 150, 300);
			} else if (twoSnakeModel.istrangThaiSnake2()) {
				g.setColor(Color.RED);
				g.setFont(new Font("Arial", Font.BOLD, 60));
				g.drawString("PLAYER 2 THẮNG", 150, 300);
			} 
        } 
    }
}
