package in.kce.main;

import java.util.ArrayList;
import java.util.Scanner;

import in.kce.bean.Employee;
import in.kce.service.EmployeeService;

public class TestEmployee {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create EmployeeService only once
        EmployeeService employeeService = new EmployeeService();

        System.out.println("===== Employee Management =====");
        System.out.println("1. Store Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. Fetch One Employee");
        System.out.println("4. Fetch All Employees");
        System.out.println("5. Delete Employee");
        System.out.print("Enter Choice: ");

        int option = sc.nextInt();

        switch (option) {

        case 1:

            System.out.print("Enter Employee ID: ");
            int empId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Employee Name: ");
            String empName = sc.nextLine();

            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();

            boolean saveResult =
                    employeeService.saveEmployee(empId, empName, designation);

            if (saveResult) {
                System.out.println("Employee Saved Successfully");
            } else {
                System.out.println("Employee Save Failed");
            }
            break;

        case 2:

            System.out.print("Enter Employee ID: ");
            int updateId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Employee Name: ");
            String updateName = sc.nextLine();

            System.out.print("Enter New Designation: ");
            String updateDesignation = sc.nextLine();

            boolean updateResult =
                    employeeService.updateEmployee(
                            updateId,
                            updateName,
                            updateDesignation);

            if (updateResult) {
                System.out.println("Employee Updated Successfully");
            } else {
                System.out.println("Employee Update Failed");
            }
            break;

        case 3:

            System.out.print("Enter Employee ID: ");
            String searchId = sc.next();

            Employee emp = employeeService.getEmployee(searchId);

            if (emp != null) {
                System.out.println("\nEmployee Details");
                System.out.println("ID          : " + emp.getEmpId());
                System.out.println("Name        : " + emp.getEmpName());
                System.out.println("Designation : " + emp.getDesignation());
            } else {
                System.out.println("Employee Not Found");
            }
            break;

        case 4:

            ArrayList<Employee> employees =
                    employeeService.getAllEmployees();

            if (employees != null && !employees.isEmpty()) {

                System.out.println("\n--------------------------------");
                System.out.println("ID\tName\tDesignation");
                System.out.println("--------------------------------");

                for (Employee e : employees) {
                    System.out.println(
                            e.getEmpId() + "\t" +
                            e.getEmpName() + "\t" +
                            e.getDesignation());
                }

            } else {
                System.out.println("No Employees Found");
            }
            break;

        case 5:

            System.out.print("Enter Employee ID to Delete: ");
            String deleteId = sc.next();

            boolean deleteResult =
                    employeeService.deleteEmployee(deleteId);

            if (deleteResult) {
                System.out.println("Employee Deleted Successfully");
            } else {
                System.out.println("Employee Deletion Failed");
            }
            break;

        default:
            System.out.println("Invalid Choice");
        }

        sc.close();
    }
}