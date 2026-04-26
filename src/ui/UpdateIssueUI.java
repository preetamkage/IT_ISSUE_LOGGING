package ui;

import service.IssueService;

import javax.swing.*;
import java.awt.*;

public class UpdateIssueUI extends JFrame {

    IssueService service = new IssueService();

    public UpdateIssueUI(JFrame parent) {
        setTitle("Update Issue");
        setSize(400,250);
        setLocationRelativeTo(parent);

        JTextField id = new JTextField();
        JTextArea desc = new JTextArea();

        JButton update = new JButton("Update");
        JButton back = new JButton("Back");

        setLayout(new GridLayout(5,1));

        add(new JLabel("Issue ID"));
        add(id);
        add(new JLabel("New Description"));
        add(new JScrollPane(desc));
        add(update);
        add(back);

        update.addActionListener(e -> {
            try {
                int issueId = Integer.parseInt(id.getText());
                boolean res = service.updateIssue(issueId, desc.getText());
                JOptionPane.showMessageDialog(this, res ? "Updated!" : "Not Found");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        back.addActionListener(e -> dispose());

        setVisible(true);
    }
}