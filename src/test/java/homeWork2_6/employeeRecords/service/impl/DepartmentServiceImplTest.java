package homeWork2_6.employeeRecords.service.impl;

import com.github.javafaker.Faker;
import homeWork2_6.employeeRecords.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private static final Faker faker = new Faker();

    Random random = new Random();
    short randomNum = (short) (random.nextInt(10 - 3 + 1) + 3);

    private static final Collection<Employee> employees = List.of(
            new Employee(faker.name().firstName(), "Zamorskii", nextDouble(), (short) 1),
            new Employee("Agafon", faker.name().lastName(), 146289.5, (short) 2),
            new Employee(faker.name().firstName(), faker.name().lastName(), nextDouble(), (short) 2),
            new Employee("Zaiats", "Volkov", 201763.4, (short) 1));

    @Test
    void getSumSalaryByDepartment_WhenCorrectRandomizeData_ThenReturnCorrectSum() {
        short department = 1;
        when(employeeService.findAllEmployee()).thenReturn(employees);
        double expected = 0;
        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                expected += employee.getSalary();
            }
        }

        //test
        double actual = departmentService.getSumSalaryByDepartment(department);

        //check
        assertThat(actual).isEqualTo(expected);

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getSumSalaryByDepartment_WhenEmptyMap_ThenReturnZero() {
        when(employeeService.findAllEmployee()).thenReturn(new ArrayList<>());

        //test
        double actual = departmentService.getSumSalaryByDepartment((short) 1);

        //check
        assertThat(actual).isZero();

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getMaxSalaryByDepartment_WhenCorrectRandomizeData_ThenReturnCorrectMaxSalary() {
        short department = 2;
        when(employeeService.findAllEmployee()).thenReturn(employees);
        double expected = employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .max().orElseThrow();

        //test
        double actual = departmentService.getMaxSalaryByDepartment(department);

        //check
        assertThat(actual).isEqualTo(expected);

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getMaxSalaryByDepartment_WhenEmptyMap_ThenThrowException() {
        when(employeeService.findAllEmployee()).thenReturn(new ArrayList<>());

        //test && check
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> departmentService.getMaxSalaryByDepartment((short) 1));

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getMaxSalaryByDepartment_WhenNotContainsDepartmentsEmployees_ThenThrowException() {
        when(employeeService.findAllEmployee()).thenReturn(employees);

        //test && check
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> departmentService.getMaxSalaryByDepartment((short) randomNum));

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getMinSalaryByDepartment_WhenCorrectRandomizeData_ThenReturnCorrectMinSalary() {
        short department = 2;
        when(employeeService.findAllEmployee()).thenReturn(employees);
        double expected = employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .min().orElseThrow();

        //test
        double actual = departmentService.getMinSalaryByDepartment(department);

        //check
        assertThat(actual).isEqualTo(expected);

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getMinSalaryByDepartment_WhenEmptyMap_ThenThrowException() {
        when(employeeService.findAllEmployee()).thenReturn(new ArrayList<>());

        //test && check
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> departmentService.getMinSalaryByDepartment((short) 1));

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getMinSalaryByDepartment_WhenNotContainsDepartmentsEmployees_ThenThrowException() {
        when(employeeService.findAllEmployee()).thenReturn(employees);

        //test && check
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> departmentService.getMinSalaryByDepartment((short) randomNum));

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getAllEmployeesByDepartment_WhenCorrectDepartment_ThenReturnEmployeeList() {
        short department = 2;
        when(employeeService.findAllEmployee()).thenReturn(employees);
        List<Employee> expected = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartment() == department) {
                expected.add(employee);
            }
        }

        // test
        List<Employee> actual = departmentService.getAllEmployeesByDepartment(department);

        // check
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getAllEmployeesByDepartment_WhenMapNotContainsDepartmentEmployees_ThenReturnEmptyList() {
        when(employeeService.findAllEmployee()).thenReturn(employees);

        // test
        List<Employee> actual = departmentService.getAllEmployeesByDepartment(randomNum);

        // check
        assertThat(actual).isEmpty();

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getAllByDepartment_WhenCorrectData_ThenReturnCorrectMap() {
        when(employeeService.findAllEmployee()).thenReturn(employees);
        Map<Short, List<Employee>> expected = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        // test
        Map<Short, List<Employee>> actual = departmentService.getAllByDepartment();

        // check
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);

        verify(employeeService, only()).findAllEmployee();
    }

    @Test
    void getAllByDepartment_WhenEmptyListOfEmployees_ThenReturnEmptyMap() {
        when(employeeService.findAllEmployee()).thenReturn(new ArrayList<>());

        // test
        Map<Short, List<Employee>> actual = departmentService.getAllByDepartment();

        // check
        assertThat(actual).isEmpty();

        verify(employeeService, only()).findAllEmployee();
    }

}