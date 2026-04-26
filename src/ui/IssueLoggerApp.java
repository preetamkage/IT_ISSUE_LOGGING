package ui;

import dto.Issue;
import service.IssueService;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * IssueLoggerApp - Main Swing GUI Window
 * Converts the CLI-based IT Issue Logger into a full Swing GUI.
 * Preserves the existing DAO -> Service -> DB architecture.
 */
public class IssueLoggerApp extends JFrame {

    private final IssueService issueService = new IssueService();

    // Colour palette
    private static final Color CLR_BG       = new Color(15, 20, 35);
    private static final Color CLR_PANEL    = new Color(22, 30, 50);
    private static final Color CLR_ACCENT   = new Color(0, 180, 216);
    private static final Color CLR_ACCENT2  = new Color(72, 52, 212);
    private static final Color CLR_DANGER   = new Color(220, 53, 69);
    private static final Color CLR_SUCCESS  = new Color(40, 167, 69);
    private static final Color CLR_TEXT     = new Color(220, 230, 245);
    private static final Color CLR_SUBTEXT  = new Color(130, 150, 180);
    private static final Color CLR_BORDER   = new Color(40, 55, 85);
    private static final Color CLR_TBL_HDR  = new Color(0, 130, 160);
    private static final Color CLR_ROW_EVEN = new Color(25, 35, 58);
    private static final Color CLR_ROW_ODD  = new Color(30, 42, 68);
    private static final Color CLR_ROW_SEL  = new Color(0, 100, 140);

