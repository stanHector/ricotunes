package ricotunes.services.card.exception;


import ricotunes.services.card.model.User;
import ricotunes.services.card.model.Wallet;

public class WalletDoesNotBelongToUser extends Exception {
    public WalletDoesNotBelongToUser(User user, Wallet wallet) {
        super("Customer with id"+ user.getId()+" does not have associated walletId : "+wallet.getId());
    }
}
