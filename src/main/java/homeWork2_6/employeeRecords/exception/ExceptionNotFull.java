package homeWork2_6.employeeRecords.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionNotFull extends RuntimeException {
    public ExceptionNotFull() {
        super("Один из параметров отсутствует.");
    }
}
