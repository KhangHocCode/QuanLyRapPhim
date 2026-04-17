package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import entity.LoadData;

public class Start extends JFrame implements ActionListener {
    private JLabel lblNhanVien;
    private JButton btnTrangChu, btnBanVe, btnPhim, btnSuatChieu,
            btnKhachHang, btnNhanVien, btnThongKe, btnDangXuat;
    private CardLayout cardLayout;
    private JButton btnHoaDon, btnVe;
    private JButton selectedButton; 
    private JButton[] navButtons;

    public Start() {
        ModernUI.installGlobalTheme();
        setTitle("Hệ thống quản lý bán vé rạp chiếu phim");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // ==== PANEL MENU BÊN TRÁI ====
        JPanel pnWest = new JPanel(new BorderLayout());
        pnWest.setPreferredSize(new Dimension(220, 1000));
        pnWest.setBackground(new Color(255, 56, 56));
        pnWest.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        Box boxMenu = Box.createVerticalBox();
        pnWest.add(boxMenu);
        boxMenu.add(Box.createVerticalStrut(25));

        // Logo
        ImageIcon logoIcon = new ImageIcon("icon/logo.jpg");
        Image logoImg = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(logoImg));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxMenu.add(lblLogo);
        boxMenu.add(Box.createVerticalStrut(15));

        String tenNV = (DangNhap.nhanVienDangNhap != null) ? DangNhap.nhanVienDangNhap.getTenNV() : "Khách";
        lblNhanVien = new JLabel("Xin chào, " + tenNV);
        lblNhanVien.setFont(new Font("Arial", Font.BOLD, 18));
        lblNhanVien.setForeground(Color.WHITE);
        lblNhanVien.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxMenu.add(lblNhanVien);
        boxMenu.add(Box.createVerticalStrut(15));
        boxMenu.add(createSeparatorLine(3));

        Font fontButton = new Font("Arial", Font.BOLD, 18);
        Color bgMenu = pnWest.getBackground();
        Color hoverColor = new Color(41, 128, 185); 
        Color selectedColor = new Color(0, 102, 153); 

        btnTrangChu = new JButton("Trang chủ");
        btnBanVe = new JButton("Bán vé");
        btnPhim = new JButton("Phim");
        btnSuatChieu = new JButton("Suất chiếu");
        btnKhachHang = new JButton("Khách hàng");
        btnNhanVien = new JButton("Nhân viên");
        btnThongKe = new JButton("Thống kê");
        btnHoaDon = new JButton("Hóa đơn");
        btnVe = new JButton("Vé phim");
        btnDangXuat = new JButton("Đăng xuất");

        JButton[] menuButtons = {
                btnTrangChu, btnBanVe, btnPhim, btnSuatChieu, btnKhachHang, btnNhanVien, btnThongKe, btnHoaDon, btnVe
        };
        this.navButtons = menuButtons;

