package daoimpl;

import dao.IssueDAO;
import db.DBConnection;
import dto.Issue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class IssueDAOImpl implements IssueDAO {

    @Override
    public void addIssue(Issue issue) {
        try {
            Connection con = DBConnection.getConnection();

            String sql =
                "INSERT INTO issues (employee_name, department, issue_description, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, issue.getEmployeeName());
            ps.setString(2, issue.getDepartment());
            ps.setString(3, issue.getDescription());
            ps.setString(4, issue.getStatus());

            ps.executeUpdate();
            con.close();

            System.out.println("✅ Issue logged successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewIssues() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM issues");

            while (rs.next()) {
                System.out.println("---------------------------");
                System.out.println("Issue ID   : " + rs.getInt("issue_id"));
                System.out.println("Employee  : " + rs.getString("employee_name"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Issue     : " + rs.getString("issue_description"));
                System.out.println("Status    : " + rs.getString("status"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
