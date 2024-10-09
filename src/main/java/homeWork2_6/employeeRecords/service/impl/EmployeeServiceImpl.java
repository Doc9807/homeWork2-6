package homeWork2_6.employeeRecords.service.impl;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.exception.EmployeeAlreadyAddedException;
import homeWork2_6.employeeRecords.exception.EmployeeNotFoundException;
import homeWork2_6.employeeRecords.exception.EmployeeStorageIsFullException;
import homeWork2_6.employeeRecords.service.EmployeeService;
import homeWork2_6.employeeRecords.validation.ParameterValidator;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableCollection;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employeesCompany = new HashMap<>();
    public static final short MAX_EMPLOYEES = 12;
    private final ParameterValidator parameterValidator;

    public EmployeeServiceImpl(ParameterValidator workStringUtils) {
        this.parameterValidator = workStringUtils;
    }

    @Override
    public Employee add(String firstName, String lastName, double salary, short department) {
        firstName = parameterValidator.checkAndCapitalize(firstName);
        lastName = parameterValidator.checkAndCapitalize(lastName);

        checkStorageIsFull();

        Employee employee = new Employee(firstName, lastName, salary, department);

        checkEmployeeAlreadyAdded(employee);

        employeesCompany.put(employee.getFullName(), employee);

        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employeeRemove = new Employee(firstName, lastName);

        checkEmployeeForAvailability(employeeRemove);

        return employeesCompany.remove(employeeRemove.getFullName());
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employeeFind = new Employee(firstName, lastName);

        checkEmployeeForAvailability(employeeFind);

        return employeesCompany.get(employeeFind.getFullName());
    }

    @Override
    public Collection<Employee> findAllEmployee() {
        return unmodifiableCollection(employeesCompany.values());
    }

    private void checkStorageIsFull() {
        if (employeesCompany.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
    }

    private void checkEmployeeAlreadyAdded(Employee employee) {
        if (employee != null && employeesCompany.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException(employee.getFirstName(), employee.getLastName());
        }
    }

    private void checkEmployeeForAvailability(Employee employee) {
        if (employee != null && !employeesCompany.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException(employee.getFirstName(), employee.getLastName());
        }
    }
}
