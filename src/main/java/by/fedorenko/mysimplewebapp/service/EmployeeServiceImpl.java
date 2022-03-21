package by.fedorenko.mysimplewebapp.service;


import by.fedorenko.mysimplewebapp.dto.Employee;
import by.fedorenko.mysimplewebapp.dao.EmployeeDAO;
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
    public boolean insert(Employee employee) throws SQLException {
        return employeeDAO.insert(employee);
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        return employeeDAO.update(employee);
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        return employeeDAO.delete(id);
    }
}
