package usermanagement.ds2023_30641_tulbure_claudiu_marcel_1_usermanagement.exception.model;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class UserNotFoundException extends CustomException{
    private static final String MESSAGE = "User not found!";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public UserNotFoundException() {
        super(MESSAGE,httpStatus, new ArrayList<>());
    }
}
