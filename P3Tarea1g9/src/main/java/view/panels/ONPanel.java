package view.panels;

import controller.AppController;
import model.AnalizadorComplejidad;
import view.ChartPanel;
import view.Ui;

import javax.swing.*;
import java.awt.*;

public class ONPanel extends JPanel {

    private final JLabel t100 = valueLabel();
    private final JLabel t1k = valueLabel();
    private final JLabel t10k = valueLabel();
    private final JLabel r1 = valueLabel();
    private final JLabel r2 = valueLabel();
    private final ChartPanel chart = new ChartPanel();

    public ONPanel(AppController controller) {
        setLayout(new BorderLayout());
        setBackground(Ui.BG);

        JLabel title = new JLabel("Demostración O(n) - Crecimiento lineal");
        title.setForeground(Ui.TEXT);
        title.setFont(Ui.TITLE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JLabel desc = new JLabel("<html>Búsqueda secuencial recorre el arreglo completo.\n" +
            "El tiempo crece proporcionalmente a n: de 100 a 1,000 \u2248 10x; de 100 a 10,000 \u2248 100x.\n" +
            "Medimos en ns/\u03bcs para comparar ratios.</html>");
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
        addRow(grid, c, "n = 1,000", t1k);
        addRow(grid, c, "n = 10,000", t10k);
        addRow(grid, c, "Ratio 1,000/100", r1);
        addRow(grid, c, "Ratio 10,000/100", r2);

        chart.setData(new String[]{"n = 100", "n = 1,000", "n = 10,000"}, new long[]{0, 0, 0});

        card.add(grid, BorderLayout.WEST);
        card.add(chart, BorderLayout.CENTER);

        JButton run = new JButton("Ejecutar demo O(n)");
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
        t1k.setText("Calculando...");
        t10k.setText("Calculando...");
        r1.setText("-");
        r2.setText("-");

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            AppController.ONResult result;
            @Override
            protected Void doInBackground() {
                result = controller.ejecutarON();
                return null;
            }
            @Override
            protected void done() {
                t100.setText(AnalizadorComplejidad.formatearTiempo(result.t100));
                t1k.setText(AnalizadorComplejidad.formatearTiempo(result.t1k));
                t10k.setText(AnalizadorComplejidad.formatearTiempo(result.t10k));
                r1.setText(String.format("%.1fx", (double) result.t1k / (double) result.t100));
                r2.setText(String.format("%.1fx", (double) result.t10k / (double) result.t100));
                chart.setData(new String[]{"n = 100", "n = 1,000", "n = 10,000"}, new long[]{result.t100, result.t1k, result.t10k});
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
