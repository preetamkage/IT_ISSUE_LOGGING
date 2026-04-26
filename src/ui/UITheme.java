package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * UITheme - Centralised styling constants and factory helpers.
 * Every screen imports this so the look stays consistent.
 */
public class UITheme {

    // Colours
    public static final Color BG         = new Color(15, 20, 35);
    public static final Color PANEL      = new Color(22, 30, 50);
    public static final Color CARD       = new Color(28, 38, 62);
    public static final Color ACCENT     = new Color(0, 180, 216);
    public static final Color ACCENT2    = new Color(72, 52, 212);
    public static final Color DANGER     = new Color(220, 53, 69);
    public static final Color SUCCESS    = new Color(40, 167, 69);
    public static final Color WARNING    = new Color(255, 193, 7);
    public static final Color TEXT       = new Color(220, 230, 245);
    public static final Color SUBTEXT    = new Color(130, 150, 180);
    public static final Color BORDER_CLR = new Color(40, 55, 85);
    public static final Color ROW_EVEN   = new Color(25, 35, 58);
    public static final Color ROW_ODD    = new Color(30, 42, 68);
    public static final Color ROW_SEL    = new Color(0, 100, 140);
    public static final Color TBL_HDR    = new Color(0, 130, 160);
    public static final Color INPUT_BG   = new Color(18, 25, 42);

    // Fonts
    public static final Font F_TITLE  = new Font("Segoe UI", Font.BOLD,  20);
    public static final Font F_LABEL  = new Font("Segoe UI", Font.BOLD,  13);
    public static final Font F_INPUT  = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font F_BTN    = new Font("Segoe UI", Font.BOLD,  13);
    public static final Font F_TABLE  = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font F_SMALL  = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font F_DASH   = new Font("Segoe UI", Font.BOLD,  15);

    // Shared service instance - all screens use the same one
    public static final service.IssueService SERVICE = new service.IssueService();

    // ------------------------------------------------------------------
    // Factory: styled JButton
    // ------------------------------------------------------------------
    public static JButton button(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(F_BTN);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(160, 40));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(bg.brighter()); }
            public void mouseExited (MouseEvent e) { btn.setBackground(bg); }
        });
        return btn;
    }

    // ------------------------------------------------------------------
    // Factory: styled JTextField
    // ------------------------------------------------------------------
    public static JTextField textField() {
        JTextField f = new JTextField();
        f.setFont(F_INPUT);
        f.setBackground(INPUT_BG);
        f.setForeground(TEXT);
        f.setCaretColor(ACCENT);
        f.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_CLR, 1),
                new EmptyBorder(5, 8, 5, 8)));
        return f;
    }

    // ------------------------------------------------------------------
    // Factory: styled JTextArea
    // ------------------------------------------------------------------
    public static JTextArea textArea(int rows) {
        JTextArea ta = new JTextArea(rows, 20);
        ta.setFont(F_INPUT);
        ta.setBackground(INPUT_BG);
        ta.setForeground(TEXT);
        ta.setCaretColor(ACCENT);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_CLR, 1),
                new EmptyBorder(5, 8, 5, 8)));
        return ta;
    }

    // ------------------------------------------------------------------
    // Factory: field label
    // ------------------------------------------------------------------
    public static JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setFont(F_LABEL);
        l.setForeground(SUBTEXT);
        return l;
    }

    // ------------------------------------------------------------------
    // Factory: section/title label
    // ------------------------------------------------------------------
    public static JLabel title(String text) {
        JLabel l = new JLabel(text);
        l.setFont(F_TITLE);
        l.setForeground(ACCENT);
        return l;
    }

    // ------------------------------------------------------------------
    // Apply dark background to any panel
    // ------------------------------------------------------------------
    public static void darkPanel(JPanel p) {
        p.setBackground(PANEL);
    }

    // ------------------------------------------------------------------
    // Standard frame setup
    // ------------------------------------------------------------------
    public static void initFrame(JFrame frame, String title, int w, int h) {
        frame.setTitle(title);
        frame.setSize(w, h);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(BG);
        frame.setResizable(true);
    }

    // ------------------------------------------------------------------
    // Shared header bar used by all child screens
    // ------------------------------------------------------------------
    public static JPanel headerBar(String screenTitle, String subtitle) {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(PANEL);
        bar.setBorder(new MatteBorder(0, 0, 2, 0, ACCENT));
        bar.setPreferredSize(new Dimension(0, 60));

        JLabel t = new JLabel("  " + screenTitle);
        t.setFont(F_TITLE);
        t.setForeground(ACCENT);

        JLabel s = new JLabel(subtitle + "  ");
        s.setFont(F_SMALL);
        s.setForeground(SUBTEXT);

        bar.add(t, BorderLayout.WEST);
        bar.add(s, BorderLayout.EAST);
        return bar;
    }

    // ------------------------------------------------------------------
    // Shared footer / status bar
    // ------------------------------------------------------------------
    public static JPanel footerBar(String hint) {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(10, 14, 26));
        bar.setBorder(new MatteBorder(1, 0, 0, 0, BORDER_CLR));
        bar.setPreferredSize(new Dimension(0, 26));

        JLabel l = new JLabel("  " + hint);
        l.setFont(F_SMALL);
        l.setForeground(SUBTEXT);
        bar.add(l, BorderLayout.WEST);

        JLabel v = new JLabel("IT Issue Logger v2.0  |  MySQL/JDBC  ");
        v.setFont(F_SMALL);
        v.setForeground(SUBTEXT);
        bar.add(v, BorderLayout.EAST);
        return bar;
    }
}