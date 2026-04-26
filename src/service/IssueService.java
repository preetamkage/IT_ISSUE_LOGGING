package service;

import dao.IssueDAO;
import daoimpl.IssueDAOImpl;
import dto.Issue;

import java.util.List;

/**
 * IssueService - Service / Business Logic Layer.
 * Sits between UI screens and the DAO layer.
 * All validation lives here so UI classes stay clean.
 */
public class IssueService {

    private final IssueDAO issueDAO;

    public IssueService() {
        this.issueDAO = new IssueDAOImpl();
    }

    public IssueService(IssueDAO issueDAO) {
        this.issueDAO = issueDAO;
    }

    // ---------------------------------------------------------------- logIssue
    public void logIssue(String employeeName, String department, String description) {
        if (employeeName == null || employeeName.trim().isEmpty())
            throw new IllegalArgumentException("Employee name cannot be empty.");
        if (department == null || department.trim().isEmpty())
            throw new IllegalArgumentException("Department cannot be empty.");
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("Issue description cannot be empty.");

        issueDAO.addIssue(new Issue(employeeName.trim(), department.trim(), description.trim()));
    }

    // ----------------------------------------------------------- fetchAllIssues
    public List<Issue> fetchAllIssues() {
        return issueDAO.getAllIssues();
    }

    // ------------------------------------------------------------- removeIssue
    public boolean removeIssue(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("Issue ID must be a positive integer.");
        return issueDAO.deleteIssue(id);
    }

    // ------------------------------------------------------------- updateIssue
    public boolean updateIssue(int id, String newDescription) {
        if (id <= 0)
            throw new IllegalArgumentException("Issue ID must be a positive integer.");
        if (newDescription == null || newDescription.trim().isEmpty())
            throw new IllegalArgumentException("New description cannot be empty.");
        return issueDAO.updateIssue(id, newDescription.trim());
    }
}