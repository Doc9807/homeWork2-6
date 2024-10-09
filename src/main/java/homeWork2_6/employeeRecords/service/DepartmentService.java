package homeWork2_6.employeeRecords.service;

import homeWork2_6.employeeRecords.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    double getMaxSalaryByDepartment(short departmentId);

    double getMinSalaryByDepartment(short departmentId);

    List<Employee> getAllEmployeesByDepartment(short departmentId);

    Map<Short, List<Employee>> getAllByDepartment();

    double getSumSalaryByDepartment(short departmentId);
}
