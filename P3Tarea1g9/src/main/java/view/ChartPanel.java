package view;

import model.AnalizadorComplejidad;

import javax.swing.*;
import java.awt.*;

public class ChartPanel extends JPanel {
    private String[] labels = new String[0];
    private long[] values = new long[0];

    public ChartPanel() {
        setBackground(Ui.CARD);
        setPreferredSize(new Dimension(400, 220));
    }

    public void setData(String[] labels, long[] values) {
        if (labels == null || values == null || labels.length != values.length) return;
        this.labels = labels;
        this.values = values;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int left = 20;
        int right = 20;
        int top = 16;
        int barH = 26;
        int gap = 12;

        long max = 0;
        for (long v : values) max = Math.max(max, v);
        if (max == 0) {
            g2.setColor(Ui.MUTED);
            g2.setFont(Ui.BODY);
            g2.drawString("Ejecuta la demo para ver el gráfico", left, top + barH);
            g2.dispose();
            return;
        }

        for (int i = 0; i < values.length; i++) {
            int y = top + i * (barH + gap);
            double ratio = (double) values[i] / (double) max;
            int barW = (int) ((width - left - right) * ratio);

            // Fondo línea guía
            g2.setColor(new Color(60, 62, 75));
            g2.fillRoundRect(left, y, width - left - right, barH, 10, 10);

            // Barra
            g2.setColor(Ui.ACCENT);
            g2.fillRoundRect(left, y, Math.max(6, barW), barH, 10, 10);

            // Texto
            g2.setColor(Ui.TEXT);
            g2.setFont(Ui.BODY);
            String t = labels[i] + "  •  " + AnalizadorComplejidad.formatearTiempo(values[i]);
            g2.drawString(t, left + 8, y + barH - 8);
        }
        g2.dispose();
    }
}
