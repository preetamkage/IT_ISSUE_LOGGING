package ui;

import service.IssueService;

import javax.swing.*;
import java.awt.*;

public class AddIssueUI extends JFrame {

    IssueService service = new IssueService();

    public AddIssueUI(JFrame parent) {
        setTitle("Add Issue");
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JTextField name = new JTextField();
        JTextField dept = new JTextField();
        JTextArea desc = new JTextArea();

        JButton submit = new JButton("Submit");
        JButton back = new JButton("Back");

        setLayout(new GridLayout(7,1));

        add(new JLabel("Employee Name"));
        add(name);
        add(new JLabel("Department"));
        add(dept);
        add(new JLabel("Description"));
        add(new JScrollPane(desc));
        add(submit);
        add(back);

        submit.addActionListener(e -> {
            try {
                service.logIssue(name.getText(), dept.getText(), desc.getText());
                JOptionPane.showMessageDialog(this, "Issue Added!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        back.addActionListener(e -> dispose());

        setVisible(true);
    }
}