package Kirill0798.practice.controller;

import Kirill0798.practice.domain.Employee;
import Kirill0798.practice.repo.EmployeeRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeController(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("{employee_id}")
    public Employee getOne(@PathVariable("employee_id") Employee employee){
        return employee;
    }

    @GetMapping
    public List<String> list(){
        return employeeRepo.firstRequest();
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

    @PutMapping("{employee_id}")
    public Employee update(@PathVariable("employee_id") Employee employeeFromDB, @RequestBody Employee employee){
        BeanUtils.copyProperties(employee, employeeFromDB, "employee_id");
        return employeeRepo.save(employeeFromDB);
    }

    @DeleteMapping("{employee_id}")
    public void delete(@PathVariable("employee_id") Employee employee){
        employeeRepo.delete(employee);
    }

}