        for (JButton btn : menuButtons) {
            btn.setBackground(bgMenu);
            btn.setForeground(Color.WHITE);
            btn.setFont(fontButton);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setIconTextGap(5);
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(hoverColor);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if (btn != selectedButton) {
                        btn.setBackground(bgMenu);
                    } else {
                        btn.setBackground(selectedColor);
                    }
                }
            });
        }
        btnDangXuat.setBackground(Color.WHITE);
        btnDangXuat.setForeground(Color.BLACK);
        btnDangXuat.setFont(fontButton);
        btnDangXuat.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDangXuat.setBorderPainted(false);
        btnDangXuat.setFocusPainted(false);

        boxMenu.add(Box.createVerticalStrut(10));
        for (JButton btn : menuButtons) {
            boxMenu.add(btn);
            boxMenu.add(Box.createVerticalStrut(10));
        }

        boxMenu.add(createSeparatorLine(3));
        boxMenu.add(Box.createVerticalStrut(8));
        boxMenu.add(btnDangXuat);

        add(pnWest, BorderLayout.WEST);

        JPanel pnCenter = new JPanel(new CardLayout());
        add(pnCenter, BorderLayout.CENTER);

        JPanel trangChuPanel = new TrangChu();
        JPanel banVePanel = new QuanLyBanVe();
        JPanel phimPanel = new QuanLyPhim();
        JPanel suatChieuPanel = new QuanLySuatChieu();
        JPanel khachHangPanel = new QuanLyKhachHang();
        JPanel nhanVienPanel = new QuanLyNhanVien();
        JPanel thongKePanel = new QuanLyThongKe();
        JPanel hoaDonPanel = new QuanLyHoaDon();
        JPanel vePanel = new QuanLyVe();

        pnCenter.add(trangChuPanel, "TrangChu");
        pnCenter.add(banVePanel, "BanVe");
        pnCenter.add(phimPanel, "Phim");
        pnCenter.add(suatChieuPanel, "SuatChieu");
        pnCenter.add(khachHangPanel, "KhachHang");
        pnCenter.add(nhanVienPanel, "NhanVien");
        pnCenter.add(thongKePanel, "ThongKe");
        pnCenter.add(hoaDonPanel, "HoaDon");
        pnCenter.add(vePanel, "Ve");

        this.cardLayout = (CardLayout) pnCenter.getLayout();
        
        btnTrangChu.addActionListener(e -> {
            showPanel(pnCenter, "TrangChu", null);
            selectButton(btnTrangChu, menuButtons, bgMenu, selectedColor);
        });
        btnBanVe.addActionListener(e -> {
            showPanel(pnCenter, "BanVe", (LoadData) banVePanel);
            selectButton(btnBanVe, menuButtons, bgMenu, selectedColor);
        });
        btnPhim.addActionListener(e -> {
            showPanel(pnCenter, "Phim", (LoadData) phimPanel);
            selectButton(btnPhim, menuButtons, bgMenu, selectedColor);
        });
        btnSuatChieu.addActionListener(e -> {
            showPanel(pnCenter, "SuatChieu", (LoadData) suatChieuPanel);
            selectButton(btnSuatChieu, menuButtons, bgMenu, selectedColor);
        });
        btnKhachHang.addActionListener(e -> {
            showPanel(pnCenter, "KhachHang", (LoadData) khachHangPanel);
            selectButton(btnKhachHang, menuButtons, bgMenu, selectedColor);
        });
        btnNhanVien.addActionListener(e -> {
            showPanel(pnCenter, "NhanVien", (LoadData) nhanVienPanel);
            selectButton(btnNhanVien, menuButtons, bgMenu, selectedColor);
        });
        btnThongKe.addActionListener(e -> {
            showPanel(pnCenter, "ThongKe", (LoadData) thongKePanel);
            selectButton(btnThongKe, menuButtons, bgMenu, selectedColor);
        });
        btnHoaDon.addActionListener(e -> {
            showPanel(pnCenter, "HoaDon", (LoadData) hoaDonPanel);
            selectButton(btnHoaDon, menuButtons, bgMenu, selectedColor);
        });
        btnVe.addActionListener(e -> {
            showPanel(pnCenter, "Ve", (LoadData) vePanel);
            selectButton(btnVe, menuButtons, bgMenu, selectedColor);
        });

        btnDangXuat.addActionListener(this);
        setupMnemonics();
        setupKeyboardShortcuts();

        selectButton(btnTrangChu, menuButtons, bgMenu, selectedColor);
        ModernUI.applySoftStyle(this);
        TooltipSupport.apply(this);
    }

        private void setupMnemonics() {
        btnTrangChu.setMnemonic(KeyEvent.VK_T);
        btnBanVe.setMnemonic(KeyEvent.VK_B);
        btnPhim.setMnemonic(KeyEvent.VK_P);
        btnSuatChieu.setMnemonic(KeyEvent.VK_S);
        btnKhachHang.setMnemonic(KeyEvent.VK_K);
        btnNhanVien.setMnemonic(KeyEvent.VK_N);
        btnThongKe.setMnemonic(KeyEvent.VK_G);
        btnHoaDon.setMnemonic(KeyEvent.VK_H);
        btnVe.setMnemonic(KeyEvent.VK_V);
        btnDangXuat.setMnemonic(KeyEvent.VK_X);
        }

        private void setupKeyboardShortcuts() {
        JRootPane root = getRootPane();

        // Chuyển nhanh theo tab chức năng
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK), "goTrangChu",
            () -> btnTrangChu.doClick());
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK), "goBanVe",
            () -> btnBanVe.doClick());
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK), "goPhim",
            () -> btnPhim.doClick());
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK), "goSuatChieu",
            () -> btnSuatChieu.doClick());
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.CTRL_DOWN_MASK), "goKhachHang",
            () -> btnKhachHang.doClick());
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.CTRL_DOWN_MASK), "goNhanVien",
            () -> btnNhanVien.doClick());
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_7, InputEvent.CTRL_DOWN_MASK), "goThongKe",
            () -> btnThongKe.doClick());
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.CTRL_DOWN_MASK), "goHoaDon",
            () -> btnHoaDon.doClick());
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_9, InputEvent.CTRL_DOWN_MASK), "goVe",
            () -> btnVe.doClick());

        // Điều hướng tab kế tiếp / trước đó
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.CTRL_DOWN_MASK), "nextTab",
            () -> navigateTab(1));
        bindShortcut(root,
            KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK),
            "prevTab", () -> navigateTab(-1));

        // Hành động nhanh
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK), "homeQuick",
            () -> btnTrangChu.doClick());
        bindShortcut(root, KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK), "logoutQuick",
            () -> btnDangXuat.doClick());
        }

        private void navigateTab(int delta) {
        if (navButtons == null || navButtons.length == 0) {
            return;
        }
        int current = 0;
        for (int i = 0; i < navButtons.length; i++) {
            if (navButtons[i] == selectedButton) {
            current = i;
            break;
            }
        }
        int next = (current + delta + navButtons.length) % navButtons.length;
        navButtons[next].doClick();
        }

        private void bindShortcut(JRootPane root, KeyStroke keyStroke, String actionKey, Runnable action) {
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionKey);
        root.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            action.run();
            }
        });
        }

    private void showPanel(JPanel pnCenter, String name, LoadData loadData) {
        this.cardLayout.show(pnCenter, name);
        if (loadData != null)
            loadData.loadData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDangXuat) {
            new DangNhap().setVisible(true);
            dispose();
        }
    }

    private void selectButton(JButton selected, JButton[] navButtons, Color bgMenu, Color selectedColor) {
        this.selectedButton = selected;

        for (JButton b : navButtons) {
            if (b == selected) {
                b.setBackground(selectedColor);
                b.setForeground(Color.WHITE); 
            } else {
                b.setBackground(bgMenu);
                b.setForeground(Color.WHITE);
            }
        }
    }

    public static ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public JPanel createSeparatorLine(int thickness) {
        JPanel line = new JPanel();
        line.setMaximumSize(new Dimension(Integer.MAX_VALUE, thickness));
        line.setPreferredSize(new Dimension(Integer.MAX_VALUE, thickness));
        line.setBackground(Color.WHITE);
        return line;
    }

    public static void main(String[] args) {
        new Start().setVisible(true);
    }
}