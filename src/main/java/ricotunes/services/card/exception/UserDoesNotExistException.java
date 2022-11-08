package ricotunes.services.card.exception;

public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException(Long userId) {
        super("User with User ID:" + userId + " does not exist");
    }
}
