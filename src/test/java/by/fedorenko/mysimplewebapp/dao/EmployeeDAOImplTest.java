package by.fedorenko.mysimplewebapp.dao;

import by.fedorenko.mysimplewebapp.dto.Employee;
import by.fedorenko.mysimplewebapp.dto.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOImplTest {

    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();


    @Test
    void insert() {
        Employee employee = new Employee("test", "test", 1L, "test", Gender.FEMALE, new Date(11));
        try {
            Assertions.assertTrue(employeeDAO.insert(employee));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void get() {
        Assertions.assertNotNull(employeeDAO.get(1L));
    }

    @Test
    void getAll() {
        List<Employee> employees;
        try {
            employees = employeeDAO.getAll();
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
            employees = employeeDAO.getAll();
            for (Employee emp : employees) {
                Assertions.assertTrue(employeeDAO.update(emp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        Employee employee = new Employee("test", "test", 1L, "test", Gender.FEMALE, new Date(11));
        try {
            Assertions.assertTrue(employeeDAO.insert(employee));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Employee> employees;
        try {
            employees = employeeDAO.getAll();
            employee = employees.get(employees.size()-1);
            Assertions.assertTrue(employeeDAO.delete(employee.getEmployee_id()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}