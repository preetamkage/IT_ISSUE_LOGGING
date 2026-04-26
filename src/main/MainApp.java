package main;

import ui.IssueLoggerApp;
import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            // use default
        }
        SwingUtilities.invokeLater(() -> new IssueLoggerApp());
    }
}