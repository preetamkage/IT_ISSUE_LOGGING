package service;

import dao.IssueDAO;
import daoimpl.IssueDAOImpl;
import dto.Issue;

public class IssueService {

    private IssueDAO dao = new IssueDAOImpl();

    public void logIssue(String name, String dept, String desc) {
        Issue issue = new Issue(name, dept, desc, "OPEN");
        dao.addIssue(issue);
    }

    public void showIssues() {
        dao.viewIssues();
    }
}
