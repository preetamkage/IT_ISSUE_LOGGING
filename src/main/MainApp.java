package main;

import service.IssueService;   // ✅ REQUIRED IMPORT
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        IssueService service = new IssueService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Log IT Issue");
            System.out.println("2. View Issues");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Employee Name: ");
                    String name = sc.nextLine();

                    System.out.print("Department: ");
                    String dept = sc.nextLine();

                    System.out.print("Issue Description: ");
                    String desc = sc.nextLine();

                    service.logIssue(name, dept, desc);
                    break;

                case 2:
                    service.showIssues();
                    break;

                case 3:
                    System.out.println("Exiting system...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice\n");
            }
        }
    }
}
