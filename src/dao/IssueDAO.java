package dao;

import dto.Issue;

public interface IssueDAO {
    void addIssue(Issue issue);
    void viewIssues();
}
