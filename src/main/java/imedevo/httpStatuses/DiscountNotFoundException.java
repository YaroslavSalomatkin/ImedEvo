package imedevo.httpStatuses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Discount is not found")
public class DiscountNotFoundException extends Exception {

}
