package be.kdg.eirene.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (reason = "Unauthorized access", code = HttpStatus.FORBIDDEN)
public class UnauthorizedAccessException extends RuntimeException {
	public UnauthorizedAccessException(String message) {
		super(message);
	}
}
