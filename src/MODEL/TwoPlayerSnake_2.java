package MODEL;
import javax.swing.*;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;
import CONTROLLER.SoundPlayer;

public class TwoPlayerSnake_2 {
	
	    private LinkedList<Point> snake1;  // Rắn của người chơi 1
	    private LinkedList<Point> snake2;  // Rắn của người chơi 2
	    private Point thucAn;                     // Tọa độ thức ăn
	    private final int TILE_SIZE = 20;         // Kích thước ô
	    private final int GRID_WIDTH = 40;        // Số ô ngang
	    private final int GRID_HEIGHT = 30;       // Số ô dọc
	    private Point huongDi1;                   // Hướng di chuyển của người chơi 1
	    private Point huongDi2;                   // Hướng di chuyển của người chơi 2
	    private int score1 = 0;           // Điểm của người chơi 1
	    private int score2 = 0;           // Điểm của người chơi 2
	    private boolean trangThaiSnake1;      // Trạng thái sống/chết người chơi 1
	    private boolean trangThaiSnake2;      // Trạng thái sống/chết người chơi 2
	    private LinkedList<Point> duongRay;
	     private Point xeLua ;
	     private int speed ;
	    
	    public Point getXeLua() {
			return xeLua;
		}

		public LinkedList<Point> getSnake1() {
	        return snake1;
	    }

	    public LinkedList<Point> getSnake2() {
	        return snake2;
	    }

	    public Point getThucAn() {
	        return thucAn;
	    }

	    public int getScore1() {
	        return score1;
	    }

	    public int getScore2() {
	        return score2;
	    }

	    public boolean istrangThaiSnake1() {
	        return trangThaiSnake1;
	    }

	    public boolean istrangThaiSnake2() {
	        return trangThaiSnake2;
	    }
	    
	    // Khởi tạo chế độ 2 người chơi
	    public void khoiTao2Ran() {
	        // Khởi tạo rắn cho từng người chơi
	        this.snake1 = new LinkedList<>();
	        this.snake2 = new LinkedList<>();

	        // Vị trí ban đầu của rắn
	        snake1.add(new Point(5, 5));
	        snake1.add(new Point(4, 5));
	        snake1.add(new Point(3, 5));

	        snake2.add(new Point(33, 25));
	        snake2.add(new Point(34, 25));
	        snake2.add(new Point(35, 25));
	        
	        score1 = 0;
	        score2 = 0;

	        huongDi1 = new Point(1, 0); // Người chơi 1 đi sang phải
	        huongDi2 = new Point(-1, 0); // Người chơi 2 đi sang trái

	        thucAn = khoiTaoThucAn();
	        trangThaiSnake1 = true;
	        trangThaiSnake2 = true;
	        
	       this.xeLua = new Point(6,5);
	       this.speed = 200;
	    }
	    // Khởi tạo thức ăn
	    private Point khoiTaoThucAn() {
	        Random r = new Random();
	        Point newFood;

	        do {
	            int x = r.nextInt(GRID_WIDTH);
	            int y = r.nextInt(GRID_HEIGHT);
	            newFood = new Point(x, y);
	        } while (snake1.contains(newFood) || snake2.contains(newFood));

	        return newFood;
	    }
public void diChuyenRan1() {
	        diChuyenRan(snake1, huongDi1);
	    }

	    public void diChuyenRan2() {
	        diChuyenRan(snake2, huongDi2);
	    }

	    private void diChuyenRan(LinkedList<Point> ran, Point huongDi) {
	        Point dauMoi = new Point(ran.getFirst().x + huongDi.x, ran.getFirst().y + huongDi.y);

	        // Kiểm tra va chạm
	        if (vaCham(dauMoi, ran)) {
	            if (ran == snake1) {
	                trangThaiSnake1 = false;
	            } else {
	                trangThaiSnake2 = false;
	            }
	            return;
	        }
	        if(kiemTraVaChamTau(ran)) {
	        	if (ran == snake1) {
	                trangThaiSnake1 = false;
	            } else {
	                trangThaiSnake2 = false;
	            }
	            return;
	        }
	        
	        // Thêm đầu mới vào
	        ran.addFirst(dauMoi);

	        // Kiểm tra ăn thức ăn
	        if (dauMoi.equals(thucAn)) {
	            if (ran == snake1) {
	                score1++;
	                SoundPlayer.playSound("src/Sources/Eat.wav");

	            } else {
	                score2++;
	                SoundPlayer.playSound("src/Sources/Eat.wav");

	            }
	            thucAn = khoiTaoThucAn();
	        } else {
	            ran.removeLast();
	        }
	    }

	    private boolean vaCham(Point dauMoi, LinkedList<Point> ran) {
	        // Va chạm tường
	        if (dauMoi.x < 0 || dauMoi.y < 0 || dauMoi.x >= GRID_WIDTH || dauMoi.y >= GRID_HEIGHT) {
	            return true;
	        }

	        // Va chạm thân rắn
	        if (ran.contains(dauMoi)) {
	            return true;
	        }

	        // Va chạm giữa hai rắn
	        if (ran == snake1 && snake2.contains(dauMoi)) {
	            return true;
	        }
	        if (ran == snake2 && snake1.contains(dauMoi)) {
	            return true;
	        }

	        return false;
	    }

	    public void doiHuong1(Point huongMoi) {
	        if (huongDi1.x + huongMoi.x != 0 || huongDi1.y + huongMoi.y != 0) {
	            huongDi1 = huongMoi;
	        }
	    }

	    public void doiHuong2(Point huongMoi) {
	        if (huongDi2.x + huongMoi.x != 0 || huongDi2.y + huongMoi.y != 0) {
	            huongDi2 = huongMoi;
	        }
	    }
	    
	    public void taoDuongRay() {
	    	this.duongRay = new LinkedList<>();
	    	
	    	   for (int i = 6; i < 40; i++) { // Đường ray ngang trên
	               duongRay.add(new Point(i, 5));
	           }
	          for (int i = 5; i < 30; i++) { // Đường ray dọc bên phải
	               duongRay.add(new Point(6, i));
	         }
	          for (int i = 6; i < 40; i++) { // Đường ray ngang dưới
	               duongRay.add(new Point(i, 29));
	               }
	           for (int i = 5; i < 30; i++) { // Đường ray dọc bên trái
	               duongRay.add(new Point(39, i));
	           }
	    }
	    
	    public int getTILE_SIZE() {
			return TILE_SIZE;
		}

		public void tauDiChuyen() {
if(xeLua.x == 6 && xeLua.y < 29) {
				xeLua= new Point(6,xeLua.y + 1);
			}
			
			else if(xeLua.x < 39   && xeLua.y == 29) {
				xeLua= new Point(xeLua.x+1,29);
			}
			
			else if(xeLua.x == 39   && xeLua.y > 5) {
				xeLua= new Point(39,xeLua.y-1);
			}
			else if(xeLua.x > 6 && xeLua.y ==5) {
				xeLua= new Point(xeLua.x-1,5);

			}
			  
	    }
	   
	    


		public LinkedList<Point> getDuongRay() {
			return duongRay;
		}
		
		public boolean kiemTraVaChamTau( LinkedList<Point> ran) {
			if(ran.contains(xeLua)) {
			return true;
		}
			return false;
		}
	}