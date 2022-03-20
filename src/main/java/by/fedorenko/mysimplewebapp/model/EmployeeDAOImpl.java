package by.fedorenko.mysimplewebapp.model;


import by.fedorenko.mysimplewebapp.entity.Employee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee"
            + "(first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES "
            + " (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT employee_id, first_name, "
            + "last_name, department_id, job_title, gender, date_of_birth FROM employee WHERE employee_id =?";
    private static final String SELECT_ALL_EMPLOYEE = "SELECT * from employee ORDER BY employee_id";
    private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE employee_id = ?;";
    private static final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET" +
            " first_name = ?, last_name = ?, department_id = ?, " +
            " job_title = ?, gender = ?, date_of_birth = ?" +
            " WHERE employee_id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            String jdbcURL = "jdbc:postgresql://localhost:5432/employeedb";
            String jdbcUsername = "postgres";
            String jdbcPassword = "admin";
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public Employee get(Long id) {
        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            statement.setLong(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long employee_id = rs.getLong("employee_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                Long department_id = rs.getLong("department_id");
                String job_title = rs.getString("job_title");
                String gender = rs.getString("gender");
                Date date_of_birth = rs.getDate("date_of_birth");
                employee = new Employee(employee_id, first_name, last_name, department_id,
                        job_title, gender, date_of_birth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_EMPLOYEE)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long employee_id = rs.getLong("employee_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                Long department_id = rs.getLong("department_id");
                String job_title = rs.getString("job_title");
                String gender = rs.getString("gender");
                Date date_of_birth = rs.getDate("date_of_birth");
                employees.add(new Employee(employee_id, first_name, last_name, department_id,
                        job_title, gender, date_of_birth));
            }
        }
        return employees;
    }

    @Override
    public void insert(Employee employee) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setLong(3, employee.getDepartment_id());
            statement.setString(4, employee.getJob_title());
            statement.setObject(5, employee.getGender());
            statement.setDate(6, employee.getDate_of_birth());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Employee employee) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setLong(3, employee.getDepartment_id());
            statement.setString(4, employee.getJob_title());
            statement.setObject(5, employee.getGender());
            statement.setDate(6, employee.getDate_of_birth());
            statement.setLong(7, employee.getEmployee_id());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
