package dto;

/**
 * Issue - Data Transfer Object.
 * Represents a single row from the issues table.
 */
public class Issue {

    private int    id;
    private String employeeName;
    private String department;
    private String issueDescription;
    private String dateLogged;

    public Issue() {}

    public Issue(String employeeName, String department, String issueDescription) {
        this.employeeName     = employeeName;
        this.department       = department;
        this.issueDescription = issueDescription;
    }

    public Issue(int id, String employeeName, String department,
                 String issueDescription, String dateLogged) {
        this.id               = id;
        this.employeeName     = employeeName;
        this.department       = department;
        this.issueDescription = issueDescription;
        this.dateLogged       = dateLogged;
    }

    public int    getId()               { return id; }
    public void   setId(int id)         { this.id = id; }

    public String getEmployeeName()                    { return employeeName; }
    public void   setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getDepartment()                  { return department; }
    public void   setDepartment(String department) { this.department = department; }

    public String getIssueDescription()                        { return issueDescription; }
    public void   setIssueDescription(String issueDescription) { this.issueDescription = issueDescription; }

    public String getDateLogged()                  { return dateLogged; }
    public void   setDateLogged(String dateLogged) { this.dateLogged = dateLogged; }
}