package by.fedorenko.mysimplewebapp.dao;

import by.fedorenko.mysimplewebapp.dto.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    Employee get(Long id);

    List<Employee> getAll() throws SQLException;

    void insert(Employee employee) throws SQLException;

    void update(Employee employee) throws SQLException;

    void delete(Long id) throws SQLException;


}
