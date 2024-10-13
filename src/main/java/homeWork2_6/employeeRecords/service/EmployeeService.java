package homeWork2_6.employeeRecords.service;

import homeWork2_6.employeeRecords.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface EmployeeService {

    Employee add(String firstName, String lastName, double salary, int department);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> findAllEmployee();
}
