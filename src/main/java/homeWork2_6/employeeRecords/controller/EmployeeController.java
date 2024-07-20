package homeWork2_6.employeeRecords.controller;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.exception.EmployeeNotFoundException;
import homeWork2_6.employeeRecords.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@ResponseStatus
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping()
    public Collection<Employee> getWelcomeListEmployee() {
        return service.allEmployee();
    }

    @GetMapping("/add")
    public Employee getAddEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam double salary,
                                   @RequestParam short department) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundException();
        }
        return service.add(firstName, lastName, salary, department);
    }

    @GetMapping("/remove")
    public Employee getRemoveEmployee(@RequestParam String firstName,
                                      @RequestParam String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundException();
        }
        return service.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee getFindEmployee(@RequestParam String firstName,
                                    @RequestParam String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundException();
        }
        return service.find(firstName, lastName);
    }
}
