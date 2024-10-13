package homeWork2_6.employeeRecords.controller;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseStatus
@RequestMapping("/departments")
public class DepartmentController implements DepartmentService {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/sum")
    public double getSumSalaryByDepartment(@PathVariable("departmentId") int departmentId) {
        return departmentService.getSumSalaryByDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public double getMaxSalaryByDepartment(@PathVariable("departmentId") int departmentId) {
        return departmentService.getMaxSalaryByDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public double getMinSalaryByDepartment(@PathVariable("departmentId") int departmentId) {
        return departmentService.getMinSalaryByDepartment(departmentId);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getAllEmployeesByDepartment(@PathVariable("departmentId") int departmentId) {
        return departmentService.getAllEmployeesByDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllByDepartment() {
        return departmentService.getAllByDepartment();
    }
}
