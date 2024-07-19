package homeWork2_6.employeeRecords.service.impl;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMaxSalaryByDepartment(short departmentId) {
        return employeeService.allEmployee()
                .stream()
                .max((o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()))
                .orElseThrow();
    }

    @Override
    public Employee getMinSalaryByDepartment(short departmentId) {
        return employeeService.allEmployee()
                .stream()
                .min((o1, o2) -> Double.compare(o1.getSalary(), o2.getSalary()))
                .orElseThrow();
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(short departmentId) {
        return employeeService.allEmployee()
                .stream()
                .filter(x -> x.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Short, List<Employee>> getAllByDepartment() {
        return employeeService.allEmployee()
                .stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment()));

    }
}
