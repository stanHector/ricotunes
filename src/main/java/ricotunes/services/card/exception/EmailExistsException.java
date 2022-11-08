package ricotunes.services.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ricotunes.services.card.payload.response.ApiResponse;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ApiResponse apiResponse;

	public EmailExistsException(ApiResponse apiResponse) {
		super();
		this.apiResponse = apiResponse;
	}

	public EmailExistsException(String message) {
		super(message);
	}



	public EmailExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiResponse getApiResponse() {
		return apiResponse;
	}
}
