package VIEW;

import javax.swing.*;
import javax.swing.border.LineBorder;
import CONTROLLER.MenuController;
import CONTROLLER.SoundPlayer;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JFrame {
    // Các nút lựa chọn chế độ chơi và nút Start
    private JRadioButton button_motNguoiChoi; // Nút chọn chế độ một người chơi
    private JRadioButton button_haiNguoiChoi; // Nút chọn chế độ hai người chơi
    private JRadioButton button_withObstacles; // Nút chọn chế độ có chướng ngại vật
    private JRadioButton button_noObstacles; // Nút chọn chế độ không có chướng ngại vật
    private JButton startButton; // Nút Start để bắt đầu trò chơi

    // Lớp JPanel tùy chỉnh để vẽ ảnh nền
    class BackgroundPanel extends JPanel {
        private Image backgroundImage; // Ảnh nền

        public BackgroundPanel(String imagePath) {
            try {
                // Đọc ảnh nền từ file
                backgroundImage = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace(); // Báo lỗi nếu không đọc được file
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Vẽ ảnh nền, tự động điều chỉnh kích thước theo JPanel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public MenuPanel() {
        // Cấu hình JFrame
        this.setTitle("Snake Game"); // Đặt tiêu đề cho cửa sổ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng ứng dụng khi tắt cửa sổ
        this.setSize(820, 640); // Kích thước cửa sổ
        this.setLocationRelativeTo(null); // Căn giữa cửa sổ

        // Phát âm thanh nền
        SoundPlayer.playSound("src/Sources/NN.wav");

        // Tạo Font chữ cho giao diện
        Font chu = new Font("Arial", Font.BOLD, 20);

        // Tạo panel đầu tiên để chứa các lựa chọn chế độ chơi
        JPanel FirstPanel = new JPanel();
        FirstPanel.setLayout(new BoxLayout(FirstPanel, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout để sắp xếp theo chiều dọc

        JLabel titleLabel = new JLabel("CHỌN CHẾ ĐỘ CHƠI:", SwingConstants.CENTER); // Nhãn tiêu đề
        titleLabel.setFont(new Font("Serif", Font.BOLD, 23)); // Thiết lập font cho tiêu đề
        titleLabel.setForeground(Color.BLACK); // Màu chữ
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa nhãn theo trục ngang

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20)); // Sắp xếp các nút theo dòng với khoảng cách

        // Nút chế độ không có chướng ngại vật
        button_noObstacles = new JRadioButton("Không có chướng ngại vật");
        button_noObstacles.setActionCommand("NO_OBSTACLES"); // Thiết lập action command
        button_noObstacles.setOpaque(false); // Loại bỏ nền mặc định
        button_noObstacles.setFont(chu); // Áp dụng Font
        button_noObstacles.setForeground(new Color(255, 223, 0)); // Màu chữ
        button_noObstacles.addActionListener(e -> { // Thêm sự kiện khi nút được nhấn
            SoundPlayer.playSound("src/Sources/Click.wav"); // Phát âm thanh click
            new MenuController(this).actionPerformed(e); // Gọi controller để xử lý
        });

        // Nút chế độ có chướng ngại vật
        button_withObstacles = new JRadioButton("Có chướng ngại vật");
        button_withObstacles.setActionCommand("WITH_OBSTACLES"); // Thiết lập action command
        button_withObstacles.setOpaque(false);
        button_withObstacles.setForeground(new Color(255, 223, 0));
        button_withObstacles.setFont(chu);
        button_withObstacles.addActionListener(e -> { // Sự kiện khi nút được nhấn
            SoundPlayer.playSound("src/Sources/Click.wav");
            new MenuController(this).actionPerformed(e);
        });

        // Nhóm các nút chế độ chơi lại với nhau
        ButtonGroup BG = new ButtonGroup();
        BG.add(button_noObstacles);
        BG.add(button_withObstacles);

        // Thêm các nút vào panel
        buttonPanel.add(button_noObstacles);
        buttonPanel.add(button_withObstacles);
        buttonPanel.setOpaque(false); // Loại bỏ nền mặc định

        // Thêm các thành phần vào FirstPanel
        FirstPanel.add(titleLabel);
        FirstPanel.add(Box.createVerticalStrut(20)); // Thêm khoảng cách dọc
        FirstPanel.add(buttonPanel);
        FirstPanel.setOpaque(false); // Loại bỏ nền mặc định

        // Tạo panel thứ hai để chọn số người chơi
        JPanel SecondPanel = new JPanel();
        SecondPanel.setLayout(new BoxLayout(SecondPanel, BoxLayout.Y_AXIS)); // Bố cục theo trục dọc

        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20)); // Sắp xếp các nút theo dòng

        JLabel titleLabel_Che_do_nguoi_choi = new JLabel("CHỌN SỐ NGƯỜI CHƠI:", SwingConstants.CENTER);
        titleLabel_Che_do_nguoi_choi.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel_Che_do_nguoi_choi.setForeground(Color.BLACK);
        titleLabel_Che_do_nguoi_choi.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nút chế độ một người chơi
        button_motNguoiChoi = new JRadioButton("Chế độ một người chơi");
        button_motNguoiChoi.addActionListener(e -> { // Sự kiện khi nút được nhấn
            SoundPlayer.playSound("src/Sources/Click.wav");
            new MenuController(this).actionPerformed(e);
        });
        button_motNguoiChoi.setFont(chu);
        button_motNguoiChoi.setForeground(new Color(255, 223, 0));
        button_motNguoiChoi.setOpaque(false);

        // Nút chế độ hai người chơi
        button_haiNguoiChoi = new JRadioButton("Chế độ hai người chơi");
        button_haiNguoiChoi.addActionListener(e -> { // Sự kiện khi nút được nhấn
            SoundPlayer.playSound("src/Sources/Click.wav");
            new MenuController(this).actionPerformed(e);
        });
        button_haiNguoiChoi.setFont(chu);
        button_haiNguoiChoi.setForeground(new Color(255, 223, 0));
        button_haiNguoiChoi.setOpaque(false);

        // Nhóm các nút chế độ người chơi lại với nhau
        ButtonGroup BG1 = new ButtonGroup();
        BG1.add(button_motNguoiChoi);
        BG1.add(button_haiNguoiChoi);

        // Thêm các nút vào panel
        buttonPanel1.add(button_motNguoiChoi);
        buttonPanel1.add(button_haiNguoiChoi);
        buttonPanel1.setOpaque(false);

        // Thêm các thành phần vào SecondPanel
        SecondPanel.add(titleLabel_Che_do_nguoi_choi);
        SecondPanel.add(Box.createVerticalStrut(20)); // Thêm khoảng cách dọc
        SecondPanel.add(buttonPanel1);
        SecondPanel.setOpaque(false);

        // Tạo panel nền với ảnh
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/Sources/nenbg.png");

        // Sử dụng BorderLayout cho JFrame
        this.setLayout(new BorderLayout());
        this.add(backgroundPanel, BorderLayout.CENTER); // Thêm backgroundPanel vào JFrame

        // Sử dụng BoxLayout cho backgroundPanel
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.add(Box.createVerticalStrut(150)); // Thêm khoảng cách dọc
        backgroundPanel.add(FirstPanel);                  // Thêm panel chọn chế độ chơi
        backgroundPanel.add(Box.createVerticalStrut(30)); // Thêm khoảng cách dọc
        backgroundPanel.add(SecondPanel);                 // Thêm panel chọn số người chơi


        // Tạo nút Start
        startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 20)); // Font chữ cho nút
        startButton.setForeground(Color.WHITE); // Màu chữ
        startButton.setBackground(Color.LIGHT_GRAY); // Màu nền nút
        startButton.setFocusPainted(false); // Loại bỏ viền khi nút được chọn
        startButton.setPreferredSize(new Dimension(200, 50)); // Kích thước nút

        // Xử lý sự kiện khi nhấn nút Start
        startButton.addActionListener(e -> {
            SoundPlayer.playSound("src/Sources/Click.wav");
            new MenuController(this).actionPerformed(e);
        });

        // Thêm nút Start vào backgroundPanel
        backgroundPanel.add(Box.createVerticalStrut(30)); // Thêm khoảng cách
        backgroundPanel.add(startButton);

        // Hiển thị JFrame
        this.setVisible(true);
    }

    // Getter cho các thành phần
    public JButton getStartButton() {
        return startButton;
    }

    public JRadioButton getButton_motNguoiChoi() {
        return button_motNguoiChoi;
    }

    public JRadioButton getButton_haiNguoiChoi() {
        return button_haiNguoiChoi;
    }

    public JRadioButton getButton_withObstacles() {
        return button_withObstacles;
    }

    public JRadioButton getButton_noObstacles() {
        return button_noObstacles;
    }
}
