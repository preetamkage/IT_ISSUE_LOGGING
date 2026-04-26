package ui;

import dto.Issue;
import service.IssueService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewIssuesUI extends JFrame {

    IssueService service = new IssueService();

    public ViewIssuesUI(JFrame parent) {
        setTitle("View Issues");
        setSize(700,400);
        setLocationRelativeTo(parent);

        String[] cols = {"ID","Name","Dept","Issue","Date"};
        DefaultTableModel model = new DefaultTableModel(cols,0);
        JTable table = new JTable(model);

        JButton refresh = new JButton("Refresh");
        JButton back = new JButton("Back");

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel p = new JPanel();
        p.add(refresh);
        p.add(back);
        add(p, BorderLayout.SOUTH);

        refresh.addActionListener(e -> {
            model.setRowCount(0);
            List<Issue> list = service.fetchAllIssues();
            for(Issue i : list){
                model.addRow(new Object[]{
                        i.getId(),
                        i.getEmployeeName(),
                        i.getDepartment(),
                        i.getIssueDescription(),
                        i.getDateLogged()
                });
            }
        });

        back.addActionListener(e -> dispose());

        setVisible(true);
    }
}