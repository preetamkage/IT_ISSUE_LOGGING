package ui;

import service.IssueService;

import javax.swing.*;
import java.awt.*;

public class DeleteIssueUI extends JFrame {

    IssueService service = new IssueService();

    public DeleteIssueUI(JFrame parent) {
        setTitle("Delete Issue");
        setSize(300,200);
        setLocationRelativeTo(parent);

        JTextField id = new JTextField();

        JButton delete = new JButton("Delete");
        JButton back = new JButton("Back");

        setLayout(new GridLayout(4,1));

        add(new JLabel("Issue ID"));
        add(id);
        add(delete);
        add(back);

        delete.addActionListener(e -> {
            try {
                int issueId = Integer.parseInt(id.getText());
                boolean res = service.removeIssue(issueId);
                JOptionPane.showMessageDialog(this, res ? "Deleted!" : "Not Found");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID");
            }
        });

        back.addActionListener(e -> dispose());

        setVisible(true);
    }
}