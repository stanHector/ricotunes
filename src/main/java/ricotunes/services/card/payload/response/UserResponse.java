package ricotunes.services.card.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ricotunes.services.card.model.User;
import ricotunes.services.card.model.Wallet;


@Data
public class UserResponse {
    @JsonProperty("user")
    private User user;

    @JsonProperty("wallet")
    private Wallet wallet;
}
