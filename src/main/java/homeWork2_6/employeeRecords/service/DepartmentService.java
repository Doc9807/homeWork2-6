package homeWork2_6.employeeRecords.service;

import homeWork2_6.employeeRecords.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getMaxSalaryByDepartment(short departmentId);

    Employee getMinSalaryByDepartment(short departmentId);

    List<Employee> getAllEmployeesByDepartment(short departmentId);

    Map<Short, List<Employee>> getAllByDepartment();
}
