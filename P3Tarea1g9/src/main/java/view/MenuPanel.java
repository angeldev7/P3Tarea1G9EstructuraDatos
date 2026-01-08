package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import view.Ui;

public class MenuPanel extends JPanel {

    private final Map<String, JButton> items = new HashMap<>();
    private String selected = null;
    private final Consumer<String> onSelect;

    public MenuPanel(Consumer<String> onSelect) {
        this.onSelect = onSelect;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(220, 0));
        setBackground(Ui.BG);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.weightx = 1; c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(6, 12, 6, 12);

        JLabel title = new JLabel("Complejidad Big-O", SwingConstants.CENTER);
        title.setForeground(Ui.TEXT);
        title.setFont(Ui.TITLE);
        c.gridy = 0; c.insets = new Insets(20, 12, 12, 12);
        add(title, c);

        c.insets = new Insets(6, 12, 6, 12);
        c.gridy++;
        add(btn("Inicio", "welcome"), c);
        c.gridy++;
        add(btn("O(1) Constante", "o1"), c);
        c.gridy++;
        add(btn("O(n) Lineal", "on"), c);
        c.gridy++;
        add(btn("O(n²) Cuadrática", "on2"), c);
        c.gridy++;
        add(btn("Aritmética de O", "arit"), c);

        c.gridy++; c.weighty = 1;
        add(Box.createVerticalGlue(), c);
    }

    private JButton btn(String text, String card) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setRolloverEnabled(false);
        b.setContentAreaFilled(true);
        b.setOpaque(true);
        // Forzar UI básica para respetar colores personalizados
        b.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        // Estilo inicial (no seleccionado)
        styleButton(b, false);
        b.setFont(Ui.SUBTITLE);
        // Tooltip que explica qué hace cada sección (compatible con Java < 14)
        switch (card) {
            case "welcome":
                b.setToolTipText("Inicio: descripción general y cómo usar la app");
                break;
            case "o1":
                b.setToolTipText("O(1): operación constante, el tiempo no cambia con n");
                break;
            case "on":
                b.setToolTipText("O(n): crecimiento lineal; el tiempo aumenta proporcional a n");
                break;
            case "on2":
                b.setToolTipText("O(n²): doble bucle; el tiempo crece cuadráticamente");
                break;
            case "arit":
                b.setToolTipText("Aritmética de O: reglas para combinar complejidades");
                break;
            default:
                break;
        }
        b.addActionListener(e -> {
            setSelected(card);
            if (onSelect != null) onSelect.accept(card);
        });
        items.put(card, b);
        return b;
    }

    private void styleButton(JButton b, boolean selected) {
        if (selected) {
            b.setBackground(Ui.ACCENT);
            b.setForeground(Color.BLACK);
            b.setBorder(BorderFactory.createLineBorder(Ui.TEXT, 3));
        } else {
            b.setBackground(Ui.CARD);
            b.setForeground(Ui.TEXT);
            b.setBorder(BorderFactory.createLineBorder(Ui.ACCENT, 2));
        }
    }

    public void setSelected(String card) {
        this.selected = card;
        items.forEach((key, button) -> styleButton(button, key.equals(card)));
    }
}
