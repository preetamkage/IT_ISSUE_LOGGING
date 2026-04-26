package dao;

import dto.Issue;
import java.util.List;

/**
 * IssueDAO - Data Access Object Interface
 * Defines CRUD operations for the issues table.
 */
public interface IssueDAO {
    void addIssue(Issue issue);
    List<Issue> getAllIssues();
    boolean deleteIssue(int id);
    boolean updateIssue(int id, String newDescription);  // NEW for Update screen
}