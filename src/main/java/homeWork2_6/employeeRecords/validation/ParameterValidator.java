package homeWork2_6.employeeRecords.validation;

import homeWork2_6.employeeRecords.exception.ParamValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component

public class ParameterValidator {
    public String checkAndCapitalize(String param) {
        if (!StringUtils.isAlpha(param)) {
            throw new ParamValidationException(param);
        }
        return StringUtils.capitalize(param.toLowerCase());
    }
}
