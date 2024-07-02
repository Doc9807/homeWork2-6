package homeWork2_6.employeeRecords.controller;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.exception.EmployeeNotFoundException;
import homeWork2_6.employeeRecords.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
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
                                   @RequestParam String lastName) {
        if (firstName == null || lastName == null) {
            throw new EmployeeNotFoundException();
        }
        return service.add(firstName, lastName);
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
