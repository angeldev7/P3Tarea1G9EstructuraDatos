package app;

import view.MainFrame;
import view.Ui;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Forzar Nimbus para que respete colores personalizados
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ignored) {}
            }

            // Paleta por defecto para componentes clave (asegura visibilidad)
            UIManager.put("Panel.background", Ui.BG);
            UIManager.put("Label.foreground", Ui.TEXT);
            UIManager.put("Button.background", Ui.CARD);
            UIManager.put("Button.foreground", Ui.TEXT);
            UIManager.put("Button.font", Ui.SUBTITLE);
            UIManager.put("ToolTip.background", Ui.CARD);
            UIManager.put("ToolTip.foreground", Ui.TEXT);

            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
