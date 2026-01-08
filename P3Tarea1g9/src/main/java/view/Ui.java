package view;

import java.awt.Color;
import java.awt.Font;

/**
 * Paleta y utilidades de UI para dar un look consistente.
 */
public class Ui {
    // Paleta de alto contraste para asegurar legibilidad en cualquier LAF
    public static final Color BG = new Color(30, 32, 45);
    public static final Color CARD = new Color(45, 47, 60);
    public static final Color ACCENT = new Color(0, 184, 212);
    public static final Color TEXT = new Color(245, 245, 250);
    public static final Color MUTED = new Color(195, 195, 205);

    public static final Font TITLE = new Font("SansSerif", Font.BOLD, 22);
    public static final Font SUBTITLE = new Font("SansSerif", Font.BOLD, 16);
    public static final Font BODY = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font MONO = new Font("Consolas", Font.PLAIN, 13);
}
