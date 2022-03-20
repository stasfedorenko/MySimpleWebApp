package by.fedorenko.mysimplewebapp.service;


import by.fedorenko.mysimplewebapp.entity.Employee;
import by.fedorenko.mysimplewebapp.model.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public Employee get(Long id) {
        return employeeDAO.get(id);
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        return employeeDAO.getAll();
    }

    @Override
    public void insert(Employee employee) throws SQLException {
        employeeDAO.insert(employee);
    }

    @Override
    public void update(Employee employee) throws SQLException {
        employeeDAO.update(employee);
    }

    @Override
    public void delete(Long id) throws SQLException {
        employeeDAO.delete(id);
    }
}
