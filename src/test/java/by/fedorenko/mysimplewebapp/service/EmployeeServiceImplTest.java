package by.fedorenko.mysimplewebapp.service;

import by.fedorenko.mysimplewebapp.dao.EmployeeDAO;
import by.fedorenko.mysimplewebapp.dao.EmployeeDAOImpl;
import by.fedorenko.mysimplewebapp.dto.Employee;
import by.fedorenko.mysimplewebapp.dto.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

class EmployeeServiceImplTest {

    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO);

    @Test
    void insert() {
        Employee employee = new Employee("test", "test", 1L, "test", Gender.FEMALE, new Date(11));
        try {
            Assertions.assertTrue(employeeService.insert(employee));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void get() {
        Assertions.assertNotNull(employeeService.get(1L));
    }

    @Test
    void getAll() {
        List<Employee> employees;
        try {
            employees = employeeService.getAll();
            for (Employee emp : employees) {
                Assertions.assertNotNull(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void update() {
        List<Employee> employees;
        try {
            employees = employeeService.getAll();
            for (Employee emp : employees) {
                Assertions.assertTrue(employeeService.update(emp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        Employee employee = new Employee("test", "test", 1L, "test", Gender.FEMALE, new Date(11));
        try {
            Assertions.assertTrue(employeeService.insert(employee));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Employee> employees;
        try {
            employees = employeeService.getAll();
            employee = employees.get(employees.size()-1);
            Assertions.assertTrue(employeeService.delete(employee.getEmployee_id()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}