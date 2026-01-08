package view.panels;

import controller.AppController;
import model.AnalizadorComplejidad;
import view.ChartPanel;
import view.Ui;

import javax.swing.*;
import java.awt.*;

public class ON2Panel extends JPanel {

    private final JLabel t100 = valueLabel();
    private final JLabel t200 = valueLabel();
    private final JLabel t400 = valueLabel();
    private final JLabel r1 = valueLabel();
    private final JLabel r2 = valueLabel();
    private final ChartPanel chart = new ChartPanel();

    public ON2Panel(AppController controller) {
        setLayout(new BorderLayout());
        setBackground(Ui.BG);

        JLabel title = new JLabel("Demostración O(n²) - Crecimiento cuadrático");
        title.setForeground(Ui.TEXT);
        title.setFont(Ui.TITLE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JLabel desc = new JLabel("<html>Dos bucles anidados (comparar todos los pares).\n" +
            "El tiempo crece como n²: pasar de 100 a 200 \u2248 4x, de 100 a 400 \u2248 16x.\n" +
            "Las medidas están en ns/\u03bcs.</html>");
        desc.setForeground(Ui.MUTED);
        desc.setFont(Ui.BODY);
        desc.setBorder(BorderFactory.createEmptyBorder(6, 20, 10, 20));

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(Ui.BG);
        header.add(title);
        header.add(desc);
        add(header, BorderLayout.NORTH);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Ui.CARD);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST; c.insets = new Insets(6,6,6,6);

        JPanel grid = new JPanel(new GridBagLayout());
        grid.setOpaque(false);
        addRow(grid, c, "n = 100", t100);
        addRow(grid, c, "n = 200", t200);
        addRow(grid, c, "n = 400", t400);
        addRow(grid, c, "Ratio 200/100", r1);
        addRow(grid, c, "Ratio 400/100", r2);

        chart.setData(new String[]{"n = 100", "n = 200", "n = 400"}, new long[]{0, 0, 0});

        card.add(grid, BorderLayout.WEST);
        card.add(chart, BorderLayout.CENTER);

        JButton run = new JButton("Ejecutar demo O(n²)");
        run.setBackground(Ui.ACCENT);
        run.setForeground(Ui.BG);
        run.setFont(Ui.SUBTITLE);
        run.setFocusPainted(false);
        run.addActionListener(e -> runExperiment(controller, run));

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.setBackground(Ui.BG);
        south.add(run);

        add(card, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }

    private void runExperiment(AppController controller, JButton run) {
        run.setEnabled(false);
        t100.setText("Calculando...");
        t200.setText("Calculando...");
        t400.setText("Calculando...");
        r1.setText("-");
        r2.setText("-");

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            AppController.ON2Result result;
            @Override
            protected Void doInBackground() {
                result = controller.ejecutarON2();
                return null;
            }
            @Override
            protected void done() {
                t100.setText(AnalizadorComplejidad.formatearTiempo(result.t100));
                t200.setText(AnalizadorComplejidad.formatearTiempo(result.t200));
                t400.setText(AnalizadorComplejidad.formatearTiempo(result.t400));
                r1.setText(String.format("%.1fx", (double) result.t200 / (double) result.t100));
                r2.setText(String.format("%.1fx", (double) result.t400 / (double) result.t100));
                chart.setData(new String[]{"n = 100", "n = 200", "n = 400"}, new long[]{result.t100, result.t200, result.t400});
                run.setEnabled(true);
            }
        };
        worker.execute();
    }

    private void addRow(JPanel panel, GridBagConstraints c, String label, JLabel value) {
        c.gridx = 0;
        panel.add(textLabel(label), c);
        c.gridx = 1;
        panel.add(value, c);
        c.gridy++;
    }

    private JLabel textLabel(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(Ui.TEXT);
        l.setFont(Ui.BODY);
        return l;
    }

    private JLabel valueLabel() {
        JLabel l = new JLabel("-");
        l.setForeground(Ui.ACCENT);
        l.setFont(Ui.SUBTITLE);
        return l;
    }
}
