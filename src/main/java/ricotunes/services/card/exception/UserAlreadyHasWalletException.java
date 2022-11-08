package ricotunes.services.card.exception;


import ricotunes.services.card.model.User;

public class UserAlreadyHasWalletException extends Exception {
    public UserAlreadyHasWalletException(User user) {
//        super("User "+ user.getFirstname()+" "+ user.getLastname()+" already owns a wallet : ");
        super("User "+ user.getFullname()+" already owns a wallet"); //: "+ user.getWallet().getId());
    }
}
