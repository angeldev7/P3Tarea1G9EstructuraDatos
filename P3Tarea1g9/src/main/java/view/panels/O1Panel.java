package view.panels;

import controller.AppController;
import model.AnalizadorComplejidad;
import view.ChartPanel;
import view.Ui;

import javax.swing.*;
import java.awt.*;

public class O1Panel extends JPanel {

    private final JLabel lblT1 = valueLabel();
    private final JLabel lblT2 = valueLabel();
    private final JLabel lblRatio = valueLabel();
    private final ChartPanel chart = new ChartPanel();

    public O1Panel(AppController controller) {
        setLayout(new BorderLayout());
        setBackground(Ui.BG);

        JLabel title = new JLabel("Demostración O(1) - Tiempo constante");
        title.setForeground(Ui.TEXT);
        title.setFont(Ui.TITLE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JLabel desc = new JLabel("<html>Medimos tiempo con System.nanoTime(), en \u03bcs/ns.\n" +
            "Una operación constante (p. ej., acceso a arreglo) tarda casi lo mismo sin importar n; el ratio debería \u2248 1x.</html>");
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
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.WEST; c.insets = new Insets(6, 6, 6, 6);

        JPanel grid = new JPanel(new GridBagLayout());
        grid.setOpaque(false);
        addRow(grid, c, "n = 1,000", lblT1);
        addRow(grid, c, "n = 1,000,000", lblT2);
        addRow(grid, c, "Ratio", lblRatio);

        chart.setData(new String[]{"n = 1,000", "n = 1,000,000"}, new long[]{0, 0});

        card.add(grid, BorderLayout.WEST);
        card.add(chart, BorderLayout.CENTER);

        JButton run = new JButton("Ejecutar demo O(1)");
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
        lblT1.setText("Calculando...");
        lblT2.setText("Calculando...");
        lblRatio.setText("Calculando...");

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            AppController.O1Result result;
            @Override
            protected Void doInBackground() {
                result = controller.ejecutarO1();
                return null;
            }
            @Override
            protected void done() {
                lblT1.setText(AnalizadorComplejidad.formatearTiempo(result.tSmall));
                lblT2.setText(AnalizadorComplejidad.formatearTiempo(result.tLarge));
                lblRatio.setText(String.format("%.2fx", result.ratio));
                chart.setData(new String[]{"n = 1,000", "n = 1,000,000"}, new long[]{result.tSmall, result.tLarge});
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
