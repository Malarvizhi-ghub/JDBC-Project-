package in.kce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import in.kce.bean.Employee;
import in.kce.util.DBUtil;

public class EmployeeDAO {

    // Store Employee
    public boolean saveEmployee(Employee employee) {

        Connection connection = DBUtil.getConnection();
        String query = "INSERT INTO employee VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, employee.getEmpId());
            preparedStatement.setString(2, employee.getEmpName());
            preparedStatement.setString(3, employee.getDesignation());

            int row = preparedStatement.executeUpdate();

            if (row >= 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Update Employee
    public boolean updateEmployee(Employee employee) {

        Connection connection = DBUtil.getConnection();
        String query = "UPDATE employee SET empName=?, designation=? WHERE empId=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, employee.getEmpName());
            preparedStatement.setString(2, employee.getDesignation());
            preparedStatement.setInt(3, employee.getEmpId());

            int row = preparedStatement.executeUpdate();

            if (row >= 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Delete Employee
    public boolean deleteEmployee(String empId) {

        Connection connection = DBUtil.getConnection();
        String query = "DELETE FROM employee WHERE empId=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(empId));

            int row = preparedStatement.executeUpdate();

            if (row >= 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Fetch One Employee
    public Employee getEmployee(String empId) {

        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM employee WHERE empId=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(empId));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Employee emp = new Employee();

                emp.setEmpId(rs.getInt("empId"));
                emp.setEmpName(rs.getString("empName"));
                emp.setDesignation(rs.getString("designation"));

                return emp;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Fetch All Employees
    public ArrayList<Employee> getAllEmployees() {

        Connection connection = DBUtil.getConnection();
        String query = "SELECT * FROM employee";

        ArrayList<Employee> al = new ArrayList<Employee>();

        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee emp = new Employee();

                emp.setEmpId(rs.getInt("empId"));
                emp.setEmpName(rs.getString("empName"));
                emp.setDesignation(rs.getString("designation"));

                al.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return al;
    }
}