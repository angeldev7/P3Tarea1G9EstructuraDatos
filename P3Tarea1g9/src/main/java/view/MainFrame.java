package view;

import controller.AppController;
import view.panels.AritmeticaPanel;
import view.panels.O1Panel;
import view.panels.ON2Panel;
import view.panels.ONPanel;
import view.panels.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel content = new JPanel(cardLayout);
    private final AppController controller = new AppController();

    public MainFrame() {
        setTitle("Análisis de Complejidad - Visual");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        content.setBackground(Ui.BG);

        // Registrar vistas
        content.add(new WelcomePanel(), "welcome");
        content.add(new O1Panel(controller), "o1");
        content.add(new ONPanel(controller), "on");
        content.add(new ON2Panel(controller), "on2");
        content.add(new AritmeticaPanel(), "arit");

        // Menú lateral
        MenuPanel menu = new MenuPanel(name -> cardLayout.show(content, name));

        add(menu, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        // Mostrar inicio y resaltar botón correspondiente
        cardLayout.show(content, "welcome");
        menu.setSelected("welcome");
    }
}
