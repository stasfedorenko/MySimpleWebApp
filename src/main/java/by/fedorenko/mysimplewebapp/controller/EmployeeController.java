package by.fedorenko.mysimplewebapp.controller;

import by.fedorenko.mysimplewebapp.entity.Employee;
import by.fedorenko.mysimplewebapp.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView("/employees/employees-page");
        try {
            mv.getModel().put("employees",employeeService.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mv;
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable("id") Long id) {
        return employeeService.get(id);
    }


    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("/employees/employee-form");
        mv.getModel().put("employee", new Employee());
        mv.getModel().put("method","post");
        mv.getModel().put("title","Create employee");
        return mv;
    }

    @PostMapping
    public ModelAndView createPost(Employee employee){
        try {
            employeeService.insert(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/employees");
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("/employees/employee-form");
        mv.getModel().put("employee", employeeService.get(id));
        mv.getModel().put("method","PUT");
        mv.getModel().put("title","Update employee");
        return mv;
    }

    @PutMapping
    public ModelAndView update(Employee employee){
        try {
            employeeService.update(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/employees");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        try {
            employeeService.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/employees");
    }
}
