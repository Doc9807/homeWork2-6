package homeWork2_6.employeeRecords.controller;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.service.EmployeeService;
import homeWork2_6.employeeRecords.service.impl.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@ResponseStatus
@RequestMapping("/employee")
public abstract class EmployeeController implements EmployeeService {

    @GetMapping("/add")
    public Employee getAddEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("salary") double salary,
                                   @RequestParam("department") int department) {
        return add(firstName, lastName, salary, department);
    }

    @GetMapping("/remove")
    public Employee getRemoveEmployee(@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName) {
        return remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee getFindEmployee(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName) {
        return find(firstName, lastName);
    }

    @GetMapping("/find/all")
    public Collection<Employee> findAllEmployee() {
        return findAllEmployee();
    }
}
