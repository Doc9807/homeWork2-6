package homeWork2_6.employeeRecords.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException(String firstName, String lastName) {
        super("Такой сотрудник уже присутствует в базе данных.");
    }
}
