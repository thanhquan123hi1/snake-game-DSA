package MODEL;

import CONTROLLER.SoundPlayer;

import javax.swing.*;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

public class Snake {
    private LinkedList<Point> snake;    // Con rắn
    private Point thucAn;               // Tọa độ thức ăn
    private Point thucAnS; // Vị trí thức ăn đặc biêt
    private Timer timerThucAnS; // Quản lý thời gian hiển thị thức ăn đặc biêt
    private boolean thucAnSXuatHien; // Kiểm tra thức ăn đặc biệt có đang xuất hiện không
    private final int TILE_SIZE = 20;   // Kích thước 1 ô
    private final int GRID_WIDTH = 40;  // Số ô ngang
    private final int GRID_HEIGHT = 30; // Số ô dọc
    private boolean trangThaiGame ;     // Trạng thái game
    private Point huongDi;              // Hướng di chuyển của rắn
    private int score = 0;
    private int highestScore = 0;       // Điểm cao nhất
    private  int demThucAn = 0;
    private LinkedList<Point> chuongNgaiVat;
    private boolean withObstacles;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}

	public int getdemThucAn() {
        return demThucAn;
    }

    public Point getHuongDi() {
        return huongDi;
    }

    public int getTILE_SIZE() {
        return TILE_SIZE;
    }

    public int getGRID_WIDTH() {
        return GRID_WIDTH;
    }

    public int getGRID_HEIGHT() {
        return GRID_HEIGHT;
    }
    public Point getThucAnS() {
        return thucAnS;
    }
    public boolean thucAnSXuatHien() {
        return thucAnSXuatHien;
    }

    public LinkedList<Point> getChuongNgaiVat() {
        return chuongNgaiVat;
    }

    public Snake(boolean withObstacles) {
        this.withObstacles = withObstacles;

        this.snake = new LinkedList<>();
        snake.add(new Point(5, 5)); // Thêm đầu rắn vào danh sách
        snake.add(new Point(4, 5)); // Thêm thân rắn
        snake.add(new Point(3, 5)); // Thêm thân rắn
        huongDi = new Point(1, 0); // Ban đầu đi sang phải
        this.thucAnS = null; // Ban đầu chưa có thức ăn đặc biệt
        this.thucAnSXuatHien = false; // Thức ăn đặc biệt chưa xuất hiện
        thucAn = khoiTaoTA(); // Khởi tạo thức ăn
        trangThaiGame = true;
        chuongNgaiVat = withObstacles ? khoiTaoChuongNgaiVat() : new LinkedList<>();
    }

    public void KhoiDongGame() {
        this.snake = new LinkedList<>();
        snake.add(new Point(5, 5)); // Thêm đầu rắn vào danh sách
        snake.add(new Point(4, 5)); // Thêm thân rắn
        snake.add(new Point(3, 5)); // Thêm thân rắn
        huongDi = new Point(1, 0); // Ban đầu đi sang phải
        score = 0;
        demThucAn = 0;
        trangThaiGame = true;
        if(withObstacles)chuongNgaiVat = khoiTaoChuongNgaiVat(); // Nếu withObstacles = true thì mới tạo chướng ngại vật
        thucAn = khoiTaoTA(); // Khởi tạo thức ăn
    }
    private LinkedList<Point> khoiTaoChuongNgaiVat() {
        LinkedList<Point> vatCan = new LinkedList<>();
        Random r = new Random();

        for (int i = 0; i < 20; i++) { // Tạo 20 chướng ngại vật
            Point newVatCan;
            do {
                int x = r.nextInt(GRID_WIDTH);
                int y = r.nextInt(GRID_HEIGHT);
                newVatCan = new Point(x, y);
            } while (snake.contains(newVatCan) || newVatCan.equals(thucAn));
            vatCan.add(newVatCan);
        }
        return vatCan;
    }

    public boolean getTrangThaiGame() {
        return trangThaiGame;
    }

    public LinkedList<Point> getSnake() {
        return snake;
    }

    public Point getThucAn() {
        return thucAn; // Chỉ trả về vị trí hiện tại của thức ăn
    }

    private Point khoiTaoThucAnS() {
        Random r = new Random();
        Point newFood;

        do {
            int x = r.nextInt(GRID_WIDTH);
            int y = r.nextInt(GRID_HEIGHT);
            newFood = new Point(x, y);
        } while (snake.contains(newFood) || (chuongNgaiVat != null && chuongNgaiVat.contains(newFood)) || newFood.equals(thucAn));

        return newFood; // Trả về vị trí thức ăn đặc việt
    }


    // Khởi tạo thức ăn ở vị trí ngẫu nhiên, không trùng với thân rắn
    public Point khoiTaoTA() {
        demThucAn++; // Đếm số lượng thức ăn đỏ đã ăn
        Random r = new Random();
        Point newFood;

        do {
            int x = r.nextInt(GRID_WIDTH);
            int y = r.nextInt(GRID_HEIGHT);
            newFood = new Point(x, y);
        } while (snake.contains(newFood)  || (chuongNgaiVat != null && chuongNgaiVat.contains(newFood))); // Đảm bảo thức ăn không trùng thân rắn và chướng ngại vật

        // Kiểm tra nếu đã ăn đủ 5 quả thức ăn đỏ, tạo thức ăn đặc biệt
        if (demThucAn % 5 == 0) { // Khi đủ 5 quả đỏ, tạo thức ăn xanh
            thucAnS = khoiTaoThucAnS();
            thucAnSXuatHien = true;

            // Nếu đã có timer cũ, hủy nó trước khi tạo mới
            if (timerThucAnS != null) {
                timerThucAnS.stop();
            }

            // Tạo và bắt đầu timer 5 giây
            timerThucAnS = new Timer(5000, e -> {
                thucAnS = null; // Ẩn thức ăn xanh
                thucAnSXuatHien = false;
                timerThucAnS.stop(); // Dừng timer
            });
            timerThucAnS.setRepeats(false); // Chỉ chạy một lần
            timerThucAnS.start();
        }
        return newFood;
    }

    // Di chuyển rắn
    public void ranDiChuyen() {
        Point dauRanHienTai = snake.getFirst();
        // Đầu rắn mới = đầu hiện tại + hướng di chuyển (tại rắn luôn di chuyển nên mới có công thức này)
        Point dauRanMoi = new Point(dauRanHienTai.x + huongDi.x, dauRanHienTai.y + huongDi.y);

        // Kiểm tra va chạm nếu có va chạm thì rắn liệm
        if (vaCham(dauRanMoi)) {
            SoundPlayer.playSound("src/Sources/GameOver.wav"); // Phát âm thanh va chạm
            trangThaiGame = false; // Kết thúc trò chơi
            return;
        }

        // Thêm đầu mới vào danh sách
        snake.addFirst(dauRanMoi);

        // Kiểm tra nếu ăn thức ăn
        //nếu nó gặp thức ăn mà nó ăn thì dữ nguyên chiều dài
        // còn mà gặp mà nó ko ăn hoặc đi nơi khác mà không ăn tức là nó không đói thì chiều dài sẽ bỏ đi để giữ nguyên phần tử
        if (!anThucAn()) {
            snake.removeLast(); // Nếu không ăn, xóa đuôi
        }
    }
    // Kiểm tra rắn ăn thức ăn không
    private boolean anThucAn() {
        Point dauRan = snake.getFirst();

        // Kiểm tra nếu rắn ăn thức ăn đỏ
        if (dauRan.equals(thucAn)) {
            score++; // Tăng 1 điểm
            if (highestScore < score) highestScore = score;
            thucAn = khoiTaoTA(); // Tạo thức ăn đỏ mới
            SoundPlayer.playSound("src/Sources/Eat.wav");
            return true;
        }

        // Kiểm tra nếu rắn ăn thức ăn đặc biệt
        if (thucAnSXuatHien && dauRan.equals(thucAnS)) {
            score += 5; // Tăng 5 điểm
            if (highestScore < score) highestScore = score;
            thucAnS = null; // Ẩn thức ăn đặc biệt
            thucAnSXuatHien = false; // Ngừng hiển thị thức ăn đặc biệt
            SoundPlayer.playSound("src/Sources/Eat.wav");
            return true;
        }

        return false;
    }

    // Kiểm tra va chạm
    private boolean vaCham(Point dauRanMoi) {
    	
        // Va chạm với tường
        if (dauRanMoi.x < 0 || dauRanMoi.y < 0 || dauRanMoi.x >= GRID_WIDTH || dauRanMoi.y >= GRID_HEIGHT) {
            return true;
        }
        // Va chạm với thân rắn
        else if (chuongNgaiVat.contains(dauRanMoi)) return true;
        return snake.contains(dauRanMoi);
    }

    // Đổi hướng di chuyển (kiểm tra để tránh quay ngược lại)
    public void doiHuong(Point huongMoi) {
        if (huongDi.x + huongMoi.x != 0 || huongDi.y + huongMoi.y != 0) {
            this.huongDi = huongMoi ;
        }
    }
}