    // Fonts
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD,  22);
    private static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD,  13);
    private static final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_BTN   = new Font("Segoe UI", Font.BOLD,  13);
    private static final Font FONT_TABLE = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 11);

    // Placeholder strings
    private static final String PH_NAME = "e.g. Arjun Sharma";
    private static final String PH_DEPT = "e.g. Finance, IT, HR";
    private static final String PH_ID   = "Enter numeric ID";

    // Form inputs
    private JTextField txtEmployeeName;
    private JTextField txtDepartment;
    private JTextArea  txtDescription;
    private JTextField txtDeleteId;

    // Table
    private JTable            issueTable;
    private DefaultTableModel tableModel;

    // Status bar
    private JLabel lblStatus;

    public IssueLoggerApp() {
        setTitle("IT Issue Logging System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1100, 720);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        getContentPane().setBackground(CLR_BG);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });

        buildUI();
        loadIssues();
        setVisible(true);
    }

    private void buildUI() {
        setLayout(new BorderLayout(0, 0));
        add(buildHeaderPanel(), BorderLayout.NORTH);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildStatusBar(),   BorderLayout.SOUTH);
    }

    // HEADER
    private JPanel buildHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(CLR_PANEL);
        header.setBorder(new MatteBorder(0, 0, 2, 0, CLR_ACCENT));
        header.setPreferredSize(new Dimension(0, 65));

        JLabel title = new JLabel("  IT Issue Logging System");
        title.setFont(FONT_TITLE);
        title.setForeground(CLR_ACCENT);

        JLabel subtitle = new JLabel("  Track. Resolve. Report.");
        subtitle.setFont(FONT_SMALL);
        subtitle.setForeground(CLR_SUBTEXT);

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        left.setOpaque(false);
        left.add(title);
        left.add(subtitle);

        JLabel dbBadge = new JLabel("MySQL Connected  ");
        dbBadge.setFont(FONT_SMALL);
        dbBadge.setForeground(CLR_SUCCESS);

        header.add(left,    BorderLayout.WEST);
        header.add(dbBadge, BorderLayout.EAST);
        return header;
    }

    // CENTER SPLIT PANE
    private JSplitPane buildCenterPanel() {
        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                buildFormPanel(),
                buildTablePanel());
        split.setDividerLocation(340);
        split.setDividerSize(4);
        split.setBackground(CLR_BG);
        split.setBorder(null);
        split.setContinuousLayout(true);
        return split;
    }

    // LEFT FORM PANEL
    private JPanel buildFormPanel() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(CLR_BG);
        wrapper.setBorder(new EmptyBorder(16, 16, 16, 8));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CLR_PANEL);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(CLR_BORDER, 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        card.add(sectionLabel("[ Log New Issue ]"));
        card.add(Box.createVerticalStrut(16));

        card.add(fieldLabel("Employee Name"));
        card.add(Box.createVerticalStrut(4));
        txtEmployeeName = styledTextField(PH_NAME);
        card.add(txtEmployeeName);
        card.add(Box.createVerticalStrut(12));

        card.add(fieldLabel("Department"));
        card.add(Box.createVerticalStrut(4));
        txtDepartment = styledTextField(PH_DEPT);
        card.add(txtDepartment);
        card.add(Box.createVerticalStrut(12));

        card.add(fieldLabel("Issue Description"));
        card.add(Box.createVerticalStrut(4));

        txtDescription = new JTextArea(5, 20);
        txtDescription.setFont(FONT_INPUT);
        txtDescription.setBackground(new Color(18, 25, 42));
        txtDescription.setForeground(CLR_TEXT);
        txtDescription.setCaretColor(CLR_ACCENT);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(CLR_BORDER, 1),
                new EmptyBorder(6, 8, 6, 8)
        ));

        JScrollPane descScroll = new JScrollPane(txtDescription);
        descScroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        descScroll.setBorder(null);
        card.add(descScroll);
        card.add(Box.createVerticalStrut(20));

        JPanel btnRow1 = new JPanel(new GridLayout(1, 2, 8, 0));
        btnRow1.setOpaque(false);
        btnRow1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        JButton btnSubmit = styledButton("Submit Issue", CLR_ACCENT);
        JButton btnClear  = styledButton("Clear Fields", CLR_ACCENT2);
        btnRow1.add(btnSubmit);
        btnRow1.add(btnClear);
        card.add(btnRow1);
        card.add(Box.createVerticalStrut(24));

        card.add(sectionLabel("[ Delete Issue ]"));
        card.add(Box.createVerticalStrut(12));
        card.add(fieldLabel("Issue ID to Delete"));
        card.add(Box.createVerticalStrut(4));
        txtDeleteId = styledTextField(PH_ID);
        card.add(txtDeleteId);
        card.add(Box.createVerticalStrut(12));

        JPanel btnRow2 = new JPanel(new GridLayout(1, 2, 8, 0));
        btnRow2.setOpaque(false);
        btnRow2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        JButton btnDelete = styledButton("Delete",  CLR_DANGER);
        JButton btnExit   = styledButton("Exit",    new Color(80, 90, 110));
        btnRow2.add(btnDelete);
        btnRow2.add(btnExit);
        card.add(btnRow2);
        card.add(Box.createVerticalGlue());

        btnSubmit.addActionListener(e -> handleSubmit());
        btnClear .addActionListener(e -> handleClear());
        btnDelete.addActionListener(e -> handleDelete());
        btnExit  .addActionListener(e -> confirmExit());

        wrapper.add(card, BorderLayout.CENTER);
        return wrapper;
    }

    // RIGHT TABLE PANEL
    private JPanel buildTablePanel() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 8));
        wrapper.setBackground(CLR_BG);
        wrapper.setBorder(new EmptyBorder(16, 8, 16, 16));

        JPanel toolbar = new JPanel(new BorderLayout());
        toolbar.setOpaque(false);

        JLabel tblTitle = new JLabel("All Logged Issues");
        tblTitle.setFont(FONT_LABEL);
        tblTitle.setForeground(CLR_TEXT);

        JButton btnRefresh = styledButton("Refresh", CLR_ACCENT);
        btnRefresh.setPreferredSize(new Dimension(100, 32));
        btnRefresh.addActionListener(e -> loadIssues());

        toolbar.add(tblTitle,   BorderLayout.WEST);
        toolbar.add(btnRefresh, BorderLayout.EAST);
        wrapper.add(toolbar, BorderLayout.NORTH);

        String[] columns = {"ID", "Employee Name", "Department", "Issue Description", "Date Logged"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        issueTable = new JTable(tableModel);
        styleTable();

        JScrollPane scrollPane = new JScrollPane(issueTable);
        scrollPane.setBackground(CLR_PANEL);
        scrollPane.getViewport().setBackground(CLR_ROW_EVEN);
        scrollPane.setBorder(new LineBorder(CLR_BORDER, 1));
        wrapper.add(scrollPane, BorderLayout.CENTER);

        JLabel rowHint = new JLabel("  Click Refresh to reload data");
        rowHint.setFont(FONT_SMALL);
        rowHint.setForeground(CLR_SUBTEXT);
        wrapper.add(rowHint, BorderLayout.SOUTH);

        return wrapper;
    }

    private void styleTable() {
        issueTable.setFont(FONT_TABLE);
        issueTable.setForeground(CLR_TEXT);
        issueTable.setBackground(CLR_ROW_EVEN);
        issueTable.setGridColor(CLR_BORDER);
        issueTable.setRowHeight(30);
        issueTable.setSelectionBackground(CLR_ROW_SEL);
        issueTable.setSelectionForeground(Color.WHITE);
        issueTable.setShowVerticalLines(true);
        issueTable.setIntercellSpacing(new Dimension(1, 1));
        issueTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        issueTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                setFont(FONT_TABLE);
                if (isSelected) {
                    setBackground(CLR_ROW_SEL);
                    setForeground(Color.WHITE);
                } else {
                    setBackground(row % 2 == 0 ? CLR_ROW_EVEN : CLR_ROW_ODD);
                    setForeground(CLR_TEXT);
                }
                setBorder(new EmptyBorder(0, 8, 0, 8));
                return this;
            }
        });

        JTableHeader header = issueTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(CLR_TBL_HDR);
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);

        int[] widths = {45, 140, 110, 350, 130};
        for (int i = 0; i < widths.length; i++) {
            issueTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }
    }

    // STATUS BAR
    private JPanel buildStatusBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(10, 14, 26));
        bar.setBorder(new MatteBorder(1, 0, 0, 0, CLR_BORDER));
        bar.setPreferredSize(new Dimension(0, 28));

        lblStatus = new JLabel("  Ready");
        lblStatus.setFont(FONT_SMALL);
        lblStatus.setForeground(CLR_SUCCESS);

        JLabel version = new JLabel("IT Issue Logger v2.0  |  MySQL/JDBC  ");
        version.setFont(FONT_SMALL);
        version.setForeground(CLR_SUBTEXT);

        bar.add(lblStatus, BorderLayout.WEST);
        bar.add(version,   BorderLayout.EAST);
        return bar;
    }

    // ACTION HANDLERS

    private void handleSubmit() {
        String name = getFieldValue(txtEmployeeName, PH_NAME);
        String dept = getFieldValue(txtDepartment,   PH_DEPT);
        String desc = txtDescription.getText().trim();

        try {
            issueService.logIssue(name, dept, desc);
            setStatus("Issue submitted successfully for " + name, CLR_SUCCESS);
            JOptionPane.showMessageDialog(this,
                    "Issue logged successfully!\nEmployee: " + name + "\nDepartment: " + dept,
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            handleClear();
            loadIssues();
        } catch (IllegalArgumentException ex) {
            setStatus("Validation error: " + ex.getMessage(), new Color(255, 193, 7));
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            setStatus("Database error: " + ex.getMessage(), CLR_DANGER);
            JOptionPane.showMessageDialog(this,
                    "Failed to log issue:\n" + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleClear() {
        txtEmployeeName.setForeground(CLR_SUBTEXT);
        txtEmployeeName.setText(PH_NAME);
        txtDepartment.setForeground(CLR_SUBTEXT);
        txtDepartment.setText(PH_DEPT);
        txtDescription.setText("");
        txtDeleteId.setForeground(CLR_SUBTEXT);
        txtDeleteId.setText(PH_ID);
        txtEmployeeName.requestFocus();
        setStatus("Fields cleared.", CLR_SUBTEXT);
    }

    private void handleDelete() {
        String idText = getFieldValue(txtDeleteId, PH_ID);

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter an Issue ID to delete.",
                    "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Issue ID must be a valid integer.\nYou entered: \"" + idText + "\"",
                    "Invalid ID", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete Issue #" + id + "?\nThis action cannot be undone.",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            boolean deleted = issueService.removeIssue(id);
            if (deleted) {
                setStatus("Issue #" + id + " deleted successfully.", CLR_SUCCESS);
                JOptionPane.showMessageDialog(this,
                        "Issue #" + id + " has been deleted.",
                        "Deleted", JOptionPane.INFORMATION_MESSAGE);
                txtDeleteId.setForeground(CLR_SUBTEXT);
                txtDeleteId.setText(PH_ID);
                loadIssues();
            } else {
                setStatus("No issue found with ID " + id, new Color(255, 193, 7));
                JOptionPane.showMessageDialog(this,
                        "No issue found with ID: " + id + "\nPlease verify the ID from the table.",
                        "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            setStatus("Error deleting issue: " + ex.getMessage(), CLR_DANGER);
            JOptionPane.showMessageDialog(this,
                    "Error deleting issue:\n" + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadIssues() {
        try {
            List<Issue> issues = issueService.fetchAllIssues();
            tableModel.setRowCount(0);
            for (Issue issue : issues) {
                tableModel.addRow(new Object[]{
                        issue.getId(),
                        issue.getEmployeeName(),
                        issue.getDepartment(),
                        issue.getIssueDescription(),
                        issue.getDateLogged()
                });
            }
            setStatus(issues.size() + " issue(s) loaded.", CLR_ACCENT);
        } catch (Exception ex) {
            setStatus("Failed to load issues: " + ex.getMessage(), CLR_DANGER);
            JOptionPane.showMessageDialog(this,
                    "Could not load issues from database:\n" + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmExit() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            db.DBConnection.closeConnection();
            System.exit(0);
        }
    }

    // UI HELPERS

    private JLabel sectionLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(CLR_ACCENT);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JLabel fieldLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(CLR_SUBTEXT);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JTextField styledTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setFont(FONT_INPUT);
        field.setBackground(new Color(18, 25, 42));
        field.setForeground(CLR_SUBTEXT);
        field.setCaretColor(CLR_ACCENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        field.setText(placeholder);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(CLR_BORDER, 1),
                new EmptyBorder(4, 8, 4, 8)
        ));
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(CLR_TEXT);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().trim().isEmpty()) {
                    field.setForeground(CLR_SUBTEXT);
                    field.setText(placeholder);
                }
            }
        });
        return field;
    }

    private String getFieldValue(JTextField field, String placeholder) {
        String val = field.getText().trim();
        return val.equals(placeholder) ? "" : val;
    }

    private JButton styledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(FONT_BTN);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btn.setBackground(bg.brighter()); }
            @Override
            public void mouseExited(MouseEvent e)  { btn.setBackground(bg); }
        });
        return btn;
    }

    private void setStatus(String message, Color color) {
        lblStatus.setText("  " + message);
        lblStatus.setForeground(color);
    }
}