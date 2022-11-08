package ricotunes.services.card.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Map;

@JsonPropertyOrder({
		"success",
		"message"
})
public class ApiResponse<T,K> implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 7702134516418120340L;

	@JsonProperty("status")
	private Boolean status;


	@JsonProperty("message")
	private String message;

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("email")
	private String email;

	@JsonProperty("phone")
	private String phone;

	//@JsonIgnore
	private HttpStatus statusCode;


	private Map<T, K> payload ;

	public ApiResponse() {

	}

	public ApiResponse(Boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public ApiResponse(Boolean status, String message, HttpStatus httpStatus) {
		this.status = status;
		this.message = message;
		this.statusCode = httpStatus;
	}

	public ApiResponse(Boolean status, String message, Map<T,K> payload, HttpStatus httpStatus) {
		this.status = status;
		this.message = message;
		this.statusCode = httpStatus;
		this.payload = payload;
	}

    public ApiResponse(Boolean status, String message, String firstname, String lastname, String email, String phone, HttpStatus statusCode) {
        this.status = status;
        this.message = message;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.statusCode = statusCode;
    }

    public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setSuccess(Boolean success) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode.value();
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Map<T, K> getPayload() {
		return payload;
	}

	public void setPayload(Map<T, K> payload) {
		this.payload = payload;
	}

}