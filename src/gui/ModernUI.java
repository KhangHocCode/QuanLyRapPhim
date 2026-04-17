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

    private static final Font FONT_LABEL = new Font("Segoe UI", Font.PLAIN, 15);
    private static final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 15);
    private static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 15);

    private static final Color COLOR_BG = new Color(248, 250, 252);
    private static final Color COLOR_SURFACE = new Color(255, 255, 255);
    private static final Color COLOR_BORDER = new Color(206, 214, 224);

    private ModernUI() {
    }

    public static void installGlobalTheme() {
        if (installed) {
            return;
        }
        installed = true;

        UIManager.put("Panel.background", COLOR_BG);
        UIManager.put("Label.font", FONT_LABEL);
        UIManager.put("Button.font", FONT_BUTTON);
        UIManager.put("TextField.font", FONT_INPUT);
        UIManager.put("PasswordField.font", FONT_INPUT);
        UIManager.put("ComboBox.font", FONT_INPUT);
        UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 15));
    }

    public static void applySoftStyle(Component root) {
        if (root == null) {
            return;
        }
        applyRecursive(root);
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
        }

        if (component instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) component;
            Border rounded = new RoundedBorder(COLOR_BORDER, 12);
            scrollPane.setBorder(new CompoundBorder(rounded, new EmptyBorder(2, 2, 2, 2)));
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
            input.setBackground(Color.WHITE);
        }
    }

    private static void styleCombo(JComponent combo) {
        if (combo.getFont() == null || combo.getFont().getSize() < 15) {
            combo.setFont(FONT_INPUT);
        }
        Border rounded = new RoundedBorder(COLOR_BORDER, 12);
        combo.setBorder(new CompoundBorder(rounded, new EmptyBorder(2, 4, 2, 4)));
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
