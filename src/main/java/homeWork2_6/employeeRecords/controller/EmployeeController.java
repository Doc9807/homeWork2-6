package homeWork2_6.employeeRecords.controller;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.service.impl.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@ResponseStatus
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee getAddEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("salary") double salary,
                                   @RequestParam("department") short department) {
        return employeeService.add(firstName, lastName, salary, department);
    }

    @GetMapping("/remove")
    public Employee getRemoveEmployee(@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee getFindEmployee(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping("/find/all")
    private Collection<Employee> findAllEmployee() {
        return employeeService.findAllEmployee();
    }
}
