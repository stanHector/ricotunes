package ricotunes.services.card.exception;

import ricotunes.services.card.model.User;

public class WalletExistsException extends Exception {

    public WalletExistsException(User user) {
//        super("User "+ user.getFirstname()+" "+ user.getLastname()+" already has a wallet : ");
        super("User "+ user.getFullname()+" already has a wallet : ");
    }
}
