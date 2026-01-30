package dto;

public class Issue {

    private String employeeName;
    private String department;
    private String description;
    private String status;

    public Issue(String employeeName, String department,
                 String description, String status) {
        this.employeeName = employeeName;
        this.department = department;
        this.description = description;
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
