package homeWork2_6.employeeRecords.service;

import homeWork2_6.employeeRecords.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    double getMaxSalaryByDepartment(int departmentId);

    double getMinSalaryByDepartment(int departmentId);

    List<Employee> getAllEmployeesByDepartment(int departmentId);

    Map<Integer, List<Employee>> getAllByDepartment();

    double getSumSalaryByDepartment(int departmentId);
}
