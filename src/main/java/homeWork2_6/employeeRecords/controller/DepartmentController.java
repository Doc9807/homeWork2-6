package homeWork2_6.employeeRecords.controller;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseStatus
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalaryByDepartment(@RequestParam("Номер департамента") short departmentId) {
        return departmentService.getMaxSalaryByDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalaryByDepartment(@RequestParam("Номер департамента") short departmentId) {
        return departmentService.getMinSalaryByDepartment(departmentId);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam("Номер департамента") short departmentId) {
        return departmentService.getAllEmployeesByDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Short, List<Employee>> getAllByDepartment() {
        return departmentService.getAllByDepartment();
    }
}
