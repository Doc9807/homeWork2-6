package homeWork2_6.employeeRecords.service.impl;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.exception.EmployeeAlreadyAddedException;
import homeWork2_6.employeeRecords.exception.EmployeeNotFoundException;
import homeWork2_6.employeeRecords.exception.EmployeeStorageIsFullException;
import homeWork2_6.employeeRecords.service.EmployeeService;
import homeWork2_6.employeeRecords.validation.WorkStringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableCollection;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeesCompany = new HashMap<>();
    public static final short MAX_EMPLOYEES = 12;
    private final WorkStringUtils workStringUtils;

    public EmployeeServiceImpl(WorkStringUtils workStringUtils) {
        this.workStringUtils = workStringUtils;
    }

    @Override
    public Employee add(String firstName, String lastName, double salary, short department) {
        firstName = workStringUtils.checkAndCapitalize(firstName);
        lastName = workStringUtils.checkAndCapitalize(lastName);


        Employee employeeAdd = new Employee(firstName, lastName);
        if (employeesCompany.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        } else if (employeesCompany.containsKey(employeeAdd.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeesCompany.put(employeeAdd.getFullName(), employeeAdd);
        return employeeAdd;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employeeRemove = new Employee(firstName, lastName);
        if (employeesCompany.containsKey(employeeRemove.getFullName())) {
            return employeesCompany.remove(employeeRemove.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employeeFind = new Employee(firstName, lastName);
        if (employeesCompany.containsKey(employeeFind.getFullName())) {
            return employeesCompany.get(employeeFind.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> allEmployee() {
        return unmodifiableCollection(employeesCompany.values());
    }
}
