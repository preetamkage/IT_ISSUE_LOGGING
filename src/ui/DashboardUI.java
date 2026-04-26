package ui;

import javax.swing.*;
import java.awt.*;

public class DashboardUI extends JFrame {

    public DashboardUI() {
        setTitle("IT Issue Logger - Dashboard");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton add = new JButton("Add Issue");
        JButton view = new JButton("View Issues");
        JButton update = new JButton("Update Issue");
        JButton delete = new JButton("Delete Issue");
        JButton exit = new JButton("Exit");

        panel.add(add);
        panel.add(view);
        panel.add(update);
        panel.add(delete);
        panel.add(exit);

        add(panel);

        add.addActionListener(e -> new AddIssueUI(this));
        view.addActionListener(e -> new ViewIssuesUI(this));
        update.addActionListener(e -> new UpdateIssueUI(this));
        delete.addActionListener(e -> new DeleteIssueUI(this));
        exit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}