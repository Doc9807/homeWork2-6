package homeWork2_6.employeeRecords.service.impl;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
    public double getMaxSalaryByDepartment(int departmentId) {
        return employeeService.findAllEmployee()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder())
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public double getMinSalaryByDepartment(int departmentId) {
        return employeeService.findAllEmployee()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(int departmentId) {
        return employeeService.findAllEmployee()
                .stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .toList();
    }

    @Override
    public Map<Integer, List<Employee>> getAllByDepartment() {
        return employeeService.findAllEmployee()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public double getSumSalaryByDepartment(int departmentId) {
        return employeeService.findAllEmployee().
                stream().
                filter(employee -> employee.getDepartment() == departmentId).
                map(Employee::getSalary).
                reduce(0d, Double::sum);
    }
}
