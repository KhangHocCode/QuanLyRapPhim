package gui;

import java.awt.Component;
import java.awt.Container;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;

public final class TooltipSupport {
    private TooltipSupport() {
    }

    public static void apply(Component root) {
        if (root == null) {
            return;
        }
        applyRecursive(root);
    }

    private static void applyRecursive(Component component) {
        if (component instanceof JComponent) {
            JComponent jComponent = (JComponent) component;
            if (isBlank(jComponent.getToolTipText())) {
                String tooltip = buildTooltip(jComponent);
                if (!isBlank(tooltip)) {
                    jComponent.setToolTipText(tooltip);
                }
            }
        }

        if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                applyRecursive(child);
            }
        }
    }

    private static String buildTooltip(JComponent component) {
        if (component instanceof JTable) {
            return "Danh sách dữ liệu. Chọn một dòng để xem hoặc thao tác.";
        }
        if (component instanceof JTree) {
            return "Chọn mục để lọc dữ liệu.";
        }
        if (component instanceof JComboBox<?>) {
            return "Chọn một giá trị từ danh sách.";
        }
        if (component instanceof JCheckBox) {
            return "Bật hoặc tắt tùy chọn này.";
        }
        if (component instanceof JRadioButton) {
            return "Chọn một phương án.";
        }
        if (component instanceof JTextArea || component instanceof JTextField) {
            JTextField textField = component instanceof JTextField ? (JTextField) component : null;
            if (textField != null && !textField.isEditable()) {
                return "Trường chỉ đọc.";
            }
            return "Nhập thông tin tại đây.";
        }
        if (component instanceof JList<?>) {
            return "Chọn một mục từ danh sách.";
        }
        if (component instanceof JScrollPane) {
            return "Cuộn để xem thêm nội dung.";
        }
        if (component instanceof AbstractButton) {
            AbstractButton button = (AbstractButton) component;
            String action = normalizeAction(button.getText());
            if (isBlank(action)) {
                return "Nhấn để thực hiện thao tác.";
            }
            if (isNavAction(action)) {
                return "Nhấn để xem " + action + ".";
            }
            if ("đăng xuất".equals(action)) {
                return "Nhấn để đăng xuất.";
            }
            return "Nhấn để " + action + ".";
        }
        return null;
    }

    private static boolean isNavAction(String action) {
        return "trang chủ".equals(action)
            || "bán vé".equals(action)
            || "phim".equals(action)
            || "suất chiếu".equals(action)
            || "khách hàng".equals(action)
            || "nhân viên".equals(action)
            || "thống kê".equals(action)
            || "hóa đơn".equals(action)
            || "vé phim".equals(action);
    }

    private static String normalizeAction(String rawText) {
        if (isBlank(rawText)) {
            return "";
        }
        String cleaned = rawText.replaceAll("[^\\p{L}\\p{N}\\s]", "").trim().toLowerCase();
        return cleaned;
    }

    private static boolean isBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}
