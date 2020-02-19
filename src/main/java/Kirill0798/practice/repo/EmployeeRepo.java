package Kirill0798.practice.repo;

import Kirill0798.practice.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT employees.last_name, employees.salary, employees.department_id from (employees INNER JOIN ( SELECT  department_id, AVG(salary) department_average from employees GROUP BY department_id) departments_with_avg ON employees.department_id = departments_with_avg.department_id) Where employees.salary > departments_with_avg.department_average order by employees.salary desc", nativeQuery = true)
    List<String> firstRequest();

}
