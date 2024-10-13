package homeWork2_6.employeeRecords.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String firstName, String lastName) {
        super("Сотрудник с такими данными не найден.");
    }
}
