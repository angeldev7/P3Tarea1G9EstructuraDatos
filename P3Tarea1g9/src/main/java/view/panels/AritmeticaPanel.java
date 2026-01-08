package view.panels;

import view.Ui;

import javax.swing.*;
import java.awt.*;

public class AritmeticaPanel extends JPanel {
    public AritmeticaPanel() {
        setLayout(new BorderLayout());
        setBackground(Ui.BG);

        JLabel title = new JLabel("Aritmética de la notación O");
        title.setForeground(Ui.TEXT);
        title.setFont(Ui.TITLE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setBackground(Ui.CARD);
        area.setForeground(Ui.TEXT);
        area.setFont(Ui.BODY);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        area.setText(
                "Reglas clave:\n\n" +
                "1) Suma: O(f(n)) + O(g(n)) = O(max(f, g))\n" +
                "   Solo importa el término que crece más rápido.\n\n" +
                "2) Producto: O(f(n)) × O(g(n)) = O(f × g)\n" +
                "   Bucles anidados multiplican sus complejidades.\n\n" +
                "3) Constantes: O(c · f(n)) = O(f(n))\n" +
                "   Las constantes no cambian la clase de complejidad.\n\n" +
                "Ejemplos rápidos:\n" +
                "• O(n) + O(n²) = O(n²)\n" +
                "• O(n) × O(n) = O(n²)\n" +
                "• 5n² + 100n + 300 → O(n²)\n\n" +
                "Tip: Busca siempre el término dominante y olvida las constantes."
        );

        add(area, BorderLayout.CENTER);
    }
}
