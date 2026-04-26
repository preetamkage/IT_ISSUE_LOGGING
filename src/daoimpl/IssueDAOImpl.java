package daoimpl;

import dao.IssueDAO;
import db.DBConnection;
import dto.Issue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * IssueDAOImpl - Concrete JDBC implementation of IssueDAO.
 *
 * Required MySQL table:
 * -----------------------------------------------------------------
 * CREATE TABLE issues (
 *     id                INT           AUTO_INCREMENT PRIMARY KEY,
 *     employee_name     VARCHAR(100)  NOT NULL,
 *     department        VARCHAR(100)  NOT NULL,
 *     issue_description TEXT          NOT NULL,
 *     date_logged       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
 * );
 * -----------------------------------------------------------------
 */
public class IssueDAOImpl implements IssueDAO {

    // ---------------------------------------------------------------- addIssue
    @Override
    public void addIssue(Issue issue) {
        String sql = "INSERT INTO issues (employee_name, department, issue_description) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, issue.getEmployeeName());
            ps.setString(2, issue.getDepartment());
            ps.setString(3, issue.getIssueDescription());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add issue: " + e.getMessage(), e);
        }
    }

    // ------------------------------------------------------------- getAllIssues
    @Override
    public List<Issue> getAllIssues() {
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT id, employee_name, department, issue_description, date_logged " +
                     "FROM issues ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                issues.add(new Issue(
                        rs.getInt("id"),
                        rs.getString("employee_name"),
                        rs.getString("department"),
                        rs.getString("issue_description"),
                        rs.getString("date_logged")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch issues: " + e.getMessage(), e);
        }
        return issues;
    }

    // ------------------------------------------------------------- deleteIssue
    @Override
    public boolean deleteIssue(int id) {
        String sql = "DELETE FROM issues WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete issue: " + e.getMessage(), e);
        }
    }

    // ------------------------------------------------------------- updateIssue
    @Override
    public boolean updateIssue(int id, String newDescription) {
        String sql = "UPDATE issues SET issue_description = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newDescription);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update issue: " + e.getMessage(), e);
        }
    }
}