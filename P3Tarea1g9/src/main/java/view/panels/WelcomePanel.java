package view.panels;

import view.Ui;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel() {
        setLayout(new BorderLayout());
        setBackground(Ui.BG);

        JLabel title = new JLabel("Visualiza la notación asintótica", SwingConstants.LEFT);
        title.setForeground(Ui.TEXT);
        title.setFont(Ui.TITLE);
        title.setBorder(BorderFactory.createEmptyBorder(24, 24, 8, 24));
        add(title, BorderLayout.NORTH);

        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setBackground(Ui.BG);
        info.setForeground(Ui.TEXT);
        info.setFont(Ui.BODY);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setBorder(BorderFactory.createEmptyBorder(0, 24, 24, 24));
        info.setText(
            "Esta aplicación demuestra Notación Asintótica y su aritmética con mediciones reales.\n\n" +
            "• Medición: usamos System.nanoTime(); los tiempos se muestran en ns (nanosegundos), μs (microsegundos) o ms.\n" +
            "• O(1): tiempo prácticamente constante aunque n crezca; ratio ~ 1x.\n" +
            "• O(n): tiempo proporcional a n (recorrido lineal); ratios ~10x (100→1,000), ~100x (100→10,000).\n" +
            "• O(n²): tiempo cuadrático (doble bucle); ratios ~4x (100→200), ~16x (100→400).\n" +
            "• Aritmética de O: suma → domina el mayor, producto → bucles anidados multiplican, constantes no importan.\n\n" +
            "Consejo: ejecuta varias veces; la JVM se calienta (JIT) y estabiliza los tiempos.\n");

        add(info, BorderLayout.CENTER);
    }
}
