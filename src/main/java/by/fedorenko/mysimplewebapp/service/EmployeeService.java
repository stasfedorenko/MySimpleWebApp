package by.fedorenko.mysimplewebapp.service;

import by.fedorenko.mysimplewebapp.dto.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {

    Employee get(Long id);

    List<Employee> getAll() throws SQLException;

    boolean insert(Employee employee) throws SQLException;

    boolean update(Employee employee) throws SQLException;

    boolean delete(Long id) throws SQLException;


}
