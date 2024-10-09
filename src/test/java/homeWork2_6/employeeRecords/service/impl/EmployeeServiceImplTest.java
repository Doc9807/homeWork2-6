package homeWork2_6.employeeRecords.service.impl;

import com.github.javafaker.Faker;
import homeWork2_6.employeeRecords.Employee;
import homeWork2_6.employeeRecords.exception.EmployeeAlreadyAddedException;
import homeWork2_6.employeeRecords.exception.EmployeeNotFoundException;
import homeWork2_6.employeeRecords.exception.EmployeeStorageIsFullException;
import homeWork2_6.employeeRecords.exception.ParamValidationException;
import homeWork2_6.employeeRecords.validation.ParameterValidator;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class EmployeeServiceImplTest {
    private static final Faker faker = new Faker();
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new ParameterValidator());

    @Test
    void shouldAddEmployee_WhenCorrectParams_ThenAdd() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        double salary = nextDouble();
        short department = (short) nextInt();

        //test
        Employee actual = employeeService.add(firstName, lastName, salary, department);

        //check
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);
        assertThat(actual.getSalary()).isEqualTo(salary);
        assertThat(actual.getDepartment()).isEqualTo(department);
    }

    @Test
    void shouldAddEmployee_WhenEmployeeAlreadyAdded_ThenThrowException() {
        Employee expected = employeeService.add(
                faker.name().firstName(),
                faker.name().lastName(),
                nextDouble(),
                (short) nextInt());

        //test && check
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(
                        expected.getFirstName(),
                        expected.getLastName(),
                        expected.getSalary(),
                        expected.getDepartment()));
    }

    @ParameterizedTest
    @MethodSource("provideIncorrectParams")
    void shouldAddEmployee_WhenIncorrectParams_ThenThrowException(String message, String firstName, String lastName) {

        //test && check
        assertThatExceptionOfType(ParamValidationException.class)
                .isThrownBy(() -> employeeService.add(
                        firstName,
                        lastName,
                        nextDouble(),
                        (short) nextInt()));
    }

    @Test
    void shouldAddEmployee_WhenParamsIsLower_ThenCapitalizeFirstLetter() {
        String firstName = faker.name().firstName().toLowerCase();
        String lastName = faker.name().lastName().toLowerCase();

        //test
        Employee actual = employeeService.add(
                firstName,
                lastName,
                nextDouble(),
                (short) nextInt());

        //check
        assertThat(actual.getFirstName()).isEqualTo(StringUtils.capitalize(firstName));
        assertThat(actual.getLastName()).isEqualTo(StringUtils.capitalize(lastName));
    }

    @Test
    void shouldAddEmployee_WhenToManyEmployee_ThenThrowException() {
        for (short i = 0; i < 12; i++) {
            employeeService.add(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    nextDouble(),
                    (short) nextInt());
        }

        //test && check
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        nextDouble(),
                        (short) nextInt()));
    }

    @Test
    void shouldRemoveEmployee_WhenEmployeeExist_ThenEmployeeRemoved() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Employee expected = employeeService.add(firstName, lastName, nextDouble(), (short) nextInt());

        // test
        Employee actual = employeeService.remove(firstName, lastName);

        // check
        assertThat(actual).isEqualTo(expected);
        Collection<Employee> actualEmployees = employeeService.findAllEmployee();
        assertThat(actualEmployees).isEmpty();
    }

    @Test
    void shouldRemoveEmployee_WhenEmployeeNotExist_ThenThrowException() {
        //test && check
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove(
                        faker.name().firstName(),
                        faker.name().lastName()
                ));
    }

    @Test
    void shouldFindEmployee_WhenEmployeeExist_ThenReturnEmployee() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Employee expected = employeeService.add(firstName, lastName, nextDouble(), (short) nextInt());

        // test
        Employee actual = employeeService.find(firstName, lastName);

        // check
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldFindEmployee_WhenEmployeeNotExist_ThenThrowException() {
        //test && check
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find(
                        faker.name().firstName(),
                        faker.name().lastName()));
    }

    @Test
    void shouldFindAllEmployee_WhenEmptyMap_ThenReturnEmptyCollection() {
        // test
        Collection<Employee> actual = employeeService.findAllEmployee();

        // check
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldFindAllEmployee_WhenRandomizeMap_ThenReturnEmptyCollection() {
        List<Employee> randomize = new ArrayList<>();
        for (int i = 0; i < (short) nextInt(1, 12); i++) {
            randomize.add(employeeService.add(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    nextDouble(),
                    (short) nextInt()));
        }

        // test
        Collection<Employee> actual = employeeService.findAllEmployee();

        // check
        assertThat(actual).containsExactlyInAnyOrderElementsOf(randomize);
    }

    private static Stream<Arguments> provideIncorrectParams() {
        return Stream.of(Arguments.of("Не корректный второй параметр.",
                        faker.name().firstName(), faker.number().digit()),
                Arguments.of("Не корректный первый параметр.",
                        faker.number().digit(), faker.name().firstName(),
                        Arguments.of("Не корректны оба параметра",
                                faker.number().digit(), faker.number().digit())));
    }
}