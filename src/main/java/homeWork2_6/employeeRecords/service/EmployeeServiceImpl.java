package homeWork2_6.employeeRecords.service;

import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.exception.EmployeeAlreadyAddedException;
import homeWork2_6.employeeRecords.exception.EmployeeNotFoundException;
import homeWork2_6.employeeRecords.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeesCompany;

    public static final short MAX_EMPLOYEES = 2;

    public EmployeeServiceImpl(List<Employee> employeesCompany) {
        this.employeesCompany = new ArrayList<>(MAX_EMPLOYEES);
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employeeAdd = new Employee(firstName, lastName);
        if (employeesCompany.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        } else if (employeesCompany.contains(employeeAdd)) {
            throw new EmployeeAlreadyAddedException();
        }
        employeesCompany.add(employeeAdd);
        return employeeAdd;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employeeRemove = new Employee(firstName, lastName);
        if (employeesCompany.contains(employeeRemove)) {
            employeesCompany.remove(employeeRemove);
            return employeeRemove;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employeeFind = new Employee(firstName, lastName);
        if (employeesCompany.contains(employeeFind)) {
            return employeeFind;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> allEmployee() {
        return new ArrayList<>(employeesCompany);
    }
}
