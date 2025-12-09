package VIEW;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import CONTROLLER.SnakeController;
import CONTROLLER.TwoSnakeController;
import MODEL.Snake;
import MODEL.TwoPlayerSnake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.awt.image.ImageObserver;

public class SnakePanel extends JPanel { // Tạo lớp SnakePanel kế thừa JPanel
	private static final int TILE_SIZE = 20; // Kích thước của một ô (đơn vị trên bàn chơi)
	private Snake snakeModel; // Biến lưu trữ đối tượng model Snake
	private Image foodImage; // Biến để lưu hình ảnh thức ăn 
	private Image foodSImage; // Biến để lưu ảnh thức ăn đặc biệt
	private Image sanImage; // Biến để lưu ảnh sàn đặc biệt
	private Image chuongNgaiVatImage; //Biến để lưu ảnh chướng ngại vật đặc biệt
	private SnakeView sp;
	
	public SnakeView getSp() {
		return sp;
	}

	public SnakePanel(Snake sk, SnakeView sp) { // Constructor nhận đối tượng Snake model
		this.snakeModel = sk; // Gán model được truyền vào
		this.sp = sp;

		int chieuRong = 800; // Chiều rộng của bàn chơi
		int chieuCao = 600; // Chiều dài của bàn chơi

		// Đặt kích thước mong muốn của JPanel
		this.setPreferredSize(new Dimension(chieuRong, chieuCao));

		// Đặt focus để nhận sự kiện bàn phím
		this.setFocusable(true);
        SnakeController t =new SnakeController(this.snakeModel, this);


		// Thêm bộ điều khiển bàn phím (KeyListener) từ SnakeController
		this.addKeyListener(t);
		    JButton nutQuayLai = new JButton("Quay lai");
			nutQuayLai.setFont(new Font("Arial", Font.BOLD, 10));
			nutQuayLai.setSize(25,25);
			nutQuayLai.setOpaque(false);
			nutQuayLai.addActionListener(t);
			
			this.add(nutQuayLai);
	}
	

	@Override
	protected void paintComponent(Graphics g) { // Ghi đè phương thức vẽ giao diện
		super.paintComponent(g); // Gọi phương thức vẽ mặc định của JPanel

        // Vẽ sàn (San.png)
	    this.sanImage = new ImageIcon(getClass().getResource("/Sources/San.png")).getImage();
        for (int i = 0; i < getWidth() / TILE_SIZE; i++) {
            for (int j = 0; j < getHeight() / TILE_SIZE; j++) {
                g.drawImage(sanImage, i * TILE_SIZE, j * TILE_SIZE, 24, 24, this);
            }
        }
		
		// Thêm icon vào thức ăn đỏ
		Point thucAn = snakeModel.getThucAn();
		// Tải hình ảnh icon
	    this.foodImage = new ImageIcon(getClass().getResource("/Sources/Apple.png")).getImage();
	    g.drawImage(foodImage, thucAn.x * TILE_SIZE, thucAn.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);

		// Vẽ thức ăn đặc biệt nếu xuất hiện
		if (snakeModel.thucAnSXuatHien()) {
			Point thucAnS = snakeModel.getThucAnS();
			// Tải hình ảnh icon
		    this.foodSImage = new ImageIcon(getClass().getResource("/Sources/AppleS.png")).getImage();
		    g.drawImage(foodSImage, thucAnS.x * TILE_SIZE, thucAnS.y * TILE_SIZE, 23, 23, this);
		}

		// Vẽ thân rắn
		g.setColor(Color.BLUE);
		for (Point than : snakeModel.getSnake()) {
			if (than != snakeModel.getSnake().getFirst()) { // Không vẽ đầu rắn ở đây
				g.fillRect(than.x * snakeModel.getTILE_SIZE(), than.y * snakeModel.getTILE_SIZE(),
						snakeModel.getTILE_SIZE(), snakeModel.getTILE_SIZE());
			}
		}

		// Vẽ đầu rắn màu vàng
		Point dauRan = snakeModel.getSnake().getFirst();
		g.setColor(Color.RED);
		g.fillRect(dauRan.x * snakeModel.getTILE_SIZE(), dauRan.y * snakeModel.getTILE_SIZE(),
				snakeModel.getTILE_SIZE(), snakeModel.getTILE_SIZE());

		// Vẽ mắt cho đầu rắn
		g.setColor(Color.BLACK);
		int eyeSize = TILE_SIZE / 5; // Kích thước mắt
		// Mắt trái
		g.fillOval(dauRan.x * TILE_SIZE + TILE_SIZE / 4, dauRan.y * TILE_SIZE + TILE_SIZE / 4, eyeSize, eyeSize);
		// Mắt phải
		g.fillOval(dauRan.x * TILE_SIZE + TILE_SIZE / 2, dauRan.y * TILE_SIZE + TILE_SIZE / 4, eyeSize, eyeSize);

		// Hiển thị điểm số trên màn hình
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD, 16)); // Thiết lập font chữ
		g.drawString("Score: " + snakeModel.getScore(), 10, 20); // Vẽ điểm số ở góc trái trên

		// Hiển thị điểm cao nhất
		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD, 16)); // Thiết lập font chữ
		g.drawString("Highest Score: " + snakeModel.getHighestScore(), 10, 40); // Vẽ điểm cao nhất

		// Vẽ chướng ngại vật
		this.chuongNgaiVatImage = new ImageIcon(getClass().getResource("/Sources/Grass.png")).getImage();
		for (Point vatCan : snakeModel.getChuongNgaiVat()) { // Duyệt qua danh sách chướng ngại vật
		    g.drawImage(chuongNgaiVatImage, vatCan.x * TILE_SIZE, vatCan.y * TILE_SIZE, 24, 24, this);
		}

		// Nếu game over, hiển thị thông báo "Game Over"
		if (!snakeModel.getTrangThaiGame()) { // Kiểm tra trạng thái trò chơi
			Font f = new Font("Arial", Font.BOLD, 60); // Thiết lập font chữ
			g.setFont(f); // Áp dụng font
			g.setColor(Color.RED); // Đặt màu chữ đỏ
			g.drawString("Game Over", 230, 320); // Vẽ chữ "Game Over" ở giữa màn hình
			Font f2 = new Font("Arial", Font.BOLD, 15); // Thiết lập font chữ
			g.setFont(f2); // Áp dụng font
			g.setColor(Color.GREEN); // Đặt màu chữ đỏ
			g.drawString("Điểm của bạn là " + snakeModel.getScore(), 320, 340); // Vẽ chữ "Game Over" ở giữa màn hình
		}
	}
}
