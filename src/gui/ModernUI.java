package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public final class ModernUI {
    private static boolean installed = false;

    private static final Font FONT_LABEL = new Font("Roboto", Font.PLAIN, 15);
    private static final Font FONT_INPUT = new Font("Roboto", Font.PLAIN, 15);
    private static final Font FONT_BUTTON = new Font("Roboto", Font.BOLD, 15);

    private static final Color COLOR_BG = new Color(12, 12, 12);
    private static final Color COLOR_SURFACE = new Color(22, 22, 22);
    private static final Color COLOR_BORDER = new Color(60, 60, 60);
    private static final Color COLOR_ACCENT = new Color(245, 197, 66);
    private static final Color COLOR_TEXT = Color.WHITE;

    private ModernUI() {
    }

    public static void installGlobalTheme() {
        if (installed) {
            return;
        }
        installed = true;

        UIManager.put("Panel.background", COLOR_BG);
        UIManager.put("Label.font", FONT_LABEL);
        UIManager.put("Label.foreground", COLOR_TEXT);
        UIManager.put("Button.font", FONT_BUTTON);
        UIManager.put("Button.background", COLOR_SURFACE);
        UIManager.put("Button.foreground", COLOR_TEXT);
        UIManager.put("TextField.font", FONT_INPUT);
        UIManager.put("TextField.background", new Color(30, 30, 30));
        UIManager.put("TextField.foreground", COLOR_TEXT);
        UIManager.put("PasswordField.font", FONT_INPUT);
        UIManager.put("PasswordField.background", new Color(30, 30, 30));
        UIManager.put("PasswordField.foreground", COLOR_TEXT);
        UIManager.put("TextArea.background", new Color(30, 30, 30));
        UIManager.put("TextArea.foreground", COLOR_TEXT);
        UIManager.put("ComboBox.font", FONT_INPUT);
        UIManager.put("ComboBox.background", new Color(30, 30, 30));
        UIManager.put("ComboBox.foreground", COLOR_TEXT);
        UIManager.put("Table.font", new Font("Roboto", Font.PLAIN, 14));
        UIManager.put("Table.background", COLOR_SURFACE);
        UIManager.put("Table.foreground", COLOR_TEXT);
        UIManager.put("TableHeader.font", new Font("Roboto", Font.BOLD, 14));
        UIManager.put("TableHeader.background", COLOR_ACCENT);
        UIManager.put("TableHeader.foreground", Color.BLACK);
        UIManager.put("Tree.background", COLOR_SURFACE);
        UIManager.put("Tree.foreground", COLOR_TEXT);
        UIManager.put("List.background", COLOR_SURFACE);
        UIManager.put("List.foreground", COLOR_TEXT);
        UIManager.put("ScrollPane.background", COLOR_BG);
        UIManager.put("Viewport.background", COLOR_BG);
        UIManager.put("TitledBorder.titleColor", COLOR_TEXT);
        UIManager.put("TitledBorder.font", new Font("Roboto", Font.BOLD, 16));
        UIManager.put("OptionPane.background", COLOR_BG);
        UIManager.put("OptionPane.messageAreaBackground", COLOR_SURFACE);
        UIManager.put("OptionPane.messageForeground", COLOR_TEXT);
        UIManager.put("OptionPane.foreground", COLOR_TEXT);
        UIManager.put("OptionPane.buttonFont", FONT_BUTTON);
    }

    public static void applySoftStyle(Component root) {
        if (root == null) {
            return;
        }
        applyRecursive(root);
    }

    public static JPanel createHeader(String title) {
        JPanel header = new JPanel(new java.awt.BorderLayout());
        header.setBackground(COLOR_BG);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_BORDER));
        JLabel label = new JLabel(title);
        label.setFont(new Font("Roboto", Font.BOLD, 20));
        label.setForeground(COLOR_ACCENT);
        label.setBorder(new EmptyBorder(12, 16, 12, 16));
        header.add(label, java.awt.BorderLayout.WEST);
        return header;
    }

    public static JPanel createContentCard(java.awt.LayoutManager layout) {
        JPanel card = new JPanel(layout);
        card.setBackground(COLOR_SURFACE);
        card.setBorder(new EmptyBorder(16, 16, 16, 16));
        return card;
    }

    public static Color getBackgroundColor() {
        return COLOR_BG;
    }

    public static Color getSurfaceColor() {
        return COLOR_SURFACE;
    }

    public static Color getAccentColor() {
        return COLOR_ACCENT;
    }

    private static void applyRecursive(Component component) {
        if (component instanceof JPanel) {
            JPanel panel = (JPanel) component;
            if (panel.isOpaque()) {
                panel.setBackground(COLOR_SURFACE);
            }
        }

        if (component instanceof AbstractButton) {
            styleButton((AbstractButton) component);
        }

        if (component instanceof JTextField || component instanceof JPasswordField || component instanceof JTextArea) {
            styleInput((JComponent) component);
        }

        if (component instanceof JComboBox<?>) {
            styleCombo((JComponent) component);
        }

        if (component instanceof JTable) {
            JTable table = (JTable) component;
            table.setRowHeight(Math.max(table.getRowHeight(), 30));
            table.setBackground(COLOR_SURFACE);
            table.setForeground(COLOR_TEXT);
            table.getTableHeader().setBackground(COLOR_ACCENT);
            table.getTableHeader().setForeground(Color.BLACK);
        }

        if (component instanceof javax.swing.JList<?>) {
            javax.swing.JList<?> list = (javax.swing.JList<?>) component;
            list.setBackground(COLOR_SURFACE);
            list.setForeground(COLOR_TEXT);
        }

        if (component instanceof javax.swing.JTree) {
            javax.swing.JTree tree = (javax.swing.JTree) component;
            tree.setBackground(COLOR_SURFACE);
            tree.setForeground(COLOR_TEXT);
        }

        if (component instanceof javax.swing.JLabel) {
            javax.swing.JLabel label = (javax.swing.JLabel) component;
            Color current = label.getForeground();
            if (current == null || !COLOR_ACCENT.equals(current)) {
                label.setForeground(COLOR_TEXT);
            }
        }

        if (component instanceof javax.swing.JCheckBox) {
            javax.swing.JCheckBox checkBox = (javax.swing.JCheckBox) component;
            checkBox.setBackground(COLOR_BG);
            checkBox.setForeground(COLOR_TEXT);
        }

        if (component instanceof javax.swing.JRadioButton) {
            javax.swing.JRadioButton radioButton = (javax.swing.JRadioButton) component;
            radioButton.setBackground(COLOR_BG);
            radioButton.setForeground(COLOR_TEXT);
        }

        if (component instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) component;
            Border rounded = new RoundedBorder(COLOR_BORDER, 12);
            scrollPane.setBorder(new CompoundBorder(rounded, new EmptyBorder(2, 2, 2, 2)));
            if (scrollPane.getViewport() != null) {
                scrollPane.getViewport().setBackground(COLOR_BG);
            }
        }

        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                applyRecursive(child);
            }
        }
    }

    private static void styleButton(AbstractButton button) {
        if (button.getFont() == null || button.getFont().getSize() < 15) {
            button.setFont(FONT_BUTTON);
        }
        button.setFocusPainted(false);
        Color defaultBg = UIManager.getColor("Button.background");
        if (button.getBackground() == null || button.getBackground().equals(defaultBg)) {
            button.setBackground(COLOR_SURFACE);
        }
        Color defaultFg = UIManager.getColor("Button.foreground");
        if (button.getForeground() == null || button.getForeground().equals(defaultFg)) {
            button.setForeground(COLOR_TEXT);
        }
        Border rounded = new RoundedBorder(COLOR_BORDER, 12);
        button.setBorder(new CompoundBorder(rounded, new EmptyBorder(4, 10, 4, 10)));
    }

    private static void styleInput(JComponent input) {
        if (input.getFont() == null || input.getFont().getSize() < 15) {
            input.setFont(FONT_INPUT);
        }
        Border rounded = new RoundedBorder(COLOR_BORDER, 12);
        input.setBorder(new CompoundBorder(rounded, BorderFactory.createEmptyBorder(2, 8, 2, 8)));
        if (input.isOpaque()) {
            input.setBackground(new Color(30, 30, 30));
            input.setForeground(COLOR_TEXT);
        }
    }

    private static void styleCombo(JComponent combo) {
        if (combo.getFont() == null || combo.getFont().getSize() < 15) {
            combo.setFont(FONT_INPUT);
        }
        Border rounded = new RoundedBorder(COLOR_BORDER, 12);
        combo.setBorder(new CompoundBorder(rounded, new EmptyBorder(2, 4, 2, 4)));
        if (combo.isOpaque()) {
            combo.setBackground(new Color(30, 30, 30));
            combo.setForeground(COLOR_TEXT);
        }
    }

    private static class RoundedBorder implements Border {
        private final Color color;
        private final int radius;

        RoundedBorder(Color color, int radius) {
            this.color = color;
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(1, 1, 1, 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }
}
